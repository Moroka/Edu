package edu;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnagramFinderTest {
    @Test
    public void anagramAtBeginningOfText() {
        assertEquals(0, AnagramFinder.getAnagramIndexAtText("ttes", "testsimple"));
    }

    @Test
    public void anagramAtEndingOfText() {
        assertEquals(6, AnagramFinder.getAnagramIndexAtText("ttes", "simpletest"));
    }

    @Test
    public void anagramAtMiddleOfText() {
        assertEquals(3, AnagramFinder.getAnagramIndexAtText("ttes", "simtestple"));
    }

    @Test
    public void anagramIsText() {
        assertEquals(0, AnagramFinder.getAnagramIndexAtText("bcd", "dcb"));
    }

    @Test
    public void anagramIsEqualToText() {
        assertEquals(0, AnagramFinder.getAnagramIndexAtText("bcd", "bcd"));
    }

    @Test
    public void anagramFalsePositive() {
        assertEquals(6, AnagramFinder.getAnagramIndexAtText("abc", "abdacdabc"));
    }

    @Test
    public void anagramAtEmptyText() {
        assertEquals(-1, AnagramFinder.getAnagramIndexAtText("fail", ""));
    }

    @Test
    public void emptyAnagramAtText() {
        assertEquals(-1, AnagramFinder.getAnagramIndexAtText("", "fail"));
    }
}