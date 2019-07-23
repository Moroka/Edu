package edu;

public class ErrorTextFinder {

    //
    public static boolean isTextContainErrors(String sourceText, String comparedText) {
        System.out.print("Compare '" + sourceText + "' & '" + comparedText);

        final int lengthDiff = sourceText.length() - comparedText.length();

        // Get result if length of texts differs by more than 1
        if (lengthDiff > 1 || lengthDiff < -1) {
            System.out.println("' Length of texts differs by more than 1");
            return true;
        }
        // Get result if texts are equal
        if (sourceText.equals(comparedText)) {
            System.out.println("' Texts are equal");
            return false;
        }

        int errorCounter = 0;
        char[] sourceTextCharArr = sourceText.toCharArray();
        char[] comparedTextCharArr = comparedText.toCharArray();

        // If sourceText.length == comparedText.length: compare chars and calculate errors count
        if (lengthDiff == 0) {
            for (int i = 0; i < sourceText.length() - 1; i++) {
                if (sourceTextCharArr[i] != comparedTextCharArr[i]) {
                    errorCounter++;
                }
            }

        // If comparedText has missing chars
        } else if (lengthDiff == 1) {
            int j = 0;
            for (int i = 0; i < sourceText.length() - 1; i++) {
                if (sourceTextCharArr[i] != comparedTextCharArr[j]) {
                    errorCounter++;
                    if (sourceTextCharArr[i+1] == comparedTextCharArr[j]) {
                        i++;
                    } else {
                        errorCounter++;
                    }
                }
                j++;
            }
        // ComparedText has extra chars
        } else {
            int j = 0;
            for (int i = 0; i < sourceText.length() - 1; i++) {
                if (sourceTextCharArr[i] != comparedTextCharArr[j]) {
                    errorCounter++;
                    if (sourceTextCharArr[i] == comparedTextCharArr[j+1]) {
                        j++;
                    } else {
                        errorCounter++;
                    }
                }
                j++;
            }
        }

        System.out.println("' Errors count: " + errorCounter);
        return errorCounter > 1;
    }
}
