# GitHubWebhooks4J [![](https://jitpack.io/v/ArsenArsen/GithubWebhooks4J.svg)](https://jitpack.io/#ArsenArsen/GithubWebhooks4J) [![Build Status](https://drone.io/github.com/ArsenArsen/GithubWebhooks4J/status.png)](https://drone.io/github.com/ArsenArsen/GithubWebhooks4J/latest) ![Dependicies](https://www.versioneye.com/user/projects/57c82382968d640049e12720/badge.svg?style=flat-square)
A small, easy to use, and work in progress GitHub Webhooks wrapper for Java.
## Obtaining
To get GitHubWebhooks4J use the snippets below and replace VERSION with the latest version.
```xml
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
```xml
        <dependency>
            <groupId>com.arsenarsen</groupId>
            <artifactId>githubwebhooks4j</artifactId>
            <version>1.1.0</version>
        </dependency>
```
## Usage
To use GitHubWebhooks4J you need to make a new WebhooksBuilder(), then using the provided methods set the port and IP to bind to, endpoint, and the response message for the server. At the end, to get the webhooks, just execute .build() and handle the possible IOExcepiton. All of them have default values as follows:
* PORT: 8080
* IP: Any local IP address
* Endpoint: /webhooks
* Success message: 👌 PERFECT! Dispatched handler count: %COUNT

In the success message %COUNT gets interlnally replaced by the amount of dispatched listeners.

After doing all of that a server, obtainable through getServer() will be started on a background thread waiting for calls from GitHub.