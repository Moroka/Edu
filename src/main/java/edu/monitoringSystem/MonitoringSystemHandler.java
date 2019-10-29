package edu.monitoringSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class MonitoringSystemHandler implements IMonitoringSystemHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoringSystemHandler.class);

    private final Duration timeToKeep;
    private final Map<MonitoringSystemEventType, Queue<Instant>> hashMap = new EnumMap<>(MonitoringSystemEventType.class);
    private final Clock clock;

    public MonitoringSystemHandler(Clock clock, Duration timeToKeep) {
        this.clock = clock;
        this.timeToKeep = timeToKeep;

        createEventsMaps();
    }

    public void handleEvent(MonitoringSystemEventType event) {
        final Instant timestamp = clock.instant();
        final Queue<Instant> queue = hashMap.get(event);

        removeOutdatedEvents(event, queue, timestamp);
        addEvent(event, queue, timestamp);
    }

    public Map<MonitoringSystemEventType, Queue<Instant>> getRecentEvents() {
        final Instant timestamp = clock.instant();
        for (Map.Entry<MonitoringSystemEventType, Queue<Instant>> entry : hashMap.entrySet()) {
            removeOutdatedEvents(entry.getKey(), entry.getValue(), timestamp);
        }

        final Map<MonitoringSystemEventType, Queue<Instant>> recentEvents = new EnumMap<>(MonitoringSystemEventType.class);

        for (Map.Entry<MonitoringSystemEventType, Queue<Instant>> entry : hashMap.entrySet()) {
            final Queue<Instant> queue = new LinkedList<>(entry.getValue());
            recentEvents.put(entry.getKey(), queue);
        }

        return recentEvents;
    }

    public Duration getTimeToKeep() {
        return timeToKeep;
    }

    private void createEventsMaps() {
        for (int i = 0; i < MonitoringSystemEventType.values().length; i++) {
            hashMap.put(MonitoringSystemEventType.values()[i], new LinkedList<>());
        }
    }

    private void removeOutdatedEvents(MonitoringSystemEventType event, Queue<Instant> queue, Instant timestamp) {
        while ((queue.size() > 0) && (Duration.between(queue.peek(), timestamp).compareTo(timeToKeep) > 0)) {
            LOGGER.info("[{}] Remove event with timestamp: {}", event.name(), queue.peek());
            queue.remove();
        }
    }

    private void addEvent(MonitoringSystemEventType event, Queue<Instant> queue, Instant timestamp) {
        queue.add(timestamp);
        LOGGER.info("[{}] Add event with timestamp: {}", event.name(), timestamp);
    }
}