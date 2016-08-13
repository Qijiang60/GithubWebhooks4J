package com.arsenarsen.githubwebhooks4j.events;

/**
 * Listens for a certain event
 * <br>
 * Created by Arsen on 13.8.2016..
 */
@FunctionalInterface
public interface EventListener<T extends GithubEvent> {

    /**
     * Handles an event
     * @param event The event to handle
     */
    void handle(T event);
}
