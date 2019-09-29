package edu.monitoringSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class MonitoringSystemHandler implements IMonitoringSystemHandler {
    private static final int NANOSECONDS_TIME_TO_KEEP = 2;

    private static final String HIGH_LOAD_EVENTS = "[HIGH_LOAD]";
    private static final String ERROR_EVENTS = "[ERROR]";
    private static final String CONNECTION_REFUSED_EVENTS = "[CONNECTION_REFUSED_EVENTS]";

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoringSystemHandler.class);

    private static final Queue<Timestamp> highLoadEvents = new LinkedList<>();
    private static final Queue<Timestamp> errorEvents = new LinkedList<>();
    private static final Queue<Timestamp> connectionRefusedEvents = new LinkedList<>();

    public MonitoringSystemHandler() {
    }

    public void handleEvent(MonitoringSystemEventType event) {
        final Date now = new Date();
        final Timestamp timestamp = new Timestamp(now.getTime());

        if (event == MonitoringSystemEventType.HIGH_LOAD) {
            removeOutdatedEvents(HIGH_LOAD_EVENTS, highLoadEvents, timestamp);
            highLoadEvents.add(timestamp);
            LOGGER.info("{} Add event with timestamp: {}", HIGH_LOAD_EVENTS, timestamp.getTime());
        } else if (event == MonitoringSystemEventType.ERROR) {
            removeOutdatedEvents(ERROR_EVENTS, errorEvents, timestamp);
            errorEvents.add(timestamp);
            LOGGER.info("{} Add event with timestamp: {}", ERROR_EVENTS, timestamp.getTime());
        } else {
            removeOutdatedEvents(CONNECTION_REFUSED_EVENTS, connectionRefusedEvents, timestamp);
            connectionRefusedEvents.add(timestamp);
            LOGGER.info("{} Add event with timestamp: {}", CONNECTION_REFUSED_EVENTS, timestamp.getTime());
        }
    }

    public void printStat() {
        LOGGER.info("----------------------------------");
        LOGGER.info("Statistics for last {} nanoseconds", NANOSECONDS_TIME_TO_KEEP);
        LOGGER.info("{} Events count: {}", HIGH_LOAD_EVENTS, highLoadEvents.size());
        LOGGER.info("{} Events count: {}", ERROR_EVENTS, errorEvents.size());
        LOGGER.info("{} Events count: {}", CONNECTION_REFUSED_EVENTS, connectionRefusedEvents.size());
    }

    private static void removeOutdatedEvents(String queueDescriptor, Queue<Timestamp> queue, Timestamp timestamp) {
        while ((queue.size() > 0) && (timestamp.getTime() - queue.peek().getTime()) > NANOSECONDS_TIME_TO_KEEP) {
            LOGGER.info("{} Remove event with timestamp: {}", queueDescriptor, queue.peek().getTime());
            queue.remove();
        }
    }
}