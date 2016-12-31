package com.arsenarsen.githubwebhooks4j.objects;

/**
 * Represents a ref type for the CreateEvent
 */
public enum RefType {
    /**
     * Repository ref type.
     */
    REPOSITORY,

    /**
     * Branch ref type.
     */
    BRANCH,

    /**
     * Tag ref type.
     */
    TAG;

    /**
     * Gets a ref, or null, from a string, without throwing exceptions, non case sensitive.
     *
     * @param ref The ref as a string.
     * @return The ref constant.
     */
    public static RefType getRef(String ref) {
        for (RefType constant : values()) {
            if (ref.equalsIgnoreCase(constant.toString()))
                return constant;
        }
        return null;
    }

}
