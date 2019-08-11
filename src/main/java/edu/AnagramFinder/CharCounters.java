package edu.AnagramFinder;

import java.util.HashMap;

public class CharCounters {
    private HashMap<Character, Integer> hashMap;
    private String s;

    public CharCounters(String s) {
        this.s = s;
    }

    public HashMap<Character, Integer> getHashMap() {
        hashMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (hashMap.containsKey(s.charAt(i))){
                hashMap.put(s.charAt(i), hashMap.get(s.charAt(i)) + 1);
            } else {
                hashMap.put(s.charAt(i), 1);
            }
        }
        return hashMap;
    }

    public void subtractCharCount(char c) {
        Integer value = hashMap.get(c);

        if (value == null){
            hashMap.put(c, -1);
        } else {
            if (value == 1)
                hashMap.remove(c);
            else
                hashMap.put(c, value - 1);
        }
    }

    public void addCharCount(char c) {
        Integer value = hashMap.get(c);

        if (value == null) {
            hashMap.put(c, 1);
        } else {
            if (value == -1)
                hashMap.remove(c);
            else
                hashMap.put(c, value + 1);
        }
    }
}
