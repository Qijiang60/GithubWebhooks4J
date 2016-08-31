package com.arsenarsen.githubwebhooks4j.events;

import com.arsenarsen.githubwebhooks4j.objects.RefType;
import com.arsenarsen.githubwebhooks4j.objects.Repository;
import com.arsenarsen.githubwebhooks4j.objects.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Fires on a tag, branch or repository creation<br>
 * Look here: https://developer.github.com/v3/activity/events/types/#createevent
 * <br>
 * Created by Arsen on 15.8.2016..
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
     * Gets the ref type connected to the event
     *
     * @return The ref type
     */
    public RefType getRefType() {
        return ref_type;
    }

    /**
     * Gets the ref connected to the event
     *
     * @return The ref
     */
    public String getRef() {
        return ref;
    }

    /**
     * Gets the pusher type related to the event
     *
     * @return The pusher type
     */
    public String getPusherType() {
        return pusherType;
    }

    /**
     * Gets the repository description
     *
     * @return The repository description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the master branch name of the repository
     *
     * @return The master branch name
     */
    public String getMasterBranch() {
        return masterBranch;
    }

    /**
     * Gets the related repository
     *
     * @return The related repository
     */
    public Repository getRepository() {
        return repository;
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
