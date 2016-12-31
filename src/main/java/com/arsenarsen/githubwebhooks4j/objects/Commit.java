package com.arsenarsen.githubwebhooks4j.objects;

import com.arsenarsen.githubwebhooks4j.utils.Utils;

import java.time.ZonedDateTime;

/**
 * Represents a single commit in the PushEvent
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
     * @return The commit ID.
     */
    public String getId() {
        return id;
    }

    /**
     * @return The tree ID.
     */
    public String getTreeId() {
        return tree_id;
    }

    /**
     * @return True if it is distinct.
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * @return The raw timestamp.
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @return ZonedDateTime of getTimestamp(), or null, if something fails.
     */
    public ZonedDateTime getParsedTimestamp() {
        return Utils.parseTimeStamp(getTimestamp());
    }

    /**
     * @return The commit URL.
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return The commit author.
     */
    public ShortUser getAuthor() {
        return author;
    }

    /**
     * @return The committer.
     */
    public ShortUser getCommiter() {
        return commiter;
    }

    /**
     * @return The added files.
     */
    public String[] getAdded() {
        return added;
    }

    /**
     * @return The removed files.
     */
    public String[] getRemoved() {
        return removed;
    }

    /**
     * @return The modified files.
     */
    public String[] getModified() {
        return modified;
    }

    /**
     * @return The commit message.
     */
    public String getMessage() {
        return message;
    }
}
