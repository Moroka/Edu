package edu.AnagramFinder;

import java.util.HashMap;

public class CharIntHashMap extends HashMap<Character, Integer> {
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
