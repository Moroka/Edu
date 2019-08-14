package edu.AnagramFinder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public final class AnagramFinder {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnagramFinderFast.class);

    public static int getAnagramIndexAtText(String anagramText, String text) {
        if (anagramText.length() > text.length() || anagramText.length() == 0)
            return -1;

        if (LOGGER.isDebugEnabled())
            LOGGER.debug("Find anagram: '{}' at text: {}", anagramText, text);

        final HashMap<Character, Integer> anagram = getHashMap(anagramText);

        for (int i = 0; i <= text.length() - anagramText.length(); i++) {
            final String slicedString = text.substring(i, i + anagramText.length());
            if (anagram.hashCode() == getHashMap(slicedString).hashCode()) {
                return i;
            }
        }

        return -1;
    }

    private static HashMap<Character, Integer> getHashMap(String text) {
        final HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            if (hashMap.containsKey(text.charAt(i))) {
                hashMap.put(text.charAt(i), hashMap.get(text.charAt(i)) + 1);
            } else {
                hashMap.put(text.charAt(i), 1);
            }
        }
        return hashMap;
    }
}
