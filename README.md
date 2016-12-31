# GitHubWebhooks4J [![](https://jitpack.io/v/ArsenArsen/GithubWebhooks4J.svg)](https://jitpack.io/#ArsenArsen/GithubWebhooks4J)
A small, easy to use, and work in progress GitHub Webhooks wrapper for Java.
## Obtaining
To get GitHubWebhooks4J use the snippets below and replace VERSION with the latest version.
```xml
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
```
```xml
    <dependency>
        <groupId>com.github.ArsenArsen</groupId>
        <artifactId>githubwebhooks4j</artifactId>
        <version>VERSION</version>
    </dependency>
```
## Usage
To use GitHubWebhooks4J you need to make a new WebhooksBuilder(), then using the provided methods set the port and IP to bind to, endpoint, and the response message for the server.All of them have default values as follows:
* PORT: 8080
* IP: Any local IP address
* Endpoint: /webhooks
* Success message: 👌 PERFECT! Dispatched handler count: %COUNT 
* Binder: `new DefaultBinder()`
In the success message %COUNT gets internally replaced by the amount of dispatched listeners.

At the end, to get the webhooks, just execute .build() and handle the possible IOExcepiton, due to bind error most oftenly. 
To register listeners either run addListener() on either the builder or the built instance.

After doing all of that a server, obtainable through getServer() will be started on a background thread waiting for calls from GitHub.
