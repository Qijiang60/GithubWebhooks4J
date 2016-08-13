package com.arsenarsen.githubwebhooks4j.events;

import com.arsenarsen.githubwebhooks4j.GithubWebhooks4J;

/**
 * Bare bones for all github events
 * <br>
 * Created by Arsen on 13.8.2016..
 */
public abstract class GithubEvent {

    // DO NOT REMOVE!
    protected GithubWebhooks4J webhooks;

    /**
     * Gets the webhooks that dispatched the event
     * @return The dispatching webhooks
     */
    public GithubWebhooks4J getWebhooks(){
        return webhooks;
    }

    /**
     * Takes a call body and saves all of the data into the event
     * @param body The call body
     */
    public abstract void parse(String body);
}
