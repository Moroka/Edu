package edu;

import java.util.HashMap;

public class AnagramFinderFast {
    public static int getAnagramIndexAtText(String anagramText, String text) {
        if (anagramText.length() == 0)
            return 0;
        if (anagramText.length() > text.length())
            return -1;

        System.out.printf("Find anagram: '%s' at text: %s%n", anagramText, text);

        HashMap<Character, Integer> anagram = getHashMap(anagramText);
        System.out.printf("Reference anagram hash map is: %s%n", anagram);

        for (int i = 0; i < anagramText.length(); i++) {
            subtractCharCount(anagram, text.charAt(i));
        }

        if (anagram.isEmpty())
            return 0;

        for (int j = 0; j <= text.length() - anagramText.length() - 1; j++) {
            addCharCount(anagram, text.charAt(j));
            subtractCharCount(anagram, text.charAt(j + anagramText.length()));

            if (anagram.isEmpty())
                return j + 1;
        }

        return -1;

    }

    private static HashMap<Character, Integer> getHashMap(String anagram) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < anagram.length(); i++) {
            if (hashMap.containsKey(anagram.charAt(i))){
                hashMap.put(anagram.charAt(i), hashMap.get(anagram.charAt(i)) + 1);
            } else {
                hashMap.put(anagram.charAt(i), 1);
            }
        }
        return hashMap;
    }

    private static void subtractCharCount(HashMap<Character, Integer> hashMap, char c) {
        if (hashMap.containsKey(c)){
            if (hashMap.get(c) - 1 == 0) {
                hashMap.remove(c);
            }
            else
                hashMap.put(c, hashMap.get(c) - 1);
        } else {
            hashMap.put(c, -1);
        }
    }

    private static void addCharCount(HashMap<Character, Integer> hashMap, char c) {
        if (hashMap.containsKey(c)){
            if (hashMap.get(c) + 1 == 0) {
                hashMap.remove(c);
            }
            else
                hashMap.put(c, hashMap.get(c) + 1);
        } else {
            hashMap.put(c, 1);
        }
    }
}
