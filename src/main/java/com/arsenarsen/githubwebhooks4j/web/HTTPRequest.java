package com.arsenarsen.githubwebhooks4j.web;

import java.io.InputStream;
import java.util.Map;

/**
 * Internally used class to describe a route to GithubWebhooks4J
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

    public String getRequestMethod() {
        return requestMethod;
    }

    public InputStream getRequestBody() {
        return requestBody;
    }

    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }
}
