package edu.AnagramFinder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class AnagramFinderSpeedTest {
    private static final Random random = new Random();
    private static final Logger LOGGER = LoggerFactory.getLogger(AnagramFinderSpeedTest.class);
    private static final int MAX_TEXT_LENGTH = 0x10000;
    private static final int MAX_ANAGRAM_LENGTH = 0x100;
    private static final int ITERATION_COUNT = 0x1000;
    private static final int WARM_UP_ITERATION_COUNT = 0x100;
    private long anagramFinderTotalTime;
    private long anagramFinderFastTotalTime;

    public static void main(String[] args) {
        AnagramFinderSpeedTest speedTest = new AnagramFinderSpeedTest();
        speedTest.execute();
    }

    private void execute() {
        final AnagramFinderSpeedTestData[] testData = generateTestData(ITERATION_COUNT);
        final AnagramFinderSpeedTestData[] warmUpData = generateTestData(WARM_UP_ITERATION_COUNT);

        // Warm up 1st algorithm
        for (AnagramFinderSpeedTestData data : warmUpData) {
            AnagramFinder.getAnagramIndexAtText(data.anagramText, data.text);
        }

        // Check 1st algorithm
        for (AnagramFinderSpeedTestData data : testData) {
            anagramFinderTotalTime += measureTime(() -> AnagramFinder.getAnagramIndexAtText(data.anagramText, data.text));
        }

        // Warm up 2nd algorithm
        for (AnagramFinderSpeedTestData data : warmUpData) {
            AnagramFinderFast.getAnagramIndexAtText(data.anagramText, data.text);
        }

        // Check 2nd algorithm
        for (AnagramFinderSpeedTestData data : testData) {
            anagramFinderFastTotalTime += measureTime(() -> AnagramFinderFast.getAnagramIndexAtText(data.anagramText, data.text));
        }

        printMeasureTime("[AnagramFinder] Total runtime is:", anagramFinderTotalTime);
        printMeasureTime("[AnagramFinder] Average runtime is:", anagramFinderTotalTime / ITERATION_COUNT);
        printMeasureTime("[AnagramFinderFast] Total runtime is:", anagramFinderFastTotalTime);
        printMeasureTime("[AnagramFinderFast] Average runtime is:", anagramFinderFastTotalTime / ITERATION_COUNT);
    }

    private static AnagramFinderSpeedTestData[] generateTestData(int size) {
        final AnagramFinderSpeedTestData[] dataArray = new AnagramFinderSpeedTestData[size];
        for (int i = 0; i < size; i++) {
            dataArray[i] = new AnagramFinderSpeedTestData(getRandomString(MAX_ANAGRAM_LENGTH), getRandomString(MAX_TEXT_LENGTH));
        }
        return dataArray;
    }

    private static String getRandomString(int maxLength) {
        int randomLength = random.nextInt(maxLength);
        StringBuilder s = new StringBuilder(randomLength);
        for (int i = 0; i < randomLength; i++) {
            s.append((char) ('a' + random.nextInt(26)));
        }
        return s.toString();
    }

    private static long measureTime(Runnable action) {
        long nanos = System.nanoTime();
        action.run();
        return System.nanoTime() - nanos;
    }

    private static void printMeasureTime(String prefix, long time) {
        int seconds = (int) (time / 1000000000);
        int milliseconds = (int) (time / 1000000) % 1000;
        int nanoseconds = (int) (time % 1000000);
        LOGGER.info("{} {}s|{}ms|{}ns", prefix, seconds, milliseconds, nanoseconds);
    }
}
