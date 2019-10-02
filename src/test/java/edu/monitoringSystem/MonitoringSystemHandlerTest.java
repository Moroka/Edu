package edu.monitoringSystem;

import org.junit.Test;

public class MonitoringSystemHandlerTest {
    @Test
    public void monitoringSystemRun() {
        final IMonitoringSystemHandler handler = new MonitoringSystemHandler();
        MonitoringSystemEventDispatcher.sendRandomEventToMonitoring(2000, handler);
        handler.printStat();
    }

    @Test
    public void monitoringSystemGetDelayedStat() {
        final IMonitoringSystemHandler handler = new MonitoringSystemHandler();
        MonitoringSystemEventDispatcher.sendRandomEventToMonitoring(2000, handler);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        handler.printStat();
    }
}
