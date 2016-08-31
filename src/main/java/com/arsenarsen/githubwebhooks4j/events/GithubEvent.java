package com.arsenarsen.githubwebhooks4j.events;

import com.arsenarsen.githubwebhooks4j.GithubWebhooks4J;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

/**
 * Bare bones for all github events
 * <br>
 * Created by Arsen on 13.8.2016..
 */
public abstract class GithubEvent {

    protected final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    // DO NOT REMOVE!
    protected GithubWebhooks4J webhooks = null;
    private String body;

    /**
     * Gets the webhooks that dispatched the event
     * @return The dispatching webhooks
     */
    public GithubWebhooks4J getWebhooks(){
        return webhooks;
    }

    /**
     * Sets the webhooks
     * @param webhooks The new webhooks
     * @throws IllegalStateException if it was already set
     */
    public void setWebhooks(GithubWebhooks4J webhooks) throws IllegalStateException {
        if (this.webhooks != null)
            throw new IllegalStateException("Webhooks were already set!");
        this.webhooks = webhooks;
    }

    /**
     * Saves the body and parses
     *
     * @param body The body
     */
    public void bodify(String body) {
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
            return gson.toJson(new JsonParser().parse(body));
        } catch (Exception ignored) {
            return super.toString();
        }
    }
}
