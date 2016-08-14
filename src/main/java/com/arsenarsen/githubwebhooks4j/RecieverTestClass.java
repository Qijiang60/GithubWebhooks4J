package com.arsenarsen.githubwebhooks4j;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import static spark.Spark.port;
import static spark.Spark.post;

/**
 * Just prints out formatted payload
 * <br>
 * Created by Arsen on 14.8.2016..
 */
public class RecieverTestClass {

    public static void main(String[] args) {
        Gson format = new GsonBuilder().setPrettyPrinting().create();
        port(80);
        post("/payload", (req, res) -> {
            JsonElement parsed = new JsonParser().parse(req.body());
            System.out.println(format.toJson(parsed));
            return ":+1: OK!";
        });
    }
}
