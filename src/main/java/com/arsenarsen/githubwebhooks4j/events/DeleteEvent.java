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
     * @return The ref type of the event.
     */
    public RefType getRefType() {
        return ref_type;
    }

    /**
     * @return The ref connected to the event.-
     */
    public String getRef() {
        return ref;
    }

    /**
     * @return The pusher type of the event.
     */
    public String getPusherType() {
        return pusherType;
    }

    /**
     * @return The repository this event happen in.
     */
    public Repository getRepository() {
        return repository;
    }

    /**
     * @return The event sender.
     */
    public User getSender() {
        return sender;
    }
}
