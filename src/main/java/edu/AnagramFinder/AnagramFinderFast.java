package edu.AnagramFinder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class AnagramFinderFast {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnagramFinderFast.class);

    public static int getAnagramIndexAtText(String anagramText, String text) {
        if (anagramText.length() == 0)
            return 0;
        if (anagramText.length() > text.length())
            return -1;

        LOGGER.debug("Find anagram: '{}' at text: {}", anagramText, text);

        CharCounters charCounters = new CharCounters(anagramText);
        HashMap<Character, Integer> anagram = charCounters.getHashMap();

        for (int i = 0; i < anagramText.length(); i++) {
            charCounters.subtractCharCount(text.charAt(i));
        }

        if (anagram.isEmpty())
            return 0;

        for (int j = 0; j <= text.length() - anagramText.length() - 1; j++) {
            charCounters.addCharCount(text.charAt(j));
            charCounters.subtractCharCount(text.charAt(j + anagramText.length()));

            if (anagram.isEmpty())
                return j + 1;
        }

        return -1;

    }
}
