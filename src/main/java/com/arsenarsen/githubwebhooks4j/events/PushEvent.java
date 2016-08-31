package com.arsenarsen.githubwebhooks4j.events;

import com.arsenarsen.githubwebhooks4j.objects.Commit;
import com.arsenarsen.githubwebhooks4j.objects.Pusher;
import com.arsenarsen.githubwebhooks4j.objects.Repository;
import com.arsenarsen.githubwebhooks4j.objects.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

/**
 * Represents a push
 * <br>
 * Created by Arsen on 30.8.2016.
 */
public class PushEvent extends GithubEvent {

    private String ref;
    private String before;
    private String after;
    private boolean created;
    private boolean deleted;
    private boolean forced;
    private String compare;
    private Commit[] commits;
    private Repository repository;
    private Pusher pusher;
    private User sender;

    @Override
    public void parse(String body) throws JsonParseException {
        JsonObject b = new JsonParser().parse(body).getAsJsonObject();
        ref = b.get("ref").getAsString();
        before = b.get("before").getAsString();
        after = b.get("after").getAsString();
        created = b.get("created").getAsBoolean();
        deleted = b.get("deleted").getAsBoolean();
        forced = b.get("forced").getAsBoolean();
        compare = b.get("compare").getAsString();
        commits = gson.fromJson(b.get("commits"), Commit[].class);
        repository = gson.fromJson(b.get("repository"), Repository.class);
        pusher = gson.fromJson(b.get("pusher"), Pusher.class);
        sender = gson.fromJson(b.get("sender"), User.class);
    }

    /**
     * Gets tue ref
     *
     * @return The red
     */
    public String getRef() {
        return ref;
    }

    /**
     * Gets the sha before the commit
     *
     * @return The SHA of the most recent commit on ref before the push.
     */
    public String getBefore() {
        return before;
    }

    /**
     * Gets the sha after the commit
     *
     * @return The SHA after the commit
     */
    public String getAfter() {
        return after;
    }

    /**
     * Checks is created equal to true
     *
     * @return True if it is
     */
    public boolean isCreated() {
        return created;
    }

    /**
     * Checks if is delete is true
     *
     * @return True if it is
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Checks is the push forced
     *
     * @return True if it is forced
     */
    public boolean isForced() {
        return forced;
    }

    /**
     * Gets the commit diffs url
     *
     * @return The diff/compare URL
     */
    public String getCompare() {
        return compare;
    }

    /**
     * Gets the commits
     *
     * @return The commits
     */
    public Commit[] getCommits() {
        return commits;
    }

    /**
     * Gets the repository
     *
     * @return The repository
     */
    public Repository getRepository() {
        return repository;
    }

    /**
     * Gets the pusher
     *
     * @return The pusher
     */
    public Pusher getPusher() {
        return pusher;
    }

    /**
     * Gets the sender
     *
     * @return The sender
     */
    public User getSender() {
        return sender;
    }
}
