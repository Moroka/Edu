package edu.monitoringSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class MonitoringSystemHandler implements IMonitoringSystemHandler {
    private static final int NANOSECONDS_TIME_TO_KEEP = 2;
    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoringSystemHandler.class);
    private static final Map<MonitoringSystemEventType, Queue<Instant>> hashMap = new HashMap<>();

    public MonitoringSystemHandler() {
        createEventsMaps();
    }

    public void handleEvent(MonitoringSystemEventType event) {
        final Instant timestamp = Instant.now();
        final Queue<Instant> queue = hashMap.get(event);

        removeOutdatedEvents(event, queue, timestamp);
        addEvent(event, queue, timestamp);
    }

    public void printStat() {
        LOGGER.info("----------------------------------");
        LOGGER.info("Statistics for last {} nanoseconds:", NANOSECONDS_TIME_TO_KEEP);
        final Instant timestamp = Instant.now();
        for (Map.Entry<MonitoringSystemEventType, Queue<Instant>> entry : hashMap.entrySet()) {
            removeOutdatedEvents(entry.getKey(), entry.getValue(), timestamp);
            LOGGER.info("[{}] Events count: {}", entry.getKey(), entry.getValue().size());
        }
    }

    private static void createEventsMaps() {
        for (int i = 0; i < MonitoringSystemEventType.values().length; i++) {
            hashMap.put(MonitoringSystemEventType.values()[i], new LinkedList<>());
        }
    }

    private static void removeOutdatedEvents(MonitoringSystemEventType event, Queue<Instant> queue, Instant timestamp) {
        while ((queue.size() > 0) && (timestamp.toEpochMilli() - queue.peek().toEpochMilli()) > NANOSECONDS_TIME_TO_KEEP) {
            LOGGER.info("[{}] Remove event with timestamp: {}", event.name(), queue.peek().toEpochMilli());
            queue.remove();
        }
    }

    private static void addEvent(MonitoringSystemEventType event, Queue<Instant> queue, Instant timestamp) {
        queue.add(timestamp);
        LOGGER.info("[{}] Add event with timestamp: {}", event.name(), timestamp.toEpochMilli());
    }
}