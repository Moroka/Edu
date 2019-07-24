package edu;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnagramFinderTest {
    @Test
    public void anagramFinderTest() {
        assertEquals(7, AnagramFinder.getAnagramIndexAtText("ttse",
                "simple test"));
        assertEquals(8, AnagramFinder.getAnagramIndexAtText("ttse",
                "simple stest"));
        assertEquals(41, AnagramFinder.getAnagramIndexAtText("yaes",
                "If you want to change default branch, it's so easy with github"));
    }

    @Test
    public void aTest() {
        assertEquals(-1, AnagramFinder.getAnagramIndexAtText("ttse",
                "simple trest"));
    }
}