package edu.monitoringSystem;

import java.time.Instant;
import java.util.Map;
import java.util.Queue;

public interface IMonitoringSystemHandler {
    void handleEvent(MonitoringSystemEventType event);

    void shiftInstantMs(long value);

    Map<MonitoringSystemEventType, Queue<Instant>> getRecentEvents();
}
