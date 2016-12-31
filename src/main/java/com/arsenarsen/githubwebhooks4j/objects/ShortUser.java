package com.arsenarsen.githubwebhooks4j.objects;

/**
 * A short representation of user made for a Commit
 */
public class ShortUser {
    protected String name;
    protected String email;
    protected String username;

    /**
     * @return The name of this user.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The email of this user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return This user's username.
     */
    public String getUsername() {
        return username;
    }
}
