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
     * Gets the users login username
     *
     * @return The login username
     */
    public String getLogin() {
        return login;
    }

    /**
     * Gets the user ID
     *
     * @return the user ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the users avatar URL
     *
     * @return The avatar url
     */
    public String getAvatarUrl() {
        return avatar_url;
    }

    /**
     * Gets the users gravatar ID
     *
     * @return Their gravatar ID or an empty string
     */
    public String getGravatarId() {
        return gravatar_id;
    }

    /**
     * Gets the users api.github.com URL
     *
     * @return The api.github.com URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * Gets the users profile URL
     *
     * @return The profile URL
     */
    public String getProfile() {
        return html_url;
    }

    /**
     * Gets the users follower list URL
     *
     * @return The followers list URL
     */
    public String getFollowersUrl() {
        return followers_url;
    }

    /**
     * Gets the users following URL
     *
     * @return The following URL
     */
    public String getFollowingUrl() {
        return following_url;
    }

    /**
     * Gets the users gists URL
     *
     * @return The gists URL
     */
    public String getGistsUrl() {
        return gists_url;
    }

    /**
     * Gets the users starred repositories URL
     *
     * @return The starred repositories URL
     */
    public String getStarredUrl() {
        return starred_url;
    }

    /**
     * Gets the users subscriptions URL
     *
     * @return The subscriptions URL
     */
    public String getSubscriptionsUrl() {
        return subscriptions_url;
    }

    /**
     * Gets the users organizations URL
     *
     * @return The organization URL
     */
    public String getOrganizationsUrl() {
        return organizations_url;
    }

    /**
     * Gets the users repositories URL
     *
     * @return THe repositories URL
     */
    public String getReposUrl() {
        return repos_url;
    }

    /**
     * Gets the users events URL
     *
     * @return The events URL
     */
    public String getEventsUrl() {
        return events_url;
    }

    /**
     * Gets the users received events URL
     *
     * @return The received events URL
     */
    public String getReceivedEventsUrl() {
        return received_events_url;
    }

    /**
     * Gets the user type
     *
     * @return The user tyoe
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the users site admin status
     *
     * @return True if the user is a site admin
     */
    public boolean isSiteAdmin() {
        return site_admin;
    }
}
