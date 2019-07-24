package edu;

public class ErrorTextFinder {

    //
    public static boolean isTextContainErrors(String sourceText, String comparedText) {
        final int lengthDiff = sourceText.length() - comparedText.length();

        // Get result if length of texts differs by more than 1
        if (lengthDiff > 1 || lengthDiff < -1) return true;

        // If sourceText.length == comparedText.length: compare chars and calculate errors count
        if (lengthDiff == 0) {
            int errorCounter = 0;
            for (int i = 0; i < sourceText.length() - 1; i++) {
                if (sourceText.charAt(i) != comparedText.charAt(i)) {
                    errorCounter++;
                    if (errorCounter > 1) return true;
                }
            }

        // ComparedText has missing or extra chars
        } else {
            for (int i = 0; i < sourceText.length() - 1; i++) {
                if (sourceText.charAt(i) != comparedText.charAt(i)) {
                    // sourceText.length > comparedText.length
                    if (lengthDiff == -1)
                        return !(sourceText.substring(i).equals(comparedText.substring(i + 1)));
                    // sourceText.length < comparedText.length
                    else
                        return !(sourceText.substring(i + 1).equals(comparedText.substring(i)));
                }
            }
        }

        return false;
    }
}
