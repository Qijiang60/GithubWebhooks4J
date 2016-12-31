package com.arsenarsen.githubwebhooks4j.events;

import com.arsenarsen.githubwebhooks4j.GithubWebhooks4J;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

/**
 * Bare bones for all github events.
 */
public abstract class GithubEvent {

    protected final transient Gson gson = new GsonBuilder().setPrettyPrinting().create();
    // DO NOT REMOVE!
    protected transient GithubWebhooks4J webhooks = null;
    private transient String body;

    /**
     * Gets the webhooks that dispatched the event.
     * @return The dispatching webhooks.
     */
    public GithubWebhooks4J getWebhooks(){
        return webhooks;
    }

    /**
     * Sets the webhooks.
     * @param webhooks The new webhooks.
     * @throws IllegalStateException if it was already set.
     */
    public void setWebhooks(GithubWebhooks4J webhooks) throws IllegalStateException {
        if (this.webhooks != null)
            throw new IllegalStateException("Webhooks were already set!");
        this.webhooks = webhooks;
    }

    /**
     * Saves the body and parses.
     *
     * @param body The body.
     */
    public void bodify(String body) throws JsonParseException {
        this.body = body;
        parse(body);
    }

    /**
     * Takes a call body and saves all of the data into the event
     * @param body The call body
     */
    public abstract void parse(String body) throws JsonParseException;

    @Override
    public String toString() {
        try {
            return body;
        } catch (Exception ignored) {
            return super.toString();
        }
    }
}
