package com.arsenarsen.githubwebhooks4j.events;

/**
 * Something weird happen.. I have no idea what this event that occurred is.
 * <br>
 * Created by Arsen on 13.8.2016..
 */
public class UnresolvedEvent extends GithubEvent {
    @Override
    public void parse(String body) {
    }

    /**
     * Returns the unknown event call body
     * @return The body
     */
    public String getBody(){
        return toString();
    }
}
