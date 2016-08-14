package com.arsenarsen.githubwebhooks4j.objects;

/**
 * Stores an user that was saved in a response
 * <br>
 * Created by Arsen on 13.8.2016..
 */
public class Profile extends User {
    protected String name;
    protected String company;
    protected String blog;
    protected String location;
    protected String email;
    protected boolean hireable;
    protected String bio;
    protected int public_repos;
    protected int public_gists;
    protected int followers;
    protected int following;
    protected String created_at;
    protected String updated_at;

    /**
     * Gets the name from the server response
     *
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the users company
     *
     * @return The company
     */
    public String getCompany() {
        return company;
    }

    /**
     * Gets the users website
     *
     * @return Their website
     */
    public String getBlog() {
        return blog;
    }

    /**
     * Gets the users location
     *
     * @return The users location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Get the users email
     *
     * @return The users email
     */
    public String getEmail() {
        return email;
    }

    /**
     * True if the user is hireable
     *
     * @return True if the user is hireable, false otherwise
     */
    public boolean isHireable() {
        return hireable;
    }

    /**
     * Gets the users bio
     *
     * @return Their bio
     */
    public String getBio() {
        return bio;
    }

    /**
     * Gets the users public repo count
     *
     * @return The repo count
     */
    public int getPublicRepos() {
        return public_repos;
    }

    /**
     * Gets the users public gist count
     *
     * @return The public gist count
     */
    public int getPublicGists() {
        return public_gists;
    }

    /**
     * Gets the users follower count
     *
     * @return The follower count
     */
    public int getFollowers() {
        return followers;
    }

    /**
     * Gets how many people the user is following
     *
     * @return Amount if users they're following
     */
    public int getFollowing() {
        return following;
    }

    /**
     * Gets users account creation date
     *
     * @return The creation date
     */
    public String getCreationDate() {
        return created_at;
    }

    /**
     * Gets the account last update date
     *
     * @return The last update date
     */
    public String getLastUpdateDate() {
        return updated_at;
    }
}
