package edu.monitoringSystem;

public interface IMonitoringSystemHandler {
    void handleEvent(MonitoringSystemEventType event);
    void printStat();
}
