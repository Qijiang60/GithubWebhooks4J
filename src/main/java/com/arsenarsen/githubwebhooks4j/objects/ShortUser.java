package com.arsenarsen.githubwebhooks4j.objects;

/**
 * A short representation of user made for a Commit
 * <br>
 * Created by Arsen on 31.8.2016.
 */
public class ShortUser {
    protected String name;
    protected String email;
    protected String username;

    /**
     * Gets the name of this user
     *
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the email of this user
     *
     * @return The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the username of this user
     *
     * @return The username
     */
    public String getUsername() {
        return username;
    }
}
