package com.arsenarsen.githubwebhooks4j.web;

import java.io.InputStream;
import java.util.Map;

/**
 * Passed to {@link com.arsenarsen.githubwebhooks4j.GithubWebhooks4J#callHooks(HTTPRequest)} for processing from a {@link Binder}
 */
public class HTTPRequest {
    private String requestMethod;
    private InputStream requestBody;
    private Map<String, String> requestHeaders;

    public HTTPRequest(String requestMethod, InputStream requestBody, Map<String, String> requestHeaders) {
        this.requestMethod = requestMethod;
        this.requestBody = requestBody;
        this.requestHeaders = requestHeaders;
    }

    /**
     * @return The request method used.
     */
    public String getRequestMethod() {
        return requestMethod;
    }

    /**
     * @return Request body, as an input stream
     */
    public InputStream getRequestBody() {
        return requestBody;
    }

    /**
     * @return The request headers.
     */
    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }
}
