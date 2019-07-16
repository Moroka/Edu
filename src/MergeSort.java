import java.util.ArrayList;
import java.util.Arrays;

public class MergeSort {
    public static int[] mergeSort(int[] arr) {
        System.out.print("MergeSort: " + Arrays.toString(arr) + " -> ");

        if (arr.length < 2) {
            System.out.println(Arrays.toString(arr));
            return arr;
        }

        int middle = arr.length / 2;

        int[] leftPartArray = Arrays.copyOfRange(arr, 0, middle);
        int[] rightPartArray = Arrays.copyOfRange(arr, middle, arr.length);

        System.out.println(Arrays.toString(rightPartArray) + " + " + Arrays.toString(leftPartArray));

        int[] sortedLeft = mergeSort(leftPartArray);
        int[] sortedRight = mergeSort(rightPartArray);

        return merge(sortedLeft, sortedRight);
    }

    private static int[] merge(int[] leftArray, int[] rightArray) {
        System.out.print("Merge: " + Arrays.toString(leftArray) + " + " + Arrays.toString(rightArray) + " -> ");

        ArrayList<Integer> sortedArray = new ArrayList<>();
        int leftArrayIndex = 0;
        int rightArrayIndex = 0;

        while ((leftArrayIndex < leftArray.length) && (rightArrayIndex < rightArray.length)) {
            if (leftArray[leftArrayIndex] < rightArray[rightArrayIndex]) {
                sortedArray.add(leftArray[leftArrayIndex]);
                leftArrayIndex++;
            } else {
                sortedArray.add(rightArray[rightArrayIndex]);
                rightArrayIndex++;
            }
        }

        while (leftArrayIndex < leftArray.length) {
            sortedArray.add(leftArray[leftArrayIndex]);
            leftArrayIndex++;
        }
        while (rightArrayIndex < rightArray.length) {
            sortedArray.add(rightArray[rightArrayIndex]);
            rightArrayIndex++;
        }

        System.out.println(sortedArray);

        int[] arrayForConversion = new int[sortedArray.size()];
        for (int i =0; i < sortedArray.size(); i++)
            arrayForConversion[i] = sortedArray.get(i);

        return arrayForConversion;
    }
}
