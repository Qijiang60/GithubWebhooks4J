package com.arsenarsen.githubwebhooks4j.objects;

/**
 * Pusher in a push event.. Damn simple.
 */
public class Pusher {

    protected String name;
    protected String email;

    /**
     * @return The pushers name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Their email.
     */
    public String getEmail() {
        return email;
    }
}
