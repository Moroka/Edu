package edu.monitoringSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import usefulutils.customclock.CustomClock;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

public class MonitoringSystemHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoringSystemHelper.class);

    public static void sendRandomEventToMonitoring(Random random, int count, IMonitoringSystemHandler monitoringInstance, CustomClock clock, Duration execTime) {
        for (int i = 0; i < count; i++) {
            final int eventType = random.nextInt(MonitoringSystemEventType.values().length);
            monitoringInstance.handleEvent(MonitoringSystemEventType.values()[eventType]);
            clock.shiftInstant(execTime.dividedBy(count));
        }
    }

    public static void printMonitoringEvents(IMonitoringSystemHandler monitoringInstance) {
        LOGGER.info("Statistics for last {} milliseconds:", monitoringInstance.getTimeToKeep().toMillis());
        LOGGER.info("----------------------------------");
        for (Map.Entry<MonitoringSystemEventType, Queue<Instant>> entry : monitoringInstance.getRecentEvents().entrySet()) {
            LOGGER.info("[{}] Events count: {}", entry.getKey(), entry.getValue().size());
        }
    }
}



