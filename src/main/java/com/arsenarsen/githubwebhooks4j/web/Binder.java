package com.arsenarsen.githubwebhooks4j.web;

import com.arsenarsen.githubwebhooks4j.GithubWebhooks4J;

import java.io.IOException;

public interface Binder {
    void bind(String request, String ip, int port, GithubWebhooks4J webhooks) throws IOException;
}
