package edu;

import org.junit.Test;

import static org.junit.Assert.*;

public class ErrorTextFinderTest {
    // Text contains more than two errors
    @Test
    public void wrongTwoCharsTest() {
        assertTrue(ErrorTextFinder.isTextContainsMoreThanTwoErrors("github", "gethob"));
    }

    @Test
    public void twoExtraLettersTest() {
        assertTrue(ErrorTextFinder.isTextContainsMoreThanTwoErrors("github", "gggithub"));
    }

    @Test
    public void twoMissingLettersTest() {
        assertTrue(ErrorTextFinder.isTextContainsMoreThanTwoErrors("github", "gith"));
    }

    @Test
    public void extraCharAndWrongCharTest() {
        assertTrue(ErrorTextFinder.isTextContainsMoreThanTwoErrors("github", "getthub"));
    }

    @Test
    public void missingCharAndWrongCharTest() {
        assertTrue(ErrorTextFinder.isTextContainsMoreThanTwoErrors("github", "githo"));
        assertTrue(ErrorTextFinder.isTextContainsMoreThanTwoErrors("github", "gehub"));
    }

    @Test
    public void missingCharAndExtraCharTest() {
        assertTrue(ErrorTextFinder.isTextContainsMoreThanTwoErrors("github", "ggithu"));
    }

    // Text contains one error
    @Test
    public void extraCharTest() {
        assertFalse(ErrorTextFinder.isTextContainsMoreThanTwoErrors("github", "gitthub"));
        assertFalse(ErrorTextFinder.isTextContainsMoreThanTwoErrors("github", " github"));
    }

    @Test
    public void wrongCharTest() {
        assertFalse(ErrorTextFinder.isTextContainsMoreThanTwoErrors("github", "gethub"));
        assertFalse(ErrorTextFinder.isTextContainsMoreThanTwoErrors("github", "githud"));
    }

    @Test
    public void missingCharTest() {
        assertFalse(ErrorTextFinder.isTextContainsMoreThanTwoErrors("github", "gthub"));
        assertFalse(ErrorTextFinder.isTextContainsMoreThanTwoErrors("github", "githu"));
        assertFalse(ErrorTextFinder.isTextContainsMoreThanTwoErrors("githu", "github"));
    }

    // Text doesn't contains any errors
    @Test
    public void equalTextsTest() {
        assertFalse(ErrorTextFinder.isTextContainsMoreThanTwoErrors("github", "github"));
    }
}