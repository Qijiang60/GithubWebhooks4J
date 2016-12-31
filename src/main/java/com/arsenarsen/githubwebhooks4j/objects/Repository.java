package com.arsenarsen.githubwebhooks4j.objects;

import com.google.gson.annotations.SerializedName;

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

    /**
     * @return This repository's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * @return This repository's name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return This repository's full name.
     */
    public String getFullName() {
        return full_name;
    }

    /**
     * @return This repository's owner URL.
     */
    public User getOwner() {
        return owner;
    }

    /**
     * @return True if this repository is private.
     */
    public boolean isPrivate() {
        return isPrivate;
    }

    /**
     * @return This repository's page URL.
     */
    public String getPageUrl() {
        return html_url;
    }

    /**
     * @return This repository's collaborators URL.
     */
    public String getCollaboratorsUrl() {
        return collaborators_url;
    }

    /**
     * @return This repository's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return This repository's URL.
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return True if this repository is a fork.
     */
    public boolean isFork() {
        return fork;
    }

    /**
     * @return This repository's size URL.
     */
    public int getSize() {
        return size;
    }

    /**
     * @return This repository's archives URL.
     */
    public String getArchiveUrl() {
        return archive_url;
    }

    /**
     * @return This repository's branches URL.
     */
    public String getBranchesUrl() {
        return branches_url;
    }

    /**
     * @return This repository's stargazers count URL.
     */
    public int getStargazersCount() {
        return stargazers_count;
    }

    /**
     * @return This repository's blobs URL.
     */
    public String getBlobsUrl() {
        return blobs_url;
    }

    /**
     * @return This repository's clone URL.
     */
    public String getCloneUrl() {
        return clone_url;
    }

    /**
     * @return This repository's updated at URL.
     */
    public String getUpdatedAt() {
        return updated_at;
    }

    /**
     * @return This repository's assignees URL.
     */
    public String getAssigneesUrl() {
        return assignees_url;
    }

    /**
     * @return This repository's keys URL.
     */
    public String getKeysUrl() {
        return keys_url;
    }

    /**
     * @return This repository's forks URL.
     */
    public String getForksUrl() {
        return forks_url;
    }

    /**
     * @return This repository's teams URL.
     */
    public String getTeamsUrl() {
        return teams_url;
    }

    /**
     * @return This repository's issue events URL.
     */
    public String getIssueEventsUrl() {
        return issue_events_url;
    }

    /**
     * @return This repository's hooks URL.
     */
    public String getHooksUrl() {
        return hooks_url;
    }

    /**
     * @return This repository's events URL.
     */
    public String getEventsUrl() {
        return events_url;
    }

    /**
     * @return This repository's tags URL.
     */
    public String getTagsUrl() {
        return tags_url;
    }

    /**
     * @return This repository's git tags URL.
     */
    public String getGitTagsUrl() {
        return git_tags_url;
    }

    /**
     * @return This repository's commits URL.
     */
    public String getGitCommitsUrl() {
        return git_commits_url;
    }

    /**
     * @return This repository's git refs URL.
     */
    public String getGitRefsUrl() {
        return git_refs_url;
    }

    /**
     * @return This repository's trees URL.
     */
    public String getTreesUrl() {
        return trees_url;
    }

    /**
     * @return This repository's statuses URL.
     */
    public String getStatusesUrl() {
        return statuses_url;
    }

    /**
     * @return This repository's languages URL.
     */
    public String getLanguagesUrl() {
        return languages_url;
    }

    /**
     * @return This repository's stargazers URL.
     */
    public String getStargazersUrl() {
        return stargazers_url;
    }

    /**
     * @return This repository's contributors URL.
     */
    public String getContributorsUrl() {
        return contributors_url;
    }

    /**
     * @return This repository's subscribersURL.
     */
    public String getSubscribersUrl() {
        return subscribers_url;
    }

    /**
     * @return This repository's subscription URL.
     */
    public String getSubscriptionUrl() {
        return subscription_url;
    }

    /**
     * @return This repository's comments URL.
     */
    public String getCommentsUrl() {
        return comments_url;
    }

    /**
     * @return This repository's commits URL.
     */
    public String getCommitsUrl() {
        return commits_url;
    }

    /**
     * @return This repository's issue comment URL.
     */
    public String getIssueCommentUrl() {
        return issue_comment_url;
    }

    /**
     * @return This repository's contents URL.
     */
    public String getContentsUrl() {
        return contents_url;
    }

    /**
     * @return This repository's compare URL.
     */
    public String getCompareUrl() {
        return compare_url;
    }

    /**
     * @return This repository's merges URL.
     */
    public String getMergesUrl() {
        return merges_url;
    }

    /**
     * @return This repository's downloads URL.
     */
    public String getDownloadsUrl() {
        return downloads_url;
    }

    /**
     * @return This repository's issues URL.
     */
    public String getIssuesUrl() {
        return issues_url;
    }

    /**
     * @return This repository's pulls URL.
     */
    public String getPullsUrl() {
        return pulls_url;
    }

    /**
     * @return This repository's milestones URL.
     */
    public String getMilestonesUrl() {
        return milestones_url;
    }

    /**
     * @return This repository's notifications URL.
     */
    public String getNotificationsUrl() {
        return notifications_url;
    }

    /**
     * @return This repository's labels URL.
     */
    public String getLabelsUrl() {
        return labels_url;
    }

    /**
     * @return This repository's releases URL.
     */
    public String getReleasesUrl() {
        return releases_url;
    }

    /**
     * @return This repository's deployments URL.
     */
    public String getDeploymentsUrl() {
        return deployments_url;
    }

    /**
     * @return The time this repository was created.
     */
    public String getCreatedAt() {
        return created_at;
    }

    // TODO Check this.

    /**
     * @return The time this repository was last pushed to.
     */
    public String getPushedAt() {
        return pushed_at;
    }

    /**
     * @return The git url of this repository.
     */
    public String getGitUrl() {
        return git_url;
    }

    /**
     * @return The SSH URL of this repository.
     */
    public String getSshUrl() {
        return ssh_url;
    }

    /**
     * @return The SVN URL of this repository.
     */
    public String getSvnUrl() {
        return svn_url;
    }

    /**
     * @return The homepage of this repository
     */
    public String getHomepage() {
        return homepage;
    }

    /**
     * @return The amount of watchers.
     */
    public int getWatchersCount() {
        return watchers_count;
    }

    /**
     * @return The language of this repository.
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @return True if this repository has issues.
     */
    public boolean hasIssues() {
        return has_issues;
    }

    /**
     * @return True if this repository has GitHub pages.
     */
    public boolean hasPages() {
        return has_pages;
    }

    /**
     * @return True if this repository has downloads.
     */
    public boolean hasDownloads() {
        return has_downloads;
    }

    /**
     * @return True if this repository has a wiki.
     */
    public boolean hasWiki() {
        return has_wiki;
    }

    /**
     * @return Gets a repository fork count.
     */
    public int getForkCount() {
        return forks_count;
    }

    /**
     * @return Gets the mirror URL.
     */
    public String getMirrorUrl() {
        return mirror_url;
    }

    /**
     * @return The count of open issues.
     */
    public int getOpenIssuesCount() {
        return open_issues_count;
    }

    /**
     * @return The repository fork count.
     */
    public int getForks() {
        return forks;
    }

    /**
     * @return The open issues count.
     */
    public int getOpenIssues() {
        return open_issues;
    }

    /**
     * @return The watcher count.
     */
    public int getWatchers() {
        return watchers;
    }

    /**
     * @return The default branch for the repository.
     */
    public String getDefaultBranch() {
        return default_branch;
    }
}
