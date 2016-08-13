package com.arsenarsen.githubwebhooks4j;

/**
 * <br>
 * Created by Arsen on 13.8.2016..
 */
public class WebhooksBuilder {

    private String request = "/webhooks";
    private String secret = "";
    private int port = -1;
    private String ip = null;

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
     * Sets the IP that Spark will bind to. Default: The Spark default
     * @param ip The IP to bind to
     * @return The builder object for chaining
     */
    public WebhooksBuilder withIp(String ip){
        this.ip = ip;
        return this;
    }

    /**
     * Sets the port that Spark will bind to. Default: The Spark default
     * @param port The port to bind to
     * @return The builder object for chaining
     */
    public WebhooksBuilder onPort(int port){
        this.port = port;
        return this;
    }

    /**
     * Builds the webhooks object
     * @return The newly created webhooks object with the specified parameters
     */
    public GithubWebhooks4J build(){
        return new GithubWebhooks4J(request, secret, port, ip);
    }
}
