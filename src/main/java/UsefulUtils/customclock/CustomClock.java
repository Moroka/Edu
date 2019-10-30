package usefulutils.customclock;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

public class CustomClock extends Clock {
    private Instant instant = Instant.MIN;

    @Override
    public ZoneId getZone() {
        return ZoneId.of("Europe/Moscow");
    }

    @Override
    public Clock withZone(ZoneId zone) {
        return this;
    }

    @Override
    public Instant instant() {
        return instant;
    }

    public void shiftInstant(Duration value) {
        instant = instant.plus(value);
    }
}
