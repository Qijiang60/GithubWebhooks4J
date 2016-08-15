package com.arsenarsen.githubwebhooks4j;

import com.arsenarsen.githubwebhooks4j.events.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static spark.Spark.*;

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
            BufferedReader fromWebpage = new BufferedReader(new InputStreamReader(new URL("https://api.github.com/zen").openStream()));
            GHWHLOGGER.debug(fromWebpage.readLine());
        } catch (IOException e) {
            System.err.println("Could not connect to GitHub ZEN API! If it is down, you can just ignore this error. This was put here just because");
            e.printStackTrace();
        }
        events.put("commit_comment", CommitCommentEvent.class);
        events.put("create", CreateEvent.class);
        events.put("delete", DeleteEvent.class);
    }

    private Set<EventListener> listeners = new HashSet<>();

    // Locks the default constructor
    private GithubWebhooks4J() {
    }

    // Use the builder
    GithubWebhooks4J(String request, final String secret, int port, String ip) {
        if (ip != null) ipAddress(ip);
        if (port != -1) port(port);
        post(request, (req, res) -> {
            if (!req.headers("Content-Type").equals("application/json")) {
                GHWHLOGGER.error("There was an attempt to make a non JSON POST request! The request type was: " + req.headers("Content-Type"));
                res.status(400);
                return "Content-Type must be application/json!";
            }
            if (!secret.equals("")) {
                String signedMessage = req.headers("X-Hub-Signature");
                if (signedMessage == null)
                    signedMessage = "sha0=Tm90aGluZyB0byBzZWUgaGVyZSBwYWxzLi4gS2VlcCBvbiByZWFkaW5nIG15IHNvdXJjZQ"; // Ignore that long Base64 string
                if (!hmacSha1Hex(secret, req.body()).equalsIgnoreCase(signedMessage)) {
                    GHWHLOGGER.error("There was an attempt to make an unauthorized request!");
                    res.status(401);
                    return "Unauthorized access!";
                }
            }
            Class<? extends GithubEvent> eventClass = events.getOrDefault(req.headers("X-GitHub-Event"), UnresolvedEvent.class);
            GithubEvent event = eventClass.newInstance();
            Field webhooks = eventClass.getField("webhooks");
            webhooks.setAccessible(true);
            webhooks.set(event, this);
            webhooks.setAccessible(false);

            event.parse(req.body());

            int dispatched = 0;
            for (EventListener listener : listeners) {
                Method handle;
                try {
                    handle = eventClass.getMethod("handle", eventClass);
                } catch (NoSuchMethodException ignored) {
                    continue;
                }
                dispatched++;
                GHWHLOGGER.info("Dispatching " + listener.getClass().getSimpleName());
                handle.invoke(listener, event);
            }

            return "\uD83D\uDC4C PERFECT! Dispatched handler count: " + dispatched;
        });
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
     * Adds a listener to the listeners list
     *
     * @param listener The listener to add
     */
    public void addListener(EventListener listener) {
        listeners.add(listener);
    }

    /**
     * Removes a listener from the listener list
     *
     * @param listener The listener to remove
     * @return True if it was removed; False otherwise
     */
    public boolean removeListener(EventListener listener) {
        return listeners.remove(listener);
    }

    /**
     * Gets all currently registered listeners
     *
     * @return The listeners in a set that is a clone of the registry
     */
    public Set<EventListener> getListeners() {
        Set<EventListener> clone = new HashSet<>();
        clone.addAll(listeners);
        return clone;
    }
}
