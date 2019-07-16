public class QuickSort {
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int p = partition(arr, low, high);
            quickSort(arr, low, p - 1);
            quickSort(arr, p + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        System.out.println("Low: " + Integer.toString(low) + " / High: " +  Integer.toString(high));
        int pivot = arr[(low + high) / 2];
        System.out.println("Pivot: " + Integer.toString(pivot));
        int i = low;
        int j = high;

        while (arr[i] < pivot) i++;
        while (arr[j] > pivot) j--;

        if (arr[i] >= arr[j]) {
           int temp = arr[i];
           arr[i] = arr[j];
           arr[j] = temp;
        }
        return j;
    }
}
