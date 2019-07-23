package edu;

import org.junit.Test;

import static org.junit.Assert.*;

public class ErrorTextFinderTest {
    @Test
    public void differentTextsLengthTest() {
        assertTrue(ErrorTextFinder.isTextContainErrors("github", "gggithub"));
    }

    @Test
    public void equalTextsTest() {
        assertFalse(ErrorTextFinder.isTextContainErrors("github", "github"));
    }

    @Test
    public void wrongOneCharTest() {
        assertFalse(ErrorTextFinder.isTextContainErrors("github", "gethub"));
    }

    @Test
    public void wrongTwoCharsTest() {
        assertTrue(ErrorTextFinder.isTextContainErrors("github", "gethob"));
    }

    @Test
    public void missingCharTest() {
        assertFalse(ErrorTextFinder.isTextContainErrors("github", "gthub"));
        assertFalse(ErrorTextFinder.isTextContainErrors("github", "githu"));
    }

    @Test
    public void missingCharAndWrongCharTest() {
        assertTrue(ErrorTextFinder.isTextContainErrors("github", "githo"));
    }

    @Test
    public void extraCharTest() {
        assertFalse(ErrorTextFinder.isTextContainErrors("github", "gitthub"));
        assertFalse(ErrorTextFinder.isTextContainErrors("github", " github"));
    }

    @Test
    public void extraCharAndWrongCharTest() {
        assertTrue(ErrorTextFinder.isTextContainErrors("github", "getthub"));
    }
}