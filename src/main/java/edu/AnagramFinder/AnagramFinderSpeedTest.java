package edu.AnagramFinder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

public final class AnagramFinderSpeedTest {
    private final Random random = new Random();
    private static final Logger LOGGER = LoggerFactory.getLogger(AnagramFinderSpeedTest.class);
    private static final int MAX_TEXT_LENGTH = 0x10000;
    private static final int MAX_ANAGRAM_LENGTH = 0x100;
    private static final int ITERATION_COUNT = 0x1000;
    private static final int WARM_UP_ITERATION_COUNT = 0x100;

    public static void main(String[] args) {
        AnagramFinderSpeedTest speedTest = new AnagramFinderSpeedTest();
        speedTest.execute();
    }

    private void execute() {
        final AnagramFinderSpeedTestData[] testData = generateTestData(ITERATION_COUNT);
        final AnagramFinderSpeedTestData[] warmUpData = generateTestData(WARM_UP_ITERATION_COUNT);

        // Warm up 1st algorithm
        Arrays.stream(warmUpData).forEach(data -> AnagramFinder.getAnagramIndexAtText(data.anagramText, data.text));

        // Check 1st algorithm
        final Duration anagramFinderTotalTime = Arrays.stream(testData)
                .map((data) -> measureTime(() -> AnagramFinder.getAnagramIndexAtText(data.anagramText, data.text)))
                .reduce(Duration.ZERO, Duration::plus);

        // Warm up 2nd algorithm
        Arrays.stream(warmUpData).forEach(data -> AnagramFinderFast.getAnagramIndexAtText(data.anagramText, data.text));

        // Check 2nd algorithm
        final Duration anagramFinderFastTotalTime = Arrays.stream(testData)
                .map((data) -> measureTime(() -> AnagramFinder.getAnagramIndexAtText(data.anagramText, data.text)))
                .reduce(Duration.ZERO, Duration::plus);

        printMeasureTime("[AnagramFinder] Total runtime is:", anagramFinderTotalTime);
        printMeasureTime("[AnagramFinder] Average runtime is:", anagramFinderTotalTime.dividedBy(ITERATION_COUNT));
        printMeasureTime("[AnagramFinderFast] Total runtime is:", anagramFinderFastTotalTime);
        printMeasureTime("[AnagramFinderFast] Average runtime is:", anagramFinderFastTotalTime.dividedBy(ITERATION_COUNT));
    }

    private AnagramFinderSpeedTestData[] generateTestData(int size) {
        final AnagramFinderSpeedTestData[] dataArray = new AnagramFinderSpeedTestData[size];
        for (int i = 0; i < size; i++) {
            dataArray[i] = new AnagramFinderSpeedTestData(getRandomString(MAX_ANAGRAM_LENGTH), getRandomString(MAX_TEXT_LENGTH));
        }
        return dataArray;
    }

    private String getRandomString(int maxLength) {
        final int randomLength = random.nextInt(maxLength);
        final StringBuilder s = new StringBuilder(randomLength);
        for (int i = 0; i < randomLength; i++) {
            s.append((char) ('a' + random.nextInt(26)));
        }
        return s.toString();
    }

    private static Duration measureTime(Runnable action) {
        final Instant startTime = Instant.now();
        action.run();
        return Duration.between(startTime, Instant.now());
    }

    private static void printMeasureTime(String prefix, Duration time) {
        final int seconds = (int) time.toMinutes() / 60;
        final int milliseconds = (int) time.toMillis();
        final int nanoseconds = (int) time.toNanos();
        LOGGER.info("{} {}s|{}ms|{}ns", prefix, seconds, milliseconds, nanoseconds);
    }
}
