package edu.monitoringSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

public class MonitoringSystemHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoringSystemHelper.class);

    public static void sendRandomEventToMonitoring(Random random, int count, IMonitoringSystemHandler monitoringInstance, CustomClock clock, long execTime) {
        for (int i = 0; i < count; i++) {
            final int eventType = random.nextInt(MonitoringSystemEventType.values().length);
            monitoringInstance.handleEvent(MonitoringSystemEventType.values()[eventType]);
            clock.shiftInstantMs(execTime / count);
        }
    }

    public static void printMonitoringEvents(Map<MonitoringSystemEventType, Queue<Instant>> recentEvents) {
        LOGGER.info("----------------------------------");
        LOGGER.info("Statistics for last {} nanoseconds:", MonitoringSystemHandler.MILLIS_TIME_TO_KEEP.toMillis());
        for (Map.Entry<MonitoringSystemEventType, Queue<Instant>> entry : recentEvents.entrySet()) {
            LOGGER.info("[{}] Events count: {}", entry.getKey(), entry.getValue().size());
        }
    }
}



