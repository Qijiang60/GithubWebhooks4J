package com.arsenarsen.githubwebhooks4j.events;

import com.arsenarsen.githubwebhooks4j.objects.Comment;
import com.arsenarsen.githubwebhooks4j.objects.Repository;
import com.arsenarsen.githubwebhooks4j.objects.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Triggered when a commit comment is created.<br>
 * Look here: https://developer.github.com/v3/activity/events/types/#commitcommentevent
 * <br>
 * Created by Arsen on 14.8.2016..
 */
public class CommitCommentEvent extends GithubEvent {
    private String action;
    private Comment comment;
    private Repository repository;
    private User sender;

    @Override
    public void parse(String body) {
        Gson gson = new Gson();
        JsonObject response = new JsonParser().parse(body).getAsJsonObject();
        action = response.get("action").getAsString();
        comment = gson.fromJson(response.get("comment"), Comment.class);
        sender = comment.getUser();
        repository = gson.fromJson(response.get("repository"), Repository.class);
    }

    /**
     * Gets the comment from the request
     *
     * @return The comment
     */
    public Comment getComment() {
        return comment;
    }

    /**
     * Gets the comment sender
     *
     * @return The sender
     */
    public User getSender() {
        return sender;
    }

    /**
     * Gets the action
     *
     * @return The action from the event
     */
    public String getAction() {
        return action;
    }

    /**
     * Gets the repository
     *
     * @return The repository
     */
    public Repository getRepository() {
        return repository;
    }
}
