package edu;

import java.util.HashMap;

public class AnagramFinder {
    public static int getAnagramIndexAtText(String anagramText, String text) {
        System.out.println("Find anagram: '" + anagramText + "' at text: '" + text + "'");

        HashMap<Character, Integer> anagram = getAnagram(anagramText);

        System.out.println("Anagram hashMap is: " + anagram);
        System.out.println();

        int originalSize = anagram.size();
        boolean reverseMode = false;
        int matchesCount = 0;

        for (int i = 0; i < text.length(); i++) {
             if (i > text.length() - anagram.size()) return -1;

             if (reverseMode) {
                 matchesCount = matchesCount - 1;
                 addCharToHashMap(anagram, text.charAt(i));
                 if (matchesCount == 0) {
                     reverseMode = false;
                     i = i+ 1;
                 }

             }

             if (anagram.containsKey(text.charAt(i)) && !reverseMode) {
                 System.out.println("Find anagram symbol '" + text.charAt(i) + "' at index: " + (i));
                 removeCharAtHashMap(anagram, text.charAt(i));
                 matchesCount = matchesCount + 1;
                 System.out.println("Anagram hashMap after removing symbol is: " + anagram + " Anagram size is: " + anagram.size() + " Matches count: " + matchesCount);
                 if (anagram.size() == 0) return i - originalSize + 1;
             else if (matchesCount > 0) {
                 i = i - matchesCount;
                 reverseMode = true;
                 }

//                 for (int j = 1; j < anagramText.length(); j++) {
//                     if (anagram.containsKey(text.charAt(i + j))) {
//                         System.out.println("Find anagram symbol '" + text.charAt(i+j) + "' at index: " + (i+j));
//                         removeCharAtHashMap(anagram, text.charAt(i+j));
//                         System.out.println("Anagram hashMap after removing symbol is: " + anagram + " Anagram size is: " + anagram.size());
//
//                     } else {
//                         System.out.println("Find non-anagram symbol '" + text.charAt(i+j) + "' at index: " + (i + j));
//                         anagram = getAnagram(anagramText);
//                         System.out.println(":: Refreshing anagram... " + anagram);
//                         break;
//                     }
//                 }
            }
        }

        return -1;

    }

    private static HashMap<Character, Integer> getAnagram(String anagram) {
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

    private static void removeCharAtHashMap(HashMap<Character, Integer> hashMap, Character c) {
        if (hashMap.get(c) > 1) {
            hashMap.put(c, hashMap.get(c) - 1);
        } else {
            hashMap.remove(c);
        }
    }

    private static void addCharToHashMap(HashMap<Character, Integer> hashMap, Character c) {
        if (hashMap.containsKey(c)) {
            hashMap.put(c, hashMap.get(c) + 1);
        } else {
            hashMap.put(c, 1);
        }
    }
}
