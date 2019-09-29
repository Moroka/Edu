package edu.monitoringSystem;

import org.junit.Test;

public class MonitoringSystemHandlerTest {
    @Test
    public void anagramAtBeginningOfText() {
        final IMonitoringSystemHandler handler = new MonitoringSystemHandler();
        MonitoringSystemEventDispatcher.sendRandomEventToMonitoring(2000, handler);
        handler.printStat();
    }
}
