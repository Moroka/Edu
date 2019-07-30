package edu;

public class ErrorTextFinder {

    //
    public static boolean hasMaxOneTypo(String s1, String s2) {
        final int lengthDiff = s1.length() - s2.length();

        // Get result if length of texts differs by more than 1
        if (lengthDiff > 1 || lengthDiff < -1) return false;

        // If lengths of texts are equal
        if (lengthDiff == 0) {
            return checkEqualLengths(s1, s2);
        } else return checkDifferentLengths(s1, s2);
    }

    private static boolean checkEqualLengths(String s1, String s2) {
        for (int i = 0; i < s1.length() - 1; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                // After finding first mismatch - check remaining tails
                if (i + 1 <= s1.length() - 1)
                    return tailsEqual(s1, i + 1, s2, i + 1);
            }
        }
        return true;
    }

    private static boolean checkDifferentLengths(String s1, String s2) {
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (tailsEqual(s1, i + 1, s2, i)) return true;
                return (tailsEqual(s1, i, s2, i + 1));
            }

            if (i == s2.length() - 1) return true;
        }
        return true;
    }

    private static boolean tailsEqual(String s1, int sliceInd1, String s2, int sliceInd2) {
        return s1.substring(sliceInd1).equals(s2.substring(sliceInd2));
    }
}
