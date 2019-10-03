package edu.monitoringSystem;

import org.junit.Test;

import java.time.Instant;
import java.util.Map;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

public class MonitoringSystemHandlerTest {
    @Test
    public void monitoringSystemRun() {
        final IMonitoringSystemHandler handler = new MonitoringSystemHandler();
        MonitoringSystemHelper.sendRandomEventToMonitoring(2000, handler);
        MonitoringSystemHelper.printMonitoringEvents(handler.getRecentEvents());
    }

    @Test
    public void monitoringSystemGetDelayedStat() {
        final IMonitoringSystemHandler handler = new MonitoringSystemHandler();
        MonitoringSystemHelper.sendRandomEventToMonitoring(2000, handler);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        final Map<MonitoringSystemEventType, Queue<Instant>> recentEvents = handler.getRecentEvents();
        MonitoringSystemHelper.printMonitoringEvents(recentEvents);

        for (int i = 0; i < MonitoringSystemEventType.values().length; i++) {
            assertEquals(recentEvents.get(MonitoringSystemEventType.values()[i]).size(), 0);
        }

    }
}
