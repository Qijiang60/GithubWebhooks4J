package com.arsenarsen.githubwebhooks4j.objects;

import com.arsenarsen.githubwebhooks4j.utils.Utils;

import java.time.ZonedDateTime;

/**
 * Represents a comment.
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
     * @return The user.
     */
    public User getUser() {
        return user;
    }

    /**
     * @return The API url.
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return The front page.
     */
    public String getPageUrl() {
        return html_url;
    }

    /**
     * @return The ID.
     */
    public int getId() {
        return id;
    }

    /**
     * @return The position, or null, if it isn't on a file.
     */
    public int getPosition() {
        return position;
    }

    /**
     * @return The line, or null, if it isn't on a file.
     */
    public int getLine() {
        return line;
    }

    /**
     * @return The file path, or null, if it isn't on a file.
     */
    public String getPath() {
        return path;
    }

    /**
     * @return The commit ID.
     */
    public String getCommitId() {
        return commit_id;
    }

    /**
     * @return The creation raw timestamp.
     */
    public String getRawCreatedAt() {
        return created_at;
    }

    /**
     * @return The update raw timestamp.
     */
    public String getRawUpdatedAt() {
        return updated_at;
    }

    /**
     * @return The ZonedDateTime that shows when this comment was updated.
     */
    public ZonedDateTime getUpdatedAt() {
        return Utils.parseTimeStamp(getRawUpdatedAt());
    }

    /**
     * @return The ZonedDateTime that shows when this comment was created.
     */
    public ZonedDateTime getCreatedAt() {
        return Utils.parseTimeStamp(getRawCreatedAt());
    }

    /**
     * @return The comment body text.
     */
    public String getBody() {
        return body;
    }
}
