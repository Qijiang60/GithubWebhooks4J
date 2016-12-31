package com.arsenarsen.githubwebhooks4j.events;

import com.arsenarsen.githubwebhooks4j.objects.RefType;
import com.arsenarsen.githubwebhooks4j.objects.Repository;
import com.arsenarsen.githubwebhooks4j.objects.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Fires on a tag, branch or repository creation<br>
 * Look <a href="https://developer.github.com/v3/activity/events/types/#createevent">here</a>.
 */
public class CreateEvent extends GithubEvent {
    private RefType ref_type;
    private String ref;
    private String pusherType;
    private String description;
    private String masterBranch;
    private Repository repository;
    private User sender;

    @Override
    public void parse(String body) {
        JsonObject response = new JsonParser().parse(body).getAsJsonObject();
        ref_type = RefType.getRef(response.get("ref_type").getAsString());
        ref = response.get("ref").getAsString();
        repository = gson.fromJson(response.get("repository"), Repository.class);
        sender = gson.fromJson(response.get("sender"), User.class);
        pusherType = response.get("pusher_type").getAsString();
        description = response.get("description").getAsString();
        masterBranch = response.get("master_branch").getAsString();
    }

    /**
     * @return The ref type.
     */
    public RefType getRefType() {
        return ref_type;
    }

    /**
     * @return The ref.
     */
    public String getRef() {
        return ref;
    }

    /**
     * @return The pusher type.
     */
    public String getPusherType() {
        return pusherType;
    }

    /**
     * @return The repository description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return The master branch name.
     */
    public String getMasterBranch() {
        return masterBranch;
    }

    /**
     * @return The repository this event occured in.
     */
    public Repository getRepository() {
        return repository;
    }

    /**
     * @return The sender.
     */
    public User getSender() {
        return sender;
    }
}
