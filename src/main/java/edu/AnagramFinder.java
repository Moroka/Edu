package edu;

import java.util.HashMap;

public class AnagramFinder {
    public static int getAnagramIndexAtText(String anagramText, String text) {
        System.out.println("Find anagram: '" + anagramText + "' at text: '" + text + "'");

        HashMap<Character, Integer> anagram = getAnagram(anagramText);;

        System.out.println("Anagram hashMap is: " + anagram);

        for (int i = 0; i < text.length(); i++) {
             if (anagram.containsKey(text.charAt(i))) {
                 for (int j = 0; j < anagramText.length(); j++) {
                     if (anagram.containsKey(text.charAt(i))) {
                         removeCharAtHashMap(anagram, text.charAt(i));
                         System.out.println("Char is: " + text.charAt(i));
                         System.out.println("Anagram hashMap is: aft remove" + anagram);
                         System.out.println("Size is: " + anagram.size());
                         if (anagram.size() == 0) return i;
                     } else {
                         i = i - j;
                         anagram = getAnagram(anagramText);
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
