package edu.monitoringSystem;

import java.util.Random;

public class MonitoringSystemEventDispatcher {


    public static void sendRandomEventToMonitoring(int count, MonitoringSystemHandler monitoringInstance) {
        final Random random = new Random();
        for (int i = 0; i < count; i++) {
            final int eventType = random.nextInt(3);
            switch (eventType) {
                case 0: {
                    monitoringInstance.handleEvent(MonitoringSystemEventType.HIGH_LOAD);
                    break;
                }
                case 1: {
                    monitoringInstance.handleEvent(MonitoringSystemEventType.ERROR);
                    break;
                }
                case 2: {
                    monitoringInstance.handleEvent(MonitoringSystemEventType.CONNECTION_REFUSED);
                    break;
                }
            }
        }
    }
}


