package com.arsenarsen.githubwebhooks4j;

import com.arsenarsen.githubwebhooks4j.events.EventListener;
import com.arsenarsen.githubwebhooks4j.web.Binder;
import com.arsenarsen.githubwebhooks4j.web.DefaultBinder;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Makes webhooks
 * <br>
 * Created by Arsen on 13.8.2016..
 */
public class WebhooksBuilder {

    private String request = "/webhooks";
    private String secret = null;
    private int port = 8080;
    private String ip = null;
    private String successMessage = "\ud83d\udC4c PERFECT! Dispatched handler count: %COUNT";
    private Set<EventListener> listeners = new HashSet<>();
    private Binder binder = new DefaultBinder();

    /**
     * Adds a listener
     *
     * @param listener The listener to add
     * @return The builder object for chaining
     */
    public WebhooksBuilder addListener(EventListener listener) {
        listeners.add(listener);
        return this;
    }

    /**
     * Sets the webhooks request, Default: "/webhooks"
     * @param request Route to map to the listener
     * @return The builder object for chaining
     */
    public WebhooksBuilder forRequest(String request){
        this.request = request;
        return this;
    }

    /**
     * Sets the webhooks secret, Default: ""<br>
     * <b>Never</b> hardcode the token into your app!
     * @param secret The secret
     * @return The builder object for chaining
     */
    public WebhooksBuilder withSecret(String secret){
        this.secret = secret;
        return this;
    }

    /**
     * Sets the IP that HttpServer will bind to. Default: any available local IP address
     * @param ip The IP to bind to
     * @return The builder object for chaining
     */
    public WebhooksBuilder withIp(String ip){
        this.ip = ip;
        return this;
    }

    /**
     * Sets the port that HttpServer will bind to. Default: 8080
     * @param port The port to bind to
     * @return The builder object for chaining
     */
    public WebhooksBuilder onPort(int port){
        this.port = port;
        return this;
    }

    /**
     * Sets the success message. Defaults to '👌 PERFECT! Dispatched handler count: %COUNT'
     *
     * @param successMessage The success message. Use %COUNT to include the count
     * @return The builder object for chaining
     */
    public WebhooksBuilder withSuccesMessage(String successMessage) {
        this.successMessage = successMessage;
        return this;
    }

    /**
     * Builds the webhooks object
     * @return The newly created webhooks object with the specified parameters
     */
    public GithubWebhooks4J build() throws IOException {
        return new GithubWebhooks4J(request, secret, port, ip, successMessage, listeners, binder);
    }
}
