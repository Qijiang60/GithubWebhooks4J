package com.arsenarsen.githubwebhooks4j.events;

/**
 * Something weird happen.. I have no idea what this event that occurred is.
 */
public class UnresolvedEvent extends GithubEvent {
    @Override
    public void parse(String body) {
    }

    /**
     * Returns the unknown event call body.
     * @return The body.
     */
    public String getBody(){
        return toString();
    }
}
