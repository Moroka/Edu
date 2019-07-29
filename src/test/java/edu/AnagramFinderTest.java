package edu;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnagramFinderTest {
    @Test
    public void anagramFinderTest() {
        assertEquals(-1, AnagramFinder.getAnagramIndexAtText("rest",
                "simple test"));
        assertEquals(7, AnagramFinder.getAnagramIndexAtText("ttse",
                "simple test"));
        assertEquals(8, AnagramFinder.getAnagramIndexAtText("ttse",
                "simple stest"));
        assertEquals(8, AnagramFinder.getAnagramIndexAtText("yaes",
                "It's so easy with github"));
        assertEquals(-1, AnagramFinder.getAnagramIndexAtText("ttse",
                "simple trest"));
    }
}