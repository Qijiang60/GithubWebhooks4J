package com.arsenarsen.githubwebhooks4j.web;

/**
 * Used to represent a response.
 */
public class Response {
    private String response;
    private int code;

    public Response(String response, int code) {
        this.response = response;
        this.code = code;
    }

    public Response(String response) {
        this(response, 200);
    }

    /**
     * @return The currently set HTTP Response code.
     */
    public int getCode() {
        return code;
    }

    /**
     * Updates the set HTTP Response code with a new one.
     *
     * @param code The new response code.
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return The HTTP Response text to send.
     */
    public String getResponse() {
        return response;
    }

    /**
     * Sets a new HTTP Response text that is supposed to be sent.
     *
     * @param response The new response text.
     */
    public void setResponse(String response) {
        this.response = response;
    }
}
