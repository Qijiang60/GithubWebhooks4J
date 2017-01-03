package com.arsenarsen.githubwebhooks4j.utils;

import com.arsenarsen.githubwebhooks4j.GithubWebhooks4J;
import com.arsenarsen.githubwebhooks4j.Markers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Some utilities
 * <br>
 * Created by Arsen on 31.8.2016.
 */
public class Utils {

    /**
     * Makes a ZonedDateTime
     *
     * @param stamp The stamp to convert
     * @return ZonedDateTime, or null, if there was an error
     */
    public static ZonedDateTime parseTimeStamp(String stamp) {
        if (stamp == null)
            return null;
        try {
            return LocalDateTime.parse(stamp.substring(0, 19)).atZone(ZoneId.of(stamp.substring(19)));
        } catch (Exception e) {
            GithubWebhooks4J.GHWHLOGGER.error(Markers.UTILS, "Could not parse timestamp '{}'! Please send this message to ArsenArsen",
                    stamp);
            GithubWebhooks4J.GHWHLOGGER.error(null, e);
        }
        return null;
    }

    /**
     * Uppercase all keys in a map
     *
     * @param map The map whose keys to uppercase
     * @param <V> Automatically inferred
     * @return The map after the keys are uppercase
     */
    public static <V> Map<String, V> uppercaseKeys(Map<String, V> map) {
        Map<String, V> uppercased = new HashMap<>();
        map.entrySet().forEach(e -> uppercased.put(e.getKey().toUpperCase(), e.getValue()));
        return uppercased;
    }
}
