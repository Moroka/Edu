package edu.monitoringSystem;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class MonitoringSystemHandlerTest {
    @Test
    public void eventsAreArrived() {
        // Send events into time window, check that everyone has arrived
        final CustomClock clock = new CustomClock();
        final IMonitoringSystemHandler handler = new MonitoringSystemHandler(clock);
        final int eventsCount = 100;
        final Random random = new Random(20);
        MonitoringSystemHelper.sendRandomEventToMonitoring(random, eventsCount, handler, clock, MonitoringSystemHandler.MILLIS_TIME_TO_KEEP.toMillis());
        MonitoringSystemHelper.printMonitoringEvents(handler.getRecentEvents());

        int arrivedEventsCount = 0;
        for (int i = 0; i < MonitoringSystemEventType.values().length; i++) {
            arrivedEventsCount += handler.getRecentEvents().get(MonitoringSystemEventType.values()[i]).size();
        }
        assertEquals(eventsCount, arrivedEventsCount);
    }

    @Test
    public void eventsAreNotArrived() {
        // Send events into time window(MILLIS_TIME_TO_KEEP), modify instant at handler, check that no one has arrived
        final CustomClock clock = new CustomClock();
        final IMonitoringSystemHandler handler = new MonitoringSystemHandler(clock);
        final Random random = new Random(20);
        MonitoringSystemHelper.sendRandomEventToMonitoring(random, 100, handler, clock, MonitoringSystemHandler.MILLIS_TIME_TO_KEEP.toMillis());
        handler.shiftInstantMs(1000);
        MonitoringSystemHelper.printMonitoringEvents(handler.getRecentEvents());

        for (int i = 0; i < MonitoringSystemEventType.values().length; i++) {
            assertEquals(handler.getRecentEvents().get(MonitoringSystemEventType.values()[i]).size(), 0);
        }
    }

    @Test
    public void onlyHalfEventsAreArrived() {
        // Send half events into time window(MILLIS_TIME_TO_KEEP), send second part of events into time(MILLIS_TIME_TO_KEEP), check that only half has arrived
        final CustomClock clock = new CustomClock();
        final IMonitoringSystemHandler handler = new MonitoringSystemHandler(clock);
        final Random random = new Random(20);
        final int eventsCount = 200;
        MonitoringSystemHelper.sendRandomEventToMonitoring(random, eventsCount / 2, handler, clock, MonitoringSystemHandler.MILLIS_TIME_TO_KEEP.toMillis());
        MonitoringSystemHelper.sendRandomEventToMonitoring(random, eventsCount / 2, handler, clock, MonitoringSystemHandler.MILLIS_TIME_TO_KEEP.toMillis());
        MonitoringSystemHelper.printMonitoringEvents(handler.getRecentEvents());

        int arrivedEventsCount = 0;
        for (int i = 0; i < MonitoringSystemEventType.values().length; i++) {
            arrivedEventsCount += handler.getRecentEvents().get(MonitoringSystemEventType.values()[i]).size();
        }
        assertEquals(eventsCount / 2, arrivedEventsCount);
    }
}
