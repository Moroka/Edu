package edu.monitoringSystem;

import org.junit.Test;

public class MonitoringSystemHandlerTest {
    @Test
    public void anagramAtBeginningOfText() {
        final MonitoringSystemHandler handler = new MonitoringSystemHandler();
        MonitoringSystemEventDispatcher.sendRandomEventToMonitoring(2000, handler);
        handler.printStat();
    }
}
