package com.arsenarsen.githubwebhooks4j;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.Iterator;

public enum Markers implements Marker {
    /**
     * Zen lookup marker
     */
    ZEN,
    /**
     * The API marker that logs everything from the EventHandler
     */
    HANDLER;

    private final Marker parent;

    Markers(Markers... parent) {
        this();
        for (Marker m : parent)
            add(m);
    }

    Markers() {
        this.parent = MarkerFactory.getMarker(getClass().getSimpleName() + '.' + name());
    }

    @Override
    public String getName() {
        return parent.getName();
    }

    @Override
    public void add(Marker reference) {
        parent.add(reference);
    }

    @Override
    public boolean remove(Marker reference) {
        return parent.remove(reference);
    }

    @Override
    public boolean hasChildren() {
        //noinspection deprecation
        return parent.hasChildren();
    }

    @Override
    public boolean hasReferences() {
        return parent.hasReferences();
    }

    @Override
    public Iterator<Marker> iterator() {
        return parent.iterator();
    }

    @Override
    public boolean contains(Marker other) {
        return parent.contains(other);
    }

    @Override
    public boolean contains(String name) {
        return parent.contains(name);
    }
}
