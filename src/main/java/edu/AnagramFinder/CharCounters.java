package edu.AnagramFinder;

import java.util.HashMap;

public class CharCounters {
    private final HashMap<Character, Integer> storage = new HashMap<>();

    private CharCounters() {
    }

    public static CharCounters makeFromString(String s) {
        final CharCounters result = new CharCounters();
        for (int i = 0; i < s.length(); i++)
            result.addCharCount(s.charAt(i));
        return result;
    }

    public void subtractCharCount(char c) {
        Integer value = storage.get(c);

        if (value == null) {
            storage.put(c, -1);
            return;
        }

        if (value == 1)
            storage.remove(c);
        else
            storage.put(c, value - 1);
    }

    public void addCharCount(char c) {
        Integer value = storage.get(c);

        if (value == null) {
            storage.put(c, 1);
            return;
        }

        if (value == -1)
            storage.remove(c);
        else
            storage.put(c, value + 1);
    }

    public boolean isEmpty() {
        return storage.isEmpty();
    }
}
