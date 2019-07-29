package edu;

import java.util.HashMap;

public class AnagramFinder {
    public static int getAnagramIndexAtText(String anagramText, String text) {
        System.out.println("Find anagram: '" + anagramText + "' at text: '" + text + "'");

        HashMap<Character, Integer> anagram = getAnagram(anagramText);

        System.out.println("Anagram hashMap is: " + anagram);
        System.out.println();

        for (int i = 0; i < text.length(); i++) {
             if (i > text.length() - 1 - anagram.size()) return -1;
             if (anagram.containsKey(text.charAt(i))) {
                 System.out.println("Find anagram symbol '" + text.charAt(i) + "' at index: " + (i));
                 removeCharAtHashMap(anagram, text.charAt(i));
                 System.out.println("Anagram hashMap after removing symbol is: " + anagram + " Anagram size is: " + anagram.size());
                 if (anagram.size() == 0) return i;
                 for (int j = 1; j < anagramText.length(); j++) {
                     if (anagram.containsKey(text.charAt(i + j))) {
                         System.out.println("Find anagram symbol '" + text.charAt(i+j) + "' at index: " + (i+j));
                         removeCharAtHashMap(anagram, text.charAt(i+j));
                         System.out.println("Anagram hashMap after removing symbol is: " + anagram + " Anagram size is: " + anagram.size());
                         if (anagram.size() == 0) return i;
                     } else {
                         System.out.println("Find non-anagram symbol '" + text.charAt(i+j) + "' at index: " + (i + j));
                         anagram = getAnagram(anagramText);
                         System.out.println(":: Refreshing anagram... " + anagram);
                         break;
                     }
                 }
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

    private static void removeCharAtHashMap(HashMap<Character, Integer> hashMap, Character j) {
        if (hashMap.get(j) > 1) {
            hashMap.put(j, hashMap.get(j)- 1);
        } else {
            hashMap.remove(j);
        }
    }
}
