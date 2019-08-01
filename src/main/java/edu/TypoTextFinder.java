package edu;

public class TypoTextFinder {
    public static boolean hasMaxOneTypo(String s1, String s2) {
        final int lengthDiff = s1.length() - s2.length();

        switch (lengthDiff) {
            case -1: return checkFirstLonger(s2, s1);
            case 0: return checkEqualLengths(s1, s2);
            case 1: return checkFirstLonger(s1, s2);
            default: return false;
        }
    }

    private static boolean checkEqualLengths(String s1, String s2) {
        final int typoIndex = firstTypoIndex(s1, s2);
        if (typoIndex < 0)
            return true;
        return tailsEqual(s1, typoIndex + 1, s2, typoIndex + 1);
    }

    private static boolean checkFirstLonger(String s1, String s2) {
        if (s2.length() == 0)
            return true;
        final int typoIndex = firstTypoIndex(s1, s2);
        if (typoIndex < 0)
            return true;
        return tailsEqual(s1, typoIndex + 1, s2, typoIndex);
    }

    private static int firstTypoIndex(String s1, String s2) {
        for (int i = 0; i < s2.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i))
                return i;
        }
        return -1;
    }

    private static boolean tailsEqual(String s1, int sliceInd1, String s2, int sliceInd2) {
        return s1.substring(sliceInd1).equals(s2.substring(sliceInd2));
    }
}
