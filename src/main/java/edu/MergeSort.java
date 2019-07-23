package edu;

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


        int [] sortedArray = new int[leftArray.length + rightArray.length];

        int leftArrayIndex = 0;
        int rightArrayIndex = 0;
        int i = -1;


        while ((leftArrayIndex < leftArray.length) && (rightArrayIndex < rightArray.length)) {
            i++;
            if (leftArray[leftArrayIndex] < rightArray[rightArrayIndex]) {
                sortedArray[i] = leftArray[leftArrayIndex];
                leftArrayIndex++;
            } else {
                sortedArray[i] = rightArray[rightArrayIndex];
                rightArrayIndex++;
            }
        }

        // Ugly hack, no ideas how do it better
        while (leftArrayIndex < leftArray.length) {
            i++;
            sortedArray[i] = leftArray[leftArrayIndex];
            leftArrayIndex++;
        }
        while (rightArrayIndex < rightArray.length) {
            i++;
            sortedArray[i] = rightArray[rightArrayIndex];
            rightArrayIndex++;
        }

        System.out.println(Arrays.toString(sortedArray));

        return sortedArray;
    }
}
