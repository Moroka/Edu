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
    static final Duration MILLIS_TIME_TO_KEEP = Duration.ofMillis(1000);
    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoringSystemHandler.class);
    private final Map<MonitoringSystemEventType, Queue<Instant>> hashMap = new EnumMap<>(MonitoringSystemEventType.class);
    private final CustomClock clock;

    public MonitoringSystemHandler(CustomClock clock) {
        this.clock = clock;
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

    public void shiftInstantMs(long value) {
        clock.shiftInstantMs(value);
    }

    private void createEventsMaps() {
        for (int i = 0; i < MonitoringSystemEventType.values().length; i++) {
            hashMap.put(MonitoringSystemEventType.values()[i], new LinkedList<>());
        }
    }

    private static void removeOutdatedEvents(MonitoringSystemEventType event, Queue<Instant> queue, Instant timestamp) {
        while ((queue.size() > 0) && (Duration.between(queue.peek(), timestamp).compareTo(MILLIS_TIME_TO_KEEP) > 0)) {
            LOGGER.info("[{}] Remove event with timestamp: {}", event.name(), queue.peek());
            queue.remove();
        }
    }

    private static void addEvent(MonitoringSystemEventType event, Queue<Instant> queue, Instant timestamp) {
        queue.add(timestamp);
        LOGGER.info("[{}] Add event with timestamp: {}", event.name(), timestamp);
    }
}