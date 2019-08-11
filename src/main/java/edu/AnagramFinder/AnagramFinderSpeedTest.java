package edu.AnagramFinder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class AnagramFinderSpeedTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnagramFinderFast.class);
    private static final int MAX_TEXT_LENGTH = 1000;
    private static final int MAX_ANAGRAM_LENGTH = 100;
    private static final int ITERATION_COUNT = 10;
    private static final int BURNING_UP_ITERATION_COUNT = 10;
    private static final String STRING_PATTERN = "abcdefghijklmnopqrstuvxyz";
    private long anagramFinderTotalTime;
    private long anagramFinderFastTotalTime;

    public static void main(String[] args) {
        AnagramFinderSpeedTest speedTest = new AnagramFinderSpeedTest();
        speedTest.execute();
    }

    private void execute() {
        HashMap<String, String> hashMap = generateHashMap(ITERATION_COUNT);
        HashMap<String, String> burningUpHashMap = generateHashMap(BURNING_UP_ITERATION_COUNT);

        for (HashMap.Entry<String,String> entry : burningUpHashMap.entrySet())
            burningUpJit(entry.getKey(), entry.getValue());

        for (HashMap.Entry<String,String> entry : hashMap.entrySet())
            calculateRuntime(entry.getKey(), entry.getValue());

        printNanoTime("[AnagramFinder] Total runtime is:", anagramFinderTotalTime);
        printNanoTime("[AnagramFinder] Average runtime is:", anagramFinderTotalTime / ITERATION_COUNT);
        printNanoTime("[AnagramFinderFast] Total runtime is:", anagramFinderFastTotalTime);
        printNanoTime("[AnagramFinderFast] Average runtime is:", anagramFinderFastTotalTime / ITERATION_COUNT);
    }

    private void calculateRuntime(String anagramText, String text) {
        long nanos = System.nanoTime();
        AnagramFinder.getAnagramIndexAtText(anagramText, text);
        long duration = System.nanoTime() - nanos;
        printNanoTime("AnagramFinder runtime is:", duration);
        anagramFinderTotalTime += duration;

        nanos = System.nanoTime();
        AnagramFinderFast.getAnagramIndexAtText(anagramText, text);
        duration = System.nanoTime() - nanos;
        printNanoTime("AnagramFinderFast runtime is:", duration);
        anagramFinderFastTotalTime += duration;
    }

    private void burningUpJit(String anagramText, String text) {
        AnagramFinder.getAnagramIndexAtText(anagramText, text);
        AnagramFinderFast.getAnagramIndexAtText(anagramText, text);
    }

    private static HashMap<String, String> generateHashMap(int size) {
        HashMap<String, String> hashMap = new HashMap<>();
        for (int i = 0; i < size; i++) {
            hashMap.put(getRandomString(MAX_ANAGRAM_LENGTH), getRandomString(MAX_TEXT_LENGTH));
        }
        return hashMap;
    }

    private static String getRandomString(int maxLength) {
        int randomLength = (int) (maxLength * Math.random());
        StringBuilder s = new StringBuilder(randomLength);
        for (int i = 0; i < randomLength; i++) {
            int index = (int) (STRING_PATTERN.length() * Math.random());
            s.append(STRING_PATTERN.charAt(index));
        }
        return s.toString();
    }

    private static void printNanoTime(String prefix, long time) {
        int seconds = (int) (time / 1000000000);
        int milliseconds = (int) (time / 1000000) % 1000;
        int nanoseconds = (int) (time % 1000000);
        LOGGER.info(prefix + " {}s|{}ms|{}ns", seconds, milliseconds, nanoseconds);
    }
}
