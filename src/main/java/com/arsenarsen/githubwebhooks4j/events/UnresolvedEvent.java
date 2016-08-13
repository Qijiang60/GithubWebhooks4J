package com.arsenarsen.githubwebhooks4j.events;

/**
 * <br>
 * Created by Arsen on 13.8.2016..
 */
public class UnresolvedEvent extends GithubEvent {

    private String body;

    @Override
    public void parse(String body) {
        this.body = body;
    }

    /**
     * Returns the unknown event call body
     * @return The body
     */
    public String getBody(){
        return body;
    }
}
