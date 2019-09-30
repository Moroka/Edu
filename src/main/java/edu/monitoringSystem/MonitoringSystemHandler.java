package edu.monitoringSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.*;

public class MonitoringSystemHandler implements IMonitoringSystemHandler {
    private static final int NANOSECONDS_TIME_TO_KEEP = 2;
    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoringSystemHandler.class);
    private static final Map<String, Queue<Timestamp>> hashMap = new HashMap<>();

    public MonitoringSystemHandler() {
    }

    public void handleEvent(MonitoringSystemEventType event) {
        final Date now = new Date();
        final Timestamp timestamp = new Timestamp(now.getTime());

        Queue<Timestamp> events = hashMap.get(event.name());
        if (events != null)
            hashMap.put(event.name(), addEvent(event.name(), events, timestamp));
        else {
            Queue<Timestamp> queue = new LinkedList<>();
            hashMap.put(event.name(), addEvent(event.name(), queue, timestamp));
        }
    }

    public void printStat() {
        LOGGER.info("----------------------------------");
        LOGGER.info("Statistics for last {} nanoseconds:", NANOSECONDS_TIME_TO_KEEP);
        for (Map.Entry<String, Queue<Timestamp>> entry : hashMap.entrySet()) {
            LOGGER.info("[{}] Events count: {}", entry.getKey(), entry.getValue().size());
        }
    }

    private static Queue<Timestamp> addEvent(String eventDescriptor, Queue<Timestamp> queue, Timestamp timestamp) {
        removeOutdatedEvents(eventDescriptor, queue, timestamp);
        queue.add(timestamp);
        LOGGER.info("[{}] Add event with timestamp: {}", eventDescriptor, timestamp.getTime());
        return queue;
    }

    private static void removeOutdatedEvents(String eventDescriptor, Queue<Timestamp> queue, Timestamp timestamp) {
        while ((queue.size() > 0) && (timestamp.getTime() - queue.peek().getTime()) > NANOSECONDS_TIME_TO_KEEP) {
            LOGGER.info("[{}] Remove event with timestamp: {}", eventDescriptor, queue.peek().getTime());
            queue.remove();
        }
    }
}