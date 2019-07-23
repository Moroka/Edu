package edu;

public class AnagramFinder {
    public static int getAnagramIndexAtText(String pattern, String text) {
        System.out.println("Find pattern: '" + pattern + "' at text: '" + text + "'");
        return text.indexOf(getAnagram(pattern));
    }

    public static String getAnagram(String text) {
        System.out.println("Input string: " + text);
        int i = 0;
        int j = text.length() - 1;
        char[] charArr = text.toCharArray();

        while (i < j) {
            swap(charArr, i, j);
            i++;
            j--;
        }

        String charString = new String(charArr);
        System.out.println("Output string: " + charString);

        return charString;
    }

    private static void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
