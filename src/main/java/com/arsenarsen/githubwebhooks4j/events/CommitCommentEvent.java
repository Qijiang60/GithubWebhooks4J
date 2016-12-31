package com.arsenarsen.githubwebhooks4j.events;

import com.arsenarsen.githubwebhooks4j.objects.Comment;
import com.arsenarsen.githubwebhooks4j.objects.Repository;
import com.arsenarsen.githubwebhooks4j.objects.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Triggered when a commit comment is created.<br>
 * Look <a href="https://developer.github.com/v3/activity/events/types/#commitcommentevent">here</a>.
 */
public class CommitCommentEvent extends GithubEvent {
    private String action;
    private Comment comment;
    private Repository repository;
    private User sender;

    @Override
    public void parse(String body) {
        JsonObject response = new JsonParser().parse(body).getAsJsonObject();
        action = response.get("action").getAsString();
        comment = gson.fromJson(response.get("comment"), Comment.class);
        sender = comment.getUser();
        repository = gson.fromJson(response.get("repository"), Repository.class);
    }

    /**
     * @return The commit comment.
     */
    public Comment getComment() {
        return comment;
    }

    /**
     * @return The sender.
     */
    public User getSender() {
        return sender;
    }

    /**
     * @return The action from the event
     */
    public String getAction() {
        return action;
    }

    /**
     * @return The repository where this comment was in.
     */
    public Repository getRepository() {
        return repository;
    }
}
