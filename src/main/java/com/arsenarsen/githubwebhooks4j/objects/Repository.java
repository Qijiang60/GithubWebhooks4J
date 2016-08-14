package com.arsenarsen.githubwebhooks4j.objects;

import com.google.gson.annotations.SerializedName;

// TODO: JavaDoc this file

/**
 * Represents a GitHub repository
 * <br>
 * Created by Arsen on 14.8.2016..
 */
public class Repository {
    protected int id;
    protected String name;
    protected String full_name;
    protected User owner;
    @SerializedName("private")
    protected boolean isPrivate;
    protected String html_url;
    protected String description;
    protected boolean fork;
    protected String url;
    protected String forks_url;
    protected String keys_url;
    protected String collaborators_url;
    protected String teams_url;
    protected String hooks_url;
    protected String issue_events_url;
    protected String events_url;
    protected String assignees_url;
    protected String branches_url;
    protected String tags_url;
    protected String blobs_url;
    protected String git_tags_url;
    protected String git_refs_url;
    protected String trees_url;
    protected String statuses_url;
    protected String languages_url;
    protected String stargazers_url;
    protected String contributors_url;
    protected String subscribers_url;
    protected String subscription_url;
    protected String commits_url;
    protected String git_commits_url;
    protected String comments_url;
    protected String issue_comment_url;
    protected String contents_url;
    protected String compare_url;
    protected String merges_url;
    protected String archive_url;
    protected String downloads_url;
    protected String issues_url;
    protected String pulls_url;
    protected String milestones_url;
    protected String notifications_url;
    protected String labels_url;
    protected String releases_url;
    protected String deployments_url;
    protected String created_at;
    protected String updated_at;
    protected String pushed_at;
    protected String git_url;
    protected String ssh_url;
    protected String clone_url;
    protected String svn_url;
    protected String homepage;
    protected int size;
    protected int stargazers_count;
    protected int watchers_count;
    protected String language;
    protected boolean has_issues;
    protected boolean has_downloads;
    protected boolean has_wiki;
    protected boolean has_pages;
    protected int forks_count;
    protected String mirror_url;
    protected int open_issues_count;
    protected int forks;
    protected int open_issues;
    protected int watchers;
    protected String default_branch;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return full_name;
    }

    public User getOwner() {
        return owner;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public String getPageUrl() {
        return html_url;
    }

    public String getCollaboratorsUrl() {
        return collaborators_url;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public boolean isFork() {
        return fork;
    }

    public int getSize() {
        return size;
    }

    public String getArchiveUrl() {
        return archive_url;
    }

    public String getBranchesUrl() {
        return branches_url;
    }

    public int getStargazersCount() {
        return stargazers_count;
    }

    public String getBlobsUrl() {
        return blobs_url;
    }

    public String getCloneUrl() {
        return clone_url;
    }

    public String getUpdatedAt() {
        return updated_at;
    }

    public String getAssigneesUrl() {
        return assignees_url;
    }

    public String getKeysUrl() {
        return keys_url;
    }

    public String getForksUrl() {
        return forks_url;
    }

    public String getTeamsUrl() {
        return teams_url;
    }

    public String getIssueEventsUrl() {
        return issue_events_url;
    }

    public String getHooksUrl() {
        return hooks_url;
    }

    public String getEventsUrl() {
        return events_url;
    }

    public String getTagsUrl() {
        return tags_url;
    }

    public String getGitTagsUrl() {
        return git_tags_url;
    }

    public String getGitCommitsUrl() {
        return git_commits_url;
    }

    public String getGitRefsUrl() {
        return git_refs_url;
    }

    public String getTreesUrl() {
        return trees_url;
    }

    public String getStatusesUrl() {
        return statuses_url;
    }

    public String getLanguagesUrl() {
        return languages_url;
    }

    public String getStargazersUrl() {
        return stargazers_url;
    }

    public String getContributorsUrl() {
        return contributors_url;
    }

    public String getSubscribersUrl() {
        return subscribers_url;
    }

    public String getSubscriptionUrl() {
        return subscription_url;
    }

    public String getCommentsUrl() {
        return comments_url;
    }

    public String getCommitsUrl() {
        return commits_url;
    }

    public String getIssueCommentUrl() {
        return issue_comment_url;
    }

    public String getContentsUrl() {
        return contents_url;
    }

    public String getCompareUrl() {
        return compare_url;
    }

    public String getMergesUrl() {
        return merges_url;
    }

    public String getDownloadsUrl() {
        return downloads_url;
    }

    public String getIssuesUrl() {
        return issues_url;
    }

    public String getPullsUrl() {
        return pulls_url;
    }

    public String getMilestonesUrl() {
        return milestones_url;
    }

    public String getNotificationsUrl() {
        return notifications_url;
    }

    public String getLabelsUrl() {
        return labels_url;
    }

    public String getReleasesUrl() {
        return releases_url;
    }

    public String getDeploymentsUrl() {
        return deployments_url;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public String getPushedAt() {
        return pushed_at;
    }

    public String getGitUrl() {
        return git_url;
    }

    public String getSshUrl() {
        return ssh_url;
    }

    public String getSvnUrl() {
        return svn_url;
    }

    public String getHomepage() {
        return homepage;
    }

    public int getWatchersCount() {
        return watchers_count;
    }

    public String getLanguage() {
        return language;
    }

    public boolean hasIssues() {
        return has_issues;
    }

    public boolean hasPages() {
        return has_pages;
    }

    public boolean hasDownloads() {
        return has_downloads;
    }

    public boolean hasWiki() {
        return has_wiki;
    }

    public int getForkCount() {
        return forks_count;
    }

    public String getMirrorUrl() {
        return mirror_url;
    }

    public int getOpenIssuesCount() {
        return open_issues_count;
    }

    public int getForks() {
        return forks;
    }

    public int getOpenIssues() {
        return open_issues;
    }

    public int getWatchers() {
        return watchers;
    }

    public String getDefaultBranch() {
        return default_branch;
    }
}
