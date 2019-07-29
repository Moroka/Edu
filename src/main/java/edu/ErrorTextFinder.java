package edu;

public class ErrorTextFinder {

    //
    public static boolean isTextContainsMoreThanTwoErrors(String sourceText, String comparedText) {
        final int lengthDiff = sourceText.length() - comparedText.length();

        // Get result if length of texts differs by more than 1
        if (lengthDiff > 1 || lengthDiff < -1) return true;

        // If sourceText.length == comparedText.length
        if (lengthDiff == 0) {
            for (int i = 0; i < sourceText.length() - 1; i++) {
                if (sourceText.charAt(i) != comparedText.charAt(i)) {
                    // After finding first mismatch - check remaining tails
                    if (i + 1 <= sourceText.length() - 1)
                        return !isRemainingStringsTailsEqual(sourceText, i + 1, comparedText, i + 1);
                    else return false;
                }
            }

        // ComparedText has missing or extra chars
        } else {
            for (int i = 0; i < sourceText.length() - 1; i++) {
                if (sourceText.charAt(i) != comparedText.charAt(i)) {
                    // missing char
                    if ((i + 1 <= sourceText.length() - 1)) {
                        if (isRemainingStringsTailsEqual(sourceText, i + 1, comparedText, i)) return false;
                    }
                    // extra char
                    if (i + 1 <= comparedText.length() - 1) {
                        if (isRemainingStringsTailsEqual(sourceText, i, comparedText, i + 1)) return false;
                    }
                    // If we can't find isTailAfterMissingCharOk or isTailAfterExtraCharOk - text contains more than 1 error
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isRemainingStringsTailsEqual(String s1, int sliceInd1, String s2, int sliceInd2) {
        return s1.substring(sliceInd1).equals(s2.substring(sliceInd2));
    }
}
