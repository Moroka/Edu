package edu;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnagramFinderFastTest {
    @Test
    public void anagramAtBeginningOfText() {
        assertEquals(0, AnagramFinderFast.getAnagramIndexAtText("ttes", "testsimple"));
    }

    @Test
    public void anagramAtEndingOfText() {
        assertEquals(6, AnagramFinderFast.getAnagramIndexAtText("ttes", "simpletest"));
    }

    @Test
    public void anagramAtMiddleOfText() {
        assertEquals(3, AnagramFinderFast.getAnagramIndexAtText("ttes", "simtestple"));
    }

    @Test
    public void anagramIsText() {
        assertEquals(0, AnagramFinderFast.getAnagramIndexAtText("bcd", "dcb"));
    }

    @Test
    public void anagramIsEqualToText() {
        assertEquals(0, AnagramFinderFast.getAnagramIndexAtText("bcd", "bcd"));
    }

    @Test
    public void anagramFalsePositive() {
        assertEquals(6, AnagramFinderFast.getAnagramIndexAtText("abc", "abdacdabc"));
    }

    @Test
    public void anagramAtEmptyText() {
        assertEquals(-1, AnagramFinderFast.getAnagramIndexAtText("fail", ""));
    }

    @Test
    public void emptyAnagramAtText() {
        assertEquals(-1, AnagramFinderFast.getAnagramIndexAtText("", "fail"));
    }
}