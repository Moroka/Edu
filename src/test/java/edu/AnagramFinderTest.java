package edu;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnagramFinderTest {
    @Test
    public void anagramFinderTest1() {
        assertEquals(-1, AnagramFinder.getAnagramIndexAtText("rest", "simple test"));
    }

    @Test
    public void anagramFinderTest2() {
        assertEquals(1, AnagramFinder.getAnagramIndexAtText("rat", "part"));
    }

    @Test
    public void anagramFinderTest3() {
        assertEquals(8, AnagramFinder.getAnagramIndexAtText("test", "tesqqqtestqqqq"));
    }

    @Test
    public void anagramFinderTest4() {
        assertEquals(8, AnagramFinder.getAnagramIndexAtText("yaes", "It's so easy with github"));
    }

    @Test
    public void anagramFinderTest5() {
        assertEquals(-1, AnagramFinder.getAnagramIndexAtText("ttse", "simple trest"));
    }
}