package com.arsenarsen.githubwebhooks4j.web;

import com.arsenarsen.githubwebhooks4j.GithubWebhooks4J;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Default Binder implementation that uses a {@link HttpServer}.
 */
public class DefaultBinder implements Binder {
    @SuppressWarnings("FieldCanBeLocal")
    private HttpServer server;

    @Override
    public void bind(String request, String ip, int port, GithubWebhooks4J webhooks) throws IOException {
        if (ip == null)
            server = HttpServer.create(new InetSocketAddress(port), 0);
        else
            server = HttpServer.create(new InetSocketAddress(ip, port), 0);
        server.createContext(request, httpExchange -> {
            Map<String, String> headers = new HashMap<>();
            for (Map.Entry<String, List<String>> header : httpExchange.getRequestHeaders().entrySet()) {
                headers.put(header.getKey(), header.getValue().get(0));
            }
            Response res = webhooks
                    .callHooks(new HTTPRequest(httpExchange.getRequestMethod(), httpExchange.getRequestBody(), headers));
            byte[] bytes = res.getResponse().getBytes(StandardCharsets.UTF_8);
            httpExchange.sendResponseHeaders(res.getCode(), bytes.length);
            httpExchange.getResponseHeaders().put("Content-Type", Collections.singletonList("text/plain"));
            httpExchange.getResponseBody().write(bytes);
            httpExchange.getResponseBody().close();
        });
        server.setExecutor(null);
        server.start();
    }
}
