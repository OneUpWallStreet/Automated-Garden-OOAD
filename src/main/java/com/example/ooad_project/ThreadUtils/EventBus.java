package com.example.ooad_project.ThreadUtils;

import java.util.*;
import java.util.function.Consumer;

public class EventBus {
    private static final Map<String, List<Consumer<Object>>> listeners = new HashMap<>();

    public static synchronized void subscribe(String eventType, Consumer<Object> listener) {
        listeners.putIfAbsent(eventType, new ArrayList<>());
        listeners.get(eventType).add(listener);
    }

    public static void publish(String eventType, Object event) {
        List<Consumer<Object>> eventListeners = listeners.getOrDefault(eventType, Collections.emptyList());
        eventListeners.forEach(listener -> listener.accept(event));
    }
}
