package com.arsenarsen.githubwebhooks4j.web;

import com.arsenarsen.githubwebhooks4j.GithubWebhooks4J;

import java.io.IOException;

/**
 * An interface that describes the binding process.
 */
public interface Binder {
    /**
     * Binds to a IP, port and request.
     * Upon receiving an event {@link GithubWebhooks4J#callHooks(HTTPRequest)} must be called.
     *
     * @param request  The request to bind to
     * @param ip       The IP to bind to
     * @param port     The port to bind to
     * @param webhooks The {@link GithubWebhooks4J} object to bind for.
     * @throws IOException In case of a bind failure.
     */
    void bind(String request, String ip, int port, GithubWebhooks4J webhooks) throws IOException;
}
