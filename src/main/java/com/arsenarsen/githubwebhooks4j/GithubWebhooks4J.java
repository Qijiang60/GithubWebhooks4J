package com.arsenarsen.githubwebhooks4j;

import com.arsenarsen.githubwebhooks4j.events.CommitCommentEvent;
import com.arsenarsen.githubwebhooks4j.events.EventListener;
import com.arsenarsen.githubwebhooks4j.events.GithubEvent;
import com.arsenarsen.githubwebhooks4j.events.UnresolvedEvent;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
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

    public static final String SHA_1 = "HmacSHA1";
    public static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();
    private static final Map<String, Class<? extends GithubEvent>> events = new ConcurrentHashMap<>();

    static {
        events.put("commit_comment", CommitCommentEvent.class);
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
            if (!"application/json".equals(req.headers("Content-Type"))) {
                res.status(500);
                return "Content-Type must be application/json!";
            }
            if (!secret.equals("")) {
                String signedMessage = req.headers("X-Hub-Signature");
                if (!hmacSha1Hex(secret, req.body()).equalsIgnoreCase(signedMessage)) {
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

            for (EventListener listener : listeners) {
                Method handle;
                try {
                    handle = eventClass.getMethod("handle", eventClass);
                } catch (NoSuchMethodException ignored) {
                    continue;
                }
                handle.invoke(listener, event);
            }

            return "";
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
