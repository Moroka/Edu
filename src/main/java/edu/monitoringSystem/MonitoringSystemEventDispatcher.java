package edu.monitoringSystem;

import java.util.Random;

public class MonitoringSystemEventDispatcher {
    public static void sendRandomEventToMonitoring(int count, IMonitoringSystemHandler monitoringInstance) {
        final Random random = new Random();
        for (int i = 0; i < count; i++) {
            final int eventType = random.nextInt(MonitoringSystemEventType.values().length);
            monitoringInstance.handleEvent(MonitoringSystemEventType.values()[eventType]);
        }
    }
}


