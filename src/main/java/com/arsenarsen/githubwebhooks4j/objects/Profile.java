package com.arsenarsen.githubwebhooks4j.objects;

/**
 * Stores an user that was saved in a response.
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
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return The company.
     */
    public String getCompany() {
        return company;
    }

    /**
     * @return Their website.
     */
    public String getBlog() {
        return blog;
    }

    /**
     * @return The users location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return The users email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return True if the user is hireable, false otherwise.
     */
    public boolean isHireable() {
        return hireable;
    }

    /**
     * @return Their bio.
     */
    public String getBio() {
        return bio;
    }

    /**
     * @return The repo count.
     */
    public int getPublicRepos() {
        return public_repos;
    }

    /**
     * @return The public gist count.
     */
    public int getPublicGists() {
        return public_gists;
    }

    /**
     * @return The follower count.
     */
    public int getFollowers() {
        return followers;
    }

    /**
     * @return Amount of users they're following.
     */
    public int getFollowing() {
        return following;
    }

    /**
     * @return The creation date.
     */
    public String getCreationDate() {
        return created_at;
    }

    /**
     * @return The last update date.
     */
    public String getLastUpdateDate() {
        return updated_at;
    }
}
