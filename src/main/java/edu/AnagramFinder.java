package edu;

import java.util.HashMap;

public class AnagramFinder {
    public static int getAnagramIndexAtText(String anagramText, String text) {
        if (anagramText.length() > text.length() || anagramText.length() == 0)
            return -1;

        System.out.printf("Find anagram: '%s' at text: %s%n", anagramText, text);

        HashMap<Character, Integer> anagram = getHashMap(anagramText);
        System.out.printf("Anagram hash map is: %s Hash code is: %d%n", anagram, anagram.hashCode());

        for (int i = 0; i <= text.length() - anagramText.length(); i++) {
            String slicedString = text.substring(i, i + anagramText.length());
            if (anagram.hashCode() == getHashMap(slicedString).hashCode()) {
                System.out.printf("Equal hash code found at index: %d as '%s'%n", i, slicedString);
                return i;
            }
        }

        return -1;
    }

    private static HashMap<Character, Integer> getHashMap(String text) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            if (hashMap.containsKey(text.charAt(i))){
                hashMap.put(text.charAt(i), hashMap.get(text.charAt(i)) + 1);
            } else {
                hashMap.put(text.charAt(i), 1);
            }
        }
        return hashMap;
    }
}
