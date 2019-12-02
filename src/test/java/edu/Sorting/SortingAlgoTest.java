/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.Sorting;

import org.junit.Test;
import static org.junit.Assert.*;

public class SortingAlgoTest {

    // MergeSort tests
    @Test
    public void mergeSortSimpleTest() {
        assertArrayEquals(new int[] {1, 2, 6, 7, 12, 19, 21}, MergeSort.mergeSort(new int[] {1, 7, 2, 19, 12, 6, 21}));
    }

    @Test
    public void mergeSortAlreadySortedTest() {
        assertArrayEquals(new int[] {1, 2, 6, 7, 12, 19, 21}, MergeSort.mergeSort(new int[] {1, 7, 2, 19, 12, 6, 21}));
    }

    @Test
    public void mergeSortDuplicateNumbersTest() {
        assertArrayEquals(new int[] {2, 2, 7, 8, 8, 8, 9}, MergeSort.mergeSort(new int[] {8, 9, 7, 8, 8, 2, 2}));
    }

    @Test
    public void mergeSortNegativeNumbersTest() {
        assertArrayEquals(new int[] {-25, -5, 0, 5, 10, 15, 50}, MergeSort.mergeSort(new int[] {50, -25, -5, 5, 10, 15, 0}));
    }

    // QuickSort tests
    @Test
    public void quickSortSimpleTest() {
        assertArrayEquals(new int[] {1, 2, 6, 7, 12, 19, 21}, QuickSort.quickSort(new int[] {1, 7, 2, 19, 12, 6, 21}));
    }

    @Test
    public void quickSortAlreadySortedTest() {
        assertArrayEquals(new int[] {1, 2, 6, 7, 12, 19, 21}, QuickSort.quickSort(new int[] {1, 7, 2, 19, 12, 6, 21}));
    }

    @Test
    public void quickSortNegativeNumbersTest() {
        assertArrayEquals(new int[] {-25, -5, 0, 5, 10, 15, 50}, QuickSort.quickSort(new int[] {50, -25, -5, 5, 10, 15, 0}));
    }

    @Test
    public void quickSortSDuplicateNumbersTest() {
        assertArrayEquals(new int[] {2, 2, 7, 8, 8, 8, 9}, QuickSort.quickSort(new int[] {8, 9, 7, 8, 8, 2, 2}));
    }
}