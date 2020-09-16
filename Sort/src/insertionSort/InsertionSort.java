package insertionSort;

import java.util.Arrays;

public class InsertionSort {
    int[] arr = null;

    public InsertionSort(int[] arr) {
        this.arr = arr;
    }

    public void sort() {
        for (int i = 0; i < arr.length; i++) {
            int j;
            int temporary = arr[i];
            for (j = i; j > 0 && arr[j - 1] > temporary; j--) {
                System.out.println("j - 1 : " + arr[j - 1] + " temp : " + temporary);
                arr[j] = arr[j - 1];
            }
            arr[j] = temporary;
        }
        System.out.println(Arrays.toString(arr));
    }
}
