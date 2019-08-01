package edu;

import org.junit.Test;

import static org.junit.Assert.*;

public class TypoTextFinderTest {
    // Text contains more than two typos
    @Test
    public void wrongTwoCharsTest() {
        assertFalse(TypoTextFinder.hasMaxOneTypo("github", "gethob"));
    }

    @Test
    public void twoExtraCharsTest() {
        assertFalse(TypoTextFinder.hasMaxOneTypo("github", "gggithub"));
    }

    @Test
    public void twoMissingCharsTest() {
        assertFalse(TypoTextFinder.hasMaxOneTypo("github", "gith"));
    }

    @Test
    public void extraCharAndWrongCharTest() {
        assertFalse(TypoTextFinder.hasMaxOneTypo("g", "tt"));
        assertFalse(TypoTextFinder.hasMaxOneTypo("tt", "g"));
        assertFalse(TypoTextFinder.hasMaxOneTypo("github", "getthub"));
    }

    @Test
    public void missingCharAndWrongCharTest() {
        assertFalse(TypoTextFinder.hasMaxOneTypo("github", "githo"));
        assertFalse(TypoTextFinder.hasMaxOneTypo("github", "gehub"));
    }

    @Test
    public void missingCharAndExtraCharTest() {
        assertFalse(TypoTextFinder.hasMaxOneTypo("github", "ggithu"));
    }

    // Text contains one typo
    @Test
    public void extraCharTest() {
        assertTrue(TypoTextFinder.hasMaxOneTypo("github", "gitthub"));
        assertTrue(TypoTextFinder.hasMaxOneTypo("github", " github"));
    }

    @Test
    public void wrongCharTest() {
        assertTrue(TypoTextFinder.hasMaxOneTypo("github", "gethub"));
        assertTrue(TypoTextFinder.hasMaxOneTypo("github", "githud"));
    }

    @Test
    public void missingCharTest() {
        assertTrue(TypoTextFinder.hasMaxOneTypo("github", "gthub"));
        assertTrue(TypoTextFinder.hasMaxOneTypo("github", "githu"));
        assertTrue(TypoTextFinder.hasMaxOneTypo("", "b"));
    }

    // Text doesn't contains any typos
    @Test
    public void equalTextsTest() {
        assertTrue(TypoTextFinder.hasMaxOneTypo("github", "github"));
    }
}
