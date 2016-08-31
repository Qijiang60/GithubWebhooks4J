package com.arsenarsen.githubwebhooks4j.objects;

/**
 * Pusher in a push event.. Damn simple
 * <br>
 * Created by Arsen on 31.8.2016.
 */
public class Pusher {

    protected String name;
    protected String email;

    /**
     * Gets the pushers name
     *
     * @return The pushers name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the pushers E-Mail address
     *
     * @return Their email
     */
    public String getEmail() {
        return email;
    }
}
