package com.arsenarsen.githubwebhooks4j;

import com.arsenarsen.githubwebhooks4j.events.EventListener;
import com.arsenarsen.githubwebhooks4j.events.GithubEvent;
import com.arsenarsen.githubwebhooks4j.events.UnresolvedEvent;
import org.apache.commons.codec.digest.HmacUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import static spark.Spark.*;

/**
 * GitHub webhooks main class, containing an interface to pretty much everything
 * Use WebhooksBuilder to create this class
 * <br>
 * Created by Arsen on 13.8.2016..
 */
public class GithubWebhooks4J {

    private Set<EventListener> listeners = new HashSet<>();

    // Locks the default constructor
    private GithubWebhooks4J() {
    }

    // Use the builder
    GithubWebhooks4J(String request, String secret, int port, String ip) {
        if (ip != null) ipAddress(ip);
        if (port != -1) port(port);
        post(request, (req, res) -> {
            if (!secret.equals("")) {
                String signedMessage = req.headers("X-Hub-Signature");
                if (!HmacUtils.hmacSha1Hex(secret, req.body()).equalsIgnoreCase(signedMessage)) {
                    res.status(401);
                    return "Unauthorized access!";
                }
            }

            Class<? extends GithubEvent> eventClass;

            try {
                String capName = req.headers("X-GitHub-Event");
                capName = capName.substring(0, 1).toUpperCase() + capName.substring(1);
                Class<?> resolvedClass = Class.forName("com.arsenarsen.githubwebhooks4j.events." + capName + "Event");
                if (resolvedClass.isAssignableFrom(GithubEvent.class))
                    //noinspection unchecked
                    eventClass = (Class<? extends GithubEvent>) resolvedClass;
                else eventClass = UnresolvedEvent.class;
            } catch (ClassNotFoundException ignored) {
                eventClass = UnresolvedEvent.class;
            }

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

    /**
     * Adds a listener to the listeners list
     * @param listener The listener to add
     */
    public void addListener(EventListener listener){
        listeners.add(listener);
    }

    /**
     * Removes a listener from the listener list
     * @param listener The listener to remove
     * @return True if it was removed; False otherwise
     */
    public boolean removeListener(EventListener listener){
        return listeners.remove(listener);
    }

    /**
     * Gets all currently registered listeners
     * @return The listeners in a set that is a clone of the registry
     */
    public Set<EventListener> getListeners(){
        Set<EventListener> clone = new HashSet<>();
        clone.addAll(listeners);
        return clone;
    }
}
