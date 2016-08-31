package com.arsenarsen.githubwebhooks4j.events;

import com.arsenarsen.githubwebhooks4j.objects.RefType;
import com.arsenarsen.githubwebhooks4j.objects.Repository;
import com.arsenarsen.githubwebhooks4j.objects.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Fired when a tag or branch get deleted<br>
 * Look here: https://developer.github.com/v3/activity/events/types/#deleteevent
 * <br>
 * Created by Arsen on 15.8.2016..
 */
public class DeleteEvent extends GithubEvent {
    private RefType ref_type;
    private String ref;
    private String pusherType;
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
