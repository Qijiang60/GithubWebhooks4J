package com.arsenarsen.githubwebhooks4j.events;

import com.arsenarsen.githubwebhooks4j.objects.Commit;
import com.arsenarsen.githubwebhooks4j.objects.Pusher;
import com.arsenarsen.githubwebhooks4j.objects.Repository;
import com.arsenarsen.githubwebhooks4j.objects.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

/**
 * Represents a push event.
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
     * @return The ref.
     */
    public String getRef() {
        return ref;
    }

    /**
     * @return The SHA of the most recent commit on ref before the push.
     */
    public String getBefore() {
        return before;
    }

    /**
     * @return The SHA after the commit.
     */
    public String getAfter() {
        return after;
    }

    /**
     * @return True if it is.
     */
    public boolean isCreated() {
        return created;
    }

    /**
     * @return Value of the deleted field.
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * @return True if the push is forced
     */
    public boolean isForced() {
        return forced;
    }

    /**
     * @return The diff/compare URL
     */
    public String getCompare() {
        return compare;
    }

    /**
     * @return The commits that happen
     */
    public Commit[] getCommits() {
        return commits;
    }

    /**
     * @return The repository the commit was pushed to.
     */
    public Repository getRepository() {
        return repository;
    }

    /**
     * @return The pusher of the commit,.
     */
    public Pusher getPusher() {
        return pusher;
    }

    /**
     * @return The sender of the commit.
     */
    public User getSender() {
        return sender;
    }
}
