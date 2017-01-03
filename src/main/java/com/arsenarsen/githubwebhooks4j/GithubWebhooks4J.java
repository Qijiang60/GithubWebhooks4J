package com.arsenarsen.githubwebhooks4j;

import com.arsenarsen.githubwebhooks4j.events.*;
import com.arsenarsen.githubwebhooks4j.utils.Utils;
import com.arsenarsen.githubwebhooks4j.web.Binder;
import com.arsenarsen.githubwebhooks4j.web.HTTPRequest;
import com.arsenarsen.githubwebhooks4j.web.Response;
import com.google.gson.JsonParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * GitHub webhooks main class, containing an interface to pretty much everything.
 * Use WebhooksBuilder to create this class.
 */
public class GithubWebhooks4J {

    public static final Logger GHWHLOGGER = LoggerFactory.getLogger(GithubWebhooks4J.class);
    public static final String SHA_1 = "HmacSHA1";
    public static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();
    private static final Map<String, Class<? extends GithubEvent>> events = new ConcurrentHashMap<>();

    static {
        try {
            BufferedReader fromWebpage = new BufferedReader(new InputStreamReader(new URL("https://api.github" +
                    ".com/zen").openStream()));
            GHWHLOGGER.debug(Markers.ZEN, fromWebpage.readLine());
        } catch (IOException e) {
            GHWHLOGGER.error(Markers.ZEN, "Could not connect to GitHub ZEN API! If it is down, you can just ignore this error. " +
                    "This was put here just because", e);
        }
        events.put("commit_comment", CommitCommentEvent.class);
        events.put("create", CreateEvent.class);
        events.put("delete", DeleteEvent.class);
        events.put("push", PushEvent.class);
    }

    private String secret;
    private String request;
    private int port;
    private String ip;
    private Set<EventListener> listeners = ConcurrentHashMap.newKeySet(); // https://s.mlkshk.com/r/13I1D
    private Binder binder;
    private String successMessage;

    // Locks the default constructor
    private GithubWebhooks4J() {
    }

    // Use the builder
    GithubWebhooks4J(String request, final String secret, int port, String ip, String successMessage,
                     Set<EventListener> listeners, Binder binder) throws IOException {
        this.request = request;
        this.port = port;
        this.ip = ip;
        this.listeners = listeners;
        this.binder = binder;
        if (!(request.charAt(0) == '/'))
            request = '/' + request;
        this.listeners.addAll(listeners);
        this.secret = secret;
        this.successMessage = successMessage;
        binder.bind(request, ip, port, this);
    }

    public Response callHooks(HTTPRequest httpExchange) {
        if (!httpExchange.getRequestMethod().equals("POST"))
            return new Response("Method not alloweled", 405);
        try {
            BufferedReader r = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()));
            String line, body = "";
            while ((line = r.readLine()) != null)
                body += line;
            if (!Utils.uppercaseKeys(httpExchange.getRequestHeaders())
                    .getOrDefault("CONTENT-TYPE", "").equalsIgnoreCase("application/json")) {
                GHWHLOGGER.error(Markers.HANDLER, "There was an attempt to make a non JSON POST request! The request type was: " +
                        httpExchange.getRequestHeaders().getOrDefault("CONTENT-TYPE", "empty"));
                return new Response("Content-Type must be application/json!", 400);
            }
            if (secret != null) {
                String signedMessage = httpExchange.getRequestHeaders().get("X-Hub-Signature");
                if (signedMessage == null)
                    // Ignore that long Base64 string
                    signedMessage = "sha0=Tm90aGluZyB0byBzZWUgaGVyZSBwYWxzLi4gS2VlcCBvbiByZWFkaW5nIG15IHNvdXJjZQ";
                if (!("sha1=" + hmacSha1Hex(secret, body)).equalsIgnoreCase(signedMessage)) {
                    GHWHLOGGER.error(Markers.HANDLER, "There was an attempt to make an unauthorized request!");
                    return new Response("Unauthorized access!", 401);
                }
            }
            Class<? extends GithubEvent> eventClass = events.getOrDefault(httpExchange.getRequestHeaders().get("X-GitHub-Event"),
                    UnresolvedEvent.class);
            GithubEvent event = eventClass.newInstance();
            event.setWebhooks(this);

            event.bodify(body);

            int dispatched = 0;
            for (EventListener listener : getListeners()) {
                for (Method m : listener.getClass().getDeclaredMethods()) {
                    if (m.getName().equals("handle")
                            && m.getParameterCount() == 1
                            && m.getParameterTypes()[0].isAssignableFrom(eventClass)
                            && !m.isBridge()) {
                        m.setAccessible(true);
                        m.invoke(listener, event);
                        dispatched++;
                        GHWHLOGGER.debug(Markers.HANDLER, "Dispatching " + listener.getClass().getSimpleName());
                    }
                }
            }
            GHWHLOGGER.info(Markers.HANDLER, "Success! " + successMessage.replace("%COUNT", String.valueOf(dispatched)));
            String response = successMessage.replace("%COUNT", String.valueOf(dispatched));
            return new Response(response);
        } catch (JsonParseException e) {
            GHWHLOGGER.error(Markers.HANDLER, "Bad request!", e);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.close();
            return new Response("<b>Bad request!<br>\n" + sw.toString(), 400);
        } catch (Exception e) {
            GHWHLOGGER.error(Markers.HANDLER, "Something went wrong!", e);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.close();
            return new Response("<b>Internal error!<br>\n" + sw.toString(), 500);
        }
    }

    private String hmacSha1Hex(String signWith, String toSign) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac sha1 = Mac.getInstance(SHA_1);
        sha1.init(new SecretKeySpec(signWith.getBytes(), SHA_1));
        byte[] bytes = sha1.doFinal(toSign.getBytes(StandardCharsets.UTF_8));
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_CHARS[v >>> 4];
            hexChars[j * 2 + 1] = HEX_CHARS[v & 0x0F];
        }
        return new String(hexChars);
    }

    /**
     * Adds a listener to the listeners list.
     *
     * @param listener The listener to add.
     */
    public void addListener(EventListener listener) {
        listeners.add(listener);
    }

    /**
     * Removes a listener from the listener list.
     *
     * @param listener The listener to remove.
     * @return True if it was removed; False otherwise.
     */
    public boolean removeListener(EventListener listener) {
        return listeners.remove(listener);
    }

    /**
     * Gets all currently registered listeners.
     *
     * @return The listeners in a set that is a clone of the registry.
     */
    public Set<EventListener> getListeners() {
        return Collections.unmodifiableSet(listeners);
    }

    /**
     * @return The binder instance.
     */
    public Binder getBinder() {
        return binder;
    }

    /**
     * @return The IP the binder was supposed to bind to.
     */
    public String getIp() {
        return ip;
    }

    /**
     * @return The port the binder was supposed to bind to.
     */
    public int getPort() {
        return port;
    }

    /**
     * @return The request the binder was supposed to bind to.
     */
    public String getRequest() {
        return request;
    }
}
