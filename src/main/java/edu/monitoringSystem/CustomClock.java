package edu.monitoringSystem;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class CustomClock extends Clock {
    private Instant instant = Instant.MIN;

    @Override
    public ZoneId getZone() {
        return null;
    }

    @Override
    public Clock withZone(ZoneId zone) {
        return null;
    }

    @Override
    public Instant instant() {
        return instant;
    }

    public void shiftInstantMs(long value) {
        instant = instant.plus(value, ChronoUnit.MILLIS);
    }
}
