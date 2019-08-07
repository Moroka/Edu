package edu.AnagramFinder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnagramFinderFast {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnagramFinderFast.class);

    public static int getAnagramIndexAtText(String anagramText, String text) {
        if (anagramText.length() == 0)
            return 0;
        if (anagramText.length() > text.length())
            return -1;

        LOGGER.debug("Find anagram: '{}' at text: {}", anagramText, text);

        CharIntHashMap anagram = getHashMap(anagramText);
        LOGGER.debug("Reference anagram hash map is: {}", anagram);

        for (int i = 0; i < anagramText.length(); i++) {
            anagram.subtractCharCount(text.charAt(i));
        }

        if (anagram.isEmpty())
            return 0;

        for (int j = 0; j <= text.length() - anagramText.length() - 1; j++) {
            anagram.addCharCount(text.charAt(j));
            anagram.subtractCharCount(text.charAt(j + anagramText.length()));

            if (anagram.isEmpty())
                return j + 1;
        }

        return -1;

    }

    private static CharIntHashMap getHashMap(String anagram) {
        CharIntHashMap hashMap = new CharIntHashMap();
        for (int i = 0; i < anagram.length(); i++) {
            if (hashMap.containsKey(anagram.charAt(i))){
                hashMap.put(anagram.charAt(i), hashMap.get(anagram.charAt(i)) + 1);
            } else {
                hashMap.put(anagram.charAt(i), 1);
            }
        }
        return hashMap;
    }


}
