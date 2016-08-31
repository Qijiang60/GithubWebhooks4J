package com.arsenarsen.githubwebhooks4j.objects;

import com.arsenarsen.githubwebhooks4j.utils.Utils;

import java.time.ZonedDateTime;

/**
 * Represents a comment
 * <br>
 * Created by Arsen on 14.8.2016..
 */
public class Comment {
    protected String url;
    protected String html_url;
    protected int id;
    protected User user;
    protected int position = -1;
    protected int line = -1;
    protected String path;
    protected String commit_id;
    protected String created_at;
    protected String updated_at;
    protected String body;

    /**
     * Gets the user
     *
     * @return The user
     */
    public User getUser() {
        return user;
    }

    /**
     * Gets the API url
     * @return The API url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Gets the front page url
     * @return The front page
     */
    public String getPageUrl() {
        return html_url;
    }

    /**
     * Gets the ID
     * @return The ID
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the comment position
     * @return The position, or null, if it isn't on a file
     */
    public int getPosition() {
        return position;
    }

    /**
     * Gets the line the comment is positioned on
     * @return The line, or null, if it isn't on a file
     */
    public int getLine() {
        return line;
    }

    /**
     * Gets the comments file path
     * @return The file path, or null, if it isn't on a file
     */
    public String getPath() {
        return path;
    }

    /**
     * Gets the commit ID the comment is on
     *
     * @return The commit ID
     */
    public String getCommitId() {
        return commit_id;
    }

    /**
     * Gets the creation raw timestamp
     *
     * @return The creation raw timestamp
     */
    public String getRawCreatedAt() {
        return created_at;
    }

    /**
     * Gets the update raw timestamp
     *
     * @return The update raw timestamp
     */
    public String getRawUpdatedAt() {
        return updated_at;
    }

    /**
     * Gets the ZonedDateTime of the update
     *
     * @return The ZonedDateTime
     */
    public ZonedDateTime getUpdatedAt() {
        return Utils.parseTimeStamp(getRawUpdatedAt());
    }

    /**
     * Gets the ZonedDateTime of the creation
     *
     * @return The ZonedDateTime
     */
    public ZonedDateTime getCreatedAt() {
        return Utils.parseTimeStamp(getRawCreatedAt());
    }
}
