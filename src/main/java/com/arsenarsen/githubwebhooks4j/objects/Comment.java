package com.arsenarsen.githubwebhooks4j.objects;

/**
 * <br>
 * Created by Arsen on 14.8.2016..
 */
public class Comment {
    protected String url;
    protected String html_url;
    protected int id;
    protected User user;
    protected int position = -1;
    protected int line = -1;
    protected String path;
    protected String commit_id;
    protected String created_at;
    protected String updated_at;
    protected String body;

    public User getUser() {
        return user;
    }

    public String getUrl() {
        return url;
    }

    public String getPageUrl() {
        return html_url;
    }

    public int getId() {
        return id;
    }

    public int getPosition() {
        return position;
    }

    public int getLine() {
        return line;
    }

    public String getPath() {
        return path;
    }
}
