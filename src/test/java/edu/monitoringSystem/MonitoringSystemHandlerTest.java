package edu.monitoringSystem;

import org.junit.Test;

import java.time.Instant;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

public class MonitoringSystemHandlerTest {
    @Test
    public void monitoringSystemRun() {
        final IMonitoringSystemHandler handler = new MonitoringSystemHandler();
        MonitoringSystemHelper.sendRandomEventToMonitoring(20, 2000, handler);
        MonitoringSystemHelper.printMonitoringEvents(handler.getRecentEvents());
    }

    @Test
    public void monitoringSystemGetDelayedStat() throws InterruptedException {
        final IMonitoringSystemHandler handler = new MonitoringSystemHandler();
        MonitoringSystemHelper.sendRandomEventToMonitoring(20, 2000, handler);
        Thread.sleep(1000);

        final Map<MonitoringSystemEventType, Queue<Instant>> recentEvents = new EnumMap<>(MonitoringSystemEventType.class);

        for (Map.Entry<MonitoringSystemEventType, Queue<Instant>> entry : handler.getRecentEvents().entrySet()) {
            final Queue<Instant> queue = new LinkedList<>(entry.getValue());
            recentEvents.put(entry.getKey(), queue);
        }

        MonitoringSystemHelper.printMonitoringEvents(recentEvents);

        for (int i = 0; i < MonitoringSystemEventType.values().length; i++) {
            assertEquals(recentEvents.get(MonitoringSystemEventType.values()[i]).size(), 0);
        }
    }
}
