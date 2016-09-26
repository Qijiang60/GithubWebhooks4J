package com.arsenarsen.githubwebhooks4j;

import com.arsenarsen.githubwebhooks4j.events.*;
import com.google.gson.JsonParseException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * GitHub webhooks main class, containing an interface to pretty much everything
 * Use WebhooksBuilder to create this class
 * <br>
 * Created by Arsen on 13.8.2016..
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
            GHWHLOGGER.debug(fromWebpage.readLine());
        } catch (IOException e) {
            GHWHLOGGER.error("Could not connect to GitHub ZEN API! If it is down, you can just ignore this error. " +
                    "This was put here just because", e);
        }
        events.put("commit_comment", CommitCommentEvent.class);
        events.put("create", CreateEvent.class);
        events.put("delete", DeleteEvent.class);
        events.put("push", PushEvent.class);
    }

    private HttpServer server;
    private String secret;
    private Set<EventListener> listeners = new HashSet<>();
    private String successMessage;

    // Locks the default constructor
    private GithubWebhooks4J() {
    }

    // Use the builder
    GithubWebhooks4J(String request, final String secret, int port, String ip, String successMessage,
                     Set<EventListener> listeners) throws IOException {
//        if(!(request.charAt(0) == '/'))
//            request = '/' + request;
        this.listeners.addAll(listeners);
        this.secret = secret;
        this.successMessage = successMessage;
        if (ip == null)
            server = HttpServer.create(new InetSocketAddress(port), 0);
        else
            server = HttpServer.create(new InetSocketAddress(ip, port), 0);

        server.createContext(request, httpExchange -> {
            Response res = callHooks(httpExchange);
            byte[] bytes = res.response.getBytes(StandardCharsets.UTF_8);
            httpExchange.sendResponseHeaders(res.code, bytes.length);
            httpExchange.getResponseBody().write(bytes);
            httpExchange.getResponseBody().close();
        });
        server.setExecutor(null);
        server.start();
    }

    private Response callHooks(HttpExchange httpExchange) {
        if (!httpExchange.getRequestMethod().equals("POST"))
            return new Response("");
        try {
            BufferedReader r = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()));
            String line, body = "";
            while ((line = r.readLine()) != null)
                body += line;
            if (!httpExchange.getRequestHeaders().getFirst("Content-Type").equals("application/json")) {
                GHWHLOGGER.error("There was an attempt to make a non JSON POST request! The request type was: " +
                        httpExchange.getRequestHeaders().getFirst("Content-Type"));
                return new Response("Content-Type must be application/json!", 400);
            }
            if (secret != null) {
                String signedMessage = httpExchange.getRequestHeaders().getFirst("X-Hub-Signature");
                if (signedMessage == null)
                    // Ignore that long Base64 string
                    signedMessage = "sha0=Tm90aGluZyB0byBzZWUgaGVyZSBwYWxzLi4gS2VlcCBvbiByZWFkaW5nIG15IHNvdXJjZQ";
                if (!("sha1=" + hmacSha1Hex(secret, body)).equalsIgnoreCase(signedMessage)) {
                    GHWHLOGGER.error("There was an attempt to make an unauthorized request!");
                    return new Response("Unauthorized access!", 401);
                }
            }
            Class<? extends GithubEvent> eventClass = events.getOrDefault(httpExchange.getRequestHeaders().getFirst
                            ("X-GitHub-Event"),
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
                            && m.isBridge()) {
                        m.setAccessible(true);
                        m.invoke(listener, event);
                        dispatched++;
                        GHWHLOGGER.debug("Dispatching " + listener.getClass().getSimpleName());
                    }
                }
            }
            String response = successMessage.replace("%COUNT", String.valueOf(dispatched));
            return new Response(response);
        } catch (JsonParseException e) {
            GHWHLOGGER.error("Bad request!", e);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.close();
            return new Response("<b>Bad request!<br>n\n" + sw.toString(), 400);
        } catch (Exception e) {
            GHWHLOGGER.error("Something went wrong!", e);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.close();
            return new Response("<b>Internal error!<br>n\n" + sw.toString(), 500);
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

    private synchronized Set<EventListener> access() {
        return listeners;
    }

    /**
     * Adds a listener to the listeners list
     *
     * @param listener The listener to add
     */
    public void addListener(EventListener listener) {
        access().add(listener);
    }

    /**
     * Removes a listener from the listener list
     *
     * @param listener The listener to remove
     * @return True if it was removed; False otherwise
     */
    public boolean removeListener(EventListener listener) {
        return access().remove(listener);
    }

    /**
     * Gets all currently registered listeners
     *
     * @return The listeners in a set that is a clone of the registry
     */
    public Set<EventListener> getListeners() {
        Set<EventListener> clone = new HashSet<>();
        clone.addAll(access());
        return clone;
    }

    /**
     * Gets the server instance
     *
     * @return The server instance
     */
    public HttpServer getServer() {
        return server;
    }

    private class Response {
        String response;
        int code;

        Response(String response, int code) {
            this.response = response;
            this.code = code;
        }

        Response(String response) {
            this(response, 200);
        }
    }
}
