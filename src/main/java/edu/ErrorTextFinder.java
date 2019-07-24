package edu;

public class ErrorTextFinder {

    //
    public static boolean isTextContainErrors(String sourceText, String comparedText) {
        final int lengthDiff = sourceText.length() - comparedText.length();

        // Get result if length of texts differs by more than 1
        if (lengthDiff > 1 || lengthDiff < -1) return true;

        // If sourceText.length == comparedText.length
        if (lengthDiff == 0) {
            for (int i = 0; i < sourceText.length() - 1; i++) {
                if (sourceText.charAt(i) != comparedText.charAt(i)) {
                    // After finding first mismatch - check remaining tails
                    if (i + 1 <= sourceText.length() - 1)
                        return !isStringsEqual(sourceText.substring(i + 1), comparedText.substring(i + 1));
                    else return false;
                }
            }

        // ComparedText has missing or extra chars
        } else {
            boolean isTailAfterMissingCharOk;
            boolean isTailAfterExtraCharOk;
            for (int i = 0; i < sourceText.length() - 1; i++) {
                if (sourceText.charAt(i) != comparedText.charAt(i)) {
                    // missing char
                    if ((i + 1 <= sourceText.length() - 1)) {
                        isTailAfterMissingCharOk = isStringsEqual(sourceText.substring(i + 1), comparedText.substring(i));
                        if (isTailAfterMissingCharOk) return false;
                    }
                    // extra char
                    if ((i + 1 <= comparedText.length() - 1)) {
                        isTailAfterExtraCharOk = isStringsEqual(sourceText.substring(i), comparedText.substring(i + 1));
                        if (isTailAfterExtraCharOk) return false;
                    }
                    // If we can't find isTailAfterMissingCharOk or isTailAfterExtraCharOk - text contains more than 1 error
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isStringsEqual(String s1, String s2) {
        return s1.equals(s2);
    }
}
