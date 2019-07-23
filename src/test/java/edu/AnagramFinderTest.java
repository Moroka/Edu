package edu;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnagramFinderTest {
    @Test
    public void anagramTest() {
        assertEquals("dessertS", AnagramFinder.getAnagram("Stressed"));
        assertEquals("dlrow olleh", AnagramFinder.getAnagram("hello world"));
        assertEquals(" eM", AnagramFinder.getAnagram("Me "));
    }

    @Test
    public void anagramFinderTest() {
        assertEquals(7, AnagramFinder.getAnagramIndexAtText("tset",
                "simple test"));
        assertEquals(46, AnagramFinder.getAnagramIndexAtText("ysae",
                "If you want to change default branch, it's so easy with github"));
    }
}