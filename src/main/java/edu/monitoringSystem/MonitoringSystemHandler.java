package edu.monitoringSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class MonitoringSystemHandler implements IMonitoringSystemHandler {
    static final Duration NANOSECONDS_TIME_TO_KEEP = Duration.ofNanos(20);
    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoringSystemHandler.class);
    private static final Map<MonitoringSystemEventType, Queue<Instant>> hashMap = new EnumMap<>(MonitoringSystemEventType.class);

    public MonitoringSystemHandler() {
        createEventsMaps();
    }

    public void handleEvent(MonitoringSystemEventType event) {
        final Instant timestamp = Instant.now();
        final Queue<Instant> queue = hashMap.get(event);

        removeOutdatedEvents(event, queue, timestamp);
        addEvent(event, queue, timestamp);
    }

    public Map<MonitoringSystemEventType, Queue<Instant>> getRecentEvents() {
        final Instant timestamp = Instant.now();
        for (Map.Entry<MonitoringSystemEventType, Queue<Instant>> entry : hashMap.entrySet()) {
            removeOutdatedEvents(entry.getKey(), entry.getValue(), timestamp);
        }

        final Map<MonitoringSystemEventType, Queue<Instant>> recentEvents = new EnumMap<>(MonitoringSystemEventType.class);
        recentEvents.putAll(hashMap);

        return recentEvents;
    }

    private static void createEventsMaps() {
        for (int i = 0; i < MonitoringSystemEventType.values().length; i++) {
            hashMap.put(MonitoringSystemEventType.values()[i], new LinkedList<>());
        }
    }

    private static void removeOutdatedEvents(MonitoringSystemEventType event, Queue<Instant> queue, Instant timestamp) {
        while ((queue.size() > 0) && (Duration.between(queue.peek(), timestamp).toNanos() > NANOSECONDS_TIME_TO_KEEP.toNanos())) {
            LOGGER.info("[{}] Remove event with timestamp: {}", event.name(), queue.peek().toEpochMilli());
            queue.remove();
        }
    }

    private static void addEvent(MonitoringSystemEventType event, Queue<Instant> queue, Instant timestamp) {
        queue.add(timestamp);
        LOGGER.info("[{}] Add event with timestamp: {}", event.name(), timestamp.toEpochMilli());
    }
}