package edu.AnagramFinder;

import java.util.HashMap;

public class CharIntHashMap extends HashMap<Character, Integer> {
    public CharIntHashMap(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (containsKey(s.charAt(i))){
                put(s.charAt(i), get(s.charAt(i)) + 1);
            } else {
                put(s.charAt(i), 1);
            }
        }
    }

    public CharIntHashMap getHashMapFromString(String anagram) {
        for (int i = 0; i < anagram.length(); i++) {
            if (containsKey(anagram.charAt(i))){
                put(anagram.charAt(i), get(anagram.charAt(i)) + 1);
            } else {
                put(anagram.charAt(i), 1);
            }
        }
        return this;
    }

    public void subtractCharCount(char c) {
        Integer value = get(c);

        if (value == null){
            put(c, -1);
        } else {
            if (value == 1)
                remove(c);
            else
                put(c, value - 1);
        }
    }

    public void addCharCount(char c) {
        Integer value = get(c);

        if (value == null) {
            put(c, 1);
        } else {
            if (value == -1)
                remove(c);
            else
                put(c, value + 1);
        }
    }
}
