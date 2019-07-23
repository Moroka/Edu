package Edu;


public class QuickSort {

    public static int[] quickSort(int[] arr) {
        doQuickSort(arr, 0, arr.length - 1);
        return arr;
    }

    private static void doQuickSort(int[] arr, int low, int high) {
        if (low >= high) return;

        int p = partition(arr, low, high);
        System.out.println("Partition is: " + Integer.toString(p));

        doQuickSort(arr, low, p);
        doQuickSort(arr, p + 1, high);
    }

    private static int partition(int []arr, int low, int high) {
        System.out.println("---------------------------------------------------");
        System.out.println("New partition. Index range: " + Integer.toString(low) + " - " +  Integer.toString(high));
        printRangeOfArray(arr, low, high);

        int pivot = arr[(low + high) / 2];
        System.out.println("Pivot: " + Integer.toString(pivot));

        int i = low - 1;
        int j = high + 1;

        while (true) {
            do {
                i++;
            } while (arr[i] < pivot);
            do {
                j--;
            } while (arr[j] > pivot);

            System.out.println("Element which most(or equal) then pivot: " + Integer.toString(arr[i]));
            System.out.println("Element which less(or equal) then pivot: " + Integer.toString(arr[j]));

            if (i >= j) {
                return j;
            } else {
                swap(arr, i, j);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
      System.out.println("Swap: " + Integer.toString(arr[i]) + " -> " + Integer.toString(arr[j]));
      int tmp = arr[i];
      arr[i] = arr[j];
      arr[j] = tmp;
    }

    private static void printRangeOfArray(int[] arr, int low, int high) {
        System.out.print("Array range: ");
        for (int i = low; i <= high; ++i) {
            System.out.print(Integer.toString(arr[i]) + " ");
        }
        System.out.println();
    }
}