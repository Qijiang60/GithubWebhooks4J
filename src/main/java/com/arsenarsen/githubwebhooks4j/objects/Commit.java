package com.arsenarsen.githubwebhooks4j.objects;

import com.arsenarsen.githubwebhooks4j.utils.Utils;

import java.time.ZonedDateTime;

/**
 * Represents a single commit in the PushEvent
 * <br>
 * Created by Arsen on 31.8.2016.
 */
public class Commit {
    protected String id;
    protected String tree_id;
    protected boolean distinct;
    protected String timestamp;
    protected String url;
    protected ShortUser author;
    protected ShortUser commiter;
    protected String[] added;
    protected String[] removed;
    protected String[] modified;
    protected String message;

    /**
     * Gets the commit ID
     *
     * @return The commit ID
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the commits tree ID
     *
     * @return The tree ID
     */
    public String getTreeId() {
        return tree_id;
    }

    /**
     * Checks if the commit is distinct
     *
     * @return True if it is distinct
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * Gets the raw timestamp
     *
     * @return The raw timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Gets ZonedDateTime of getTimestamp(), or null, if something fails
     *
     * @return ZonedDateTime of getTimestamp(), or null, if something fails
     */
    public ZonedDateTime getParsedTimestamp() {
        return Utils.parseTimeStamp(getTimestamp());
    }

    /**
     * Gets the commit URL
     *
     * @return The commit URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * Gets the commit author
     *
     * @return The commit author
     */
    public ShortUser getAuthor() {
        return author;
    }

    /**
     * Gets the committer
     *
     * @return The committer
     */
    public ShortUser getCommiter() {
        return commiter;
    }

    /**
     * Gets all added files
     *
     * @return The added files
     */
    public String[] getAdded() {
        return added;
    }

    /**
     * Gets the removed files
     *
     * @return The removed files
     */
    public String[] getRemoved() {
        return removed;
    }

    /**
     * Gets the modified files
     *
     * @return The modified files
     */
    public String[] getModified() {
        return modified;
    }

    /**
     * Gets the commit message
     *
     * @return The commit message
     */
    public String getMessage() {
        return message;
    }
}
