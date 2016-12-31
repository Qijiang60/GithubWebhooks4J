package com.arsenarsen.githubwebhooks4j.objects;

/**
 * Stores whatever is the response that the commenting people is
 * <br>
 * Created by Arsen on 13.8.2016..
 */
public class User {
    protected String login;
    protected int id;
    protected String avatar_url;
    protected String gravatar_id;
    protected String url;
    protected String html_url;
    protected String followers_url;
    protected String following_url;
    protected String gists_url;
    protected String starred_url;
    protected String subscriptions_url;
    protected String organizations_url;
    protected String repos_url;
    protected String events_url;
    protected String received_events_url;
    protected String type;
    protected boolean site_admin;

    /**
     * @return The user's login username.
     */
    public String getLogin() {
        return login;
    }

    /**
     * @return The user ID.
     */
    public int getId() {
        return id;
    }

    /**
     * @return The user's avatar URL.
     */
    public String getAvatarUrl() {
        return avatar_url;
    }

    /**
     * @return Users Gravatar ID or an empty string
     */
    public String getGravatarId() {
        return gravatar_id;
    }

    /**
     * @return The api.github.com URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return The profile URL.
     */
    public String getProfile() {
        return html_url;
    }

    /**
     * @return The followers list URL.
     */
    public String getFollowersUrl() {
        return followers_url;
    }

    /**
     * @return The following URL.
     */
    public String getFollowingUrl() {
        return following_url;
    }

    /**
     * @return The gists URL.
     */
    public String getGistsUrl() {
        return gists_url;
    }

    /**
     * @return The starred repositories URL.
     */
    public String getStarredUrl() {
        return starred_url;
    }

    /**
     * @return The subscriptions URL.
     */
    public String getSubscriptionsUrl() {
        return subscriptions_url;
    }

    /**
     * @return The organization URL.
     */
    public String getOrganizationsUrl() {
        return organizations_url;
    }

    /**
     * @return THe repositories URL.
     */
    public String getReposUrl() {
        return repos_url;
    }

    /**
     * @return The events URL.
     */
    public String getEventsUrl() {
        return events_url;
    }

    /**
     * @return The received events URL.
     */
    public String getReceivedEventsUrl() {
        return received_events_url;
    }

    /**
     * @return The user type.
     */
    public String getType() {
        return type;
    }

    /**
     * @return True if the user is a site admin.
     */
    public boolean isSiteAdmin() {
        return site_admin;
    }
}
