package edu.monitoringSystem;

import org.junit.Test;
import usefulutils.customclock.CustomClock;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class MonitoringSystemHandlerTest {
    @Test
    public void eventsAreArrived() {
        // Send events into time window, check that everyone has arrived
        final CustomClock clock = new CustomClock();
        final IMonitoringSystemHandler handler = new MonitoringSystemHandler(clock, Duration.ofSeconds(1));
        final int eventsCount = 20;

        for (int i = 0; i < MonitoringSystemEventType.values().length; i++) {
            for (int j = 0; j < eventsCount; j++) {
                handler.handleEvent(MonitoringSystemEventType.values()[i]);
            }
        }

        for (int i = 0; i < MonitoringSystemEventType.values().length; i++) {
            assertEquals(eventsCount, handler.getRecentEvents().get(MonitoringSystemEventType.values()[i]).size());
        }
    }

    @Test
    public void eventsAreArrivedFixedRandom() {
        // Send events into time window, check that right amount of each type events has arrived
        final CustomClock clock = new CustomClock();
        final IMonitoringSystemHandler handler = new MonitoringSystemHandler(clock, Duration.ofSeconds(1));
        final int eventsCount = 100;
        final Random random = new Random(20);
        MonitoringSystemHelper.sendRandomEventToMonitoring(random, eventsCount, handler, clock, Duration.ofSeconds(1));
        MonitoringSystemHelper.printMonitoringEvents(handler);

        for (Map.Entry<MonitoringSystemEventType, Queue<Instant>> entry : handler.getRecentEvents().entrySet()) {
            if (entry.getKey() == MonitoringSystemEventType.HIGH_LOAD)
                assertEquals(entry.getValue().size(), 37);
            else if (entry.getKey() == MonitoringSystemEventType.ERROR)
                assertEquals(entry.getValue().size(), 32);
            else if (entry.getKey() == MonitoringSystemEventType.CONNECTION_REFUSED)
                assertEquals(entry.getValue().size(), 31);
            else
                assertEquals(0, 1);
        }
    }

    @Test
    public void eventsAreNotArrived() {
        // Send events into time window, modify instant at clock, check that no one has arrived
        final CustomClock clock = new CustomClock();
        final IMonitoringSystemHandler handler = new MonitoringSystemHandler(clock, Duration.ofSeconds(1));
        final Random random = new Random(20);
        MonitoringSystemHelper.sendRandomEventToMonitoring(random, 100, handler, clock, Duration.ofSeconds(1));
        clock.shiftInstant(Duration.ofSeconds(1));
        MonitoringSystemHelper.printMonitoringEvents(handler);

        for (int i = 0; i < MonitoringSystemEventType.values().length; i++) {
            assertEquals(handler.getRecentEvents().get(MonitoringSystemEventType.values()[i]).size(), 0);
        }
    }

    @Test
    public void onlyHalfEventsAreArrived() {
        // Send half events into time window, send second part of events into time, check that only half has arrived
        final CustomClock clock = new CustomClock();
        final IMonitoringSystemHandler handler = new MonitoringSystemHandler(clock, Duration.ofSeconds(1));
        final Random random = new Random(20);
        final int eventsCount = 200;
        MonitoringSystemHelper.sendRandomEventToMonitoring(random, eventsCount / 2, handler, clock, Duration.ofSeconds(1));
        MonitoringSystemHelper.sendRandomEventToMonitoring(random, eventsCount / 2, handler, clock, Duration.ofSeconds(1));
        MonitoringSystemHelper.printMonitoringEvents(handler);

        int arrivedEventsCount = 0;
        for (int i = 0; i < MonitoringSystemEventType.values().length; i++) {
            arrivedEventsCount += handler.getRecentEvents().get(MonitoringSystemEventType.values()[i]).size();
        }
        assertEquals(eventsCount / 2, arrivedEventsCount);
    }
}
