import java.util.Arrays;

public class Main {

   public static void main(String[] args) {
       int[] arr1 = new int[]{ 1,7,2,19,12,6,21 };
       int[] arr2 = new int[]{ 7 };
       int[] arr3 = new int[]{ 1, 2 };

       QuickSort.quickSort(arr1, 0, arr1.length - 1);
       System.out.println(Arrays.toString(arr1));
   }
}