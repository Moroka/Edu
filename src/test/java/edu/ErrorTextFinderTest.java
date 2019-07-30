package edu;

import org.junit.Test;

import static org.junit.Assert.*;

public class ErrorTextFinderTest {
    // Text contains more than two typos
    @Test
    public void wrongTwoCharsTest() {
        assertFalse(ErrorTextFinder.hasMaxOneTypo("github", "gethob"));
    }

    @Test
    public void twoExtraCharsTest() {
        assertFalse(ErrorTextFinder.hasMaxOneTypo("github", "gggithub"));
    }

    @Test
    public void twoMissingCharsTest() {
        assertFalse(ErrorTextFinder.hasMaxOneTypo("github", "gith"));
    }

    @Test
    public void extraCharAndWrongCharTest() {
        assertFalse(ErrorTextFinder.hasMaxOneTypo("g", "tt"));
        assertFalse(ErrorTextFinder.hasMaxOneTypo("tt", "g"));
        assertFalse(ErrorTextFinder.hasMaxOneTypo("github", "getthub"));
    }

    @Test
    public void missingCharAndWrongCharTest() {
        assertFalse(ErrorTextFinder.hasMaxOneTypo("github", "githo"));
        assertFalse(ErrorTextFinder.hasMaxOneTypo("github", "gehub"));
    }

    @Test
    public void missingCharAndExtraCharTest() {
        assertFalse(ErrorTextFinder.hasMaxOneTypo("github", "ggithu"));
    }

    // Text contains one typo
    @Test
    public void extraCharTest() {
        assertTrue(ErrorTextFinder.hasMaxOneTypo("github", "gitthub"));
        assertTrue(ErrorTextFinder.hasMaxOneTypo("github", " github"));
    }

    @Test
    public void wrongCharTest() {
        assertTrue(ErrorTextFinder.hasMaxOneTypo("github", "gethub"));
        assertTrue(ErrorTextFinder.hasMaxOneTypo("github", "githud"));
    }

    @Test
    public void missingCharTest() {
        assertTrue(ErrorTextFinder.hasMaxOneTypo("github", "gthub"));
        assertTrue(ErrorTextFinder.hasMaxOneTypo("github", "githu"));
        assertTrue(ErrorTextFinder.hasMaxOneTypo("githu", "github"));
    }

    // Text doesn't contains any typos
    @Test
    public void equalTextsTest() {
        assertTrue(ErrorTextFinder.hasMaxOneTypo("github", "github"));
    }
}