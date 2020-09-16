package selectionSort;

import java.util.Arrays;

public class SelectionSort {
    int[] arr = null;

    public SelectionSort(int[] arr) {
        this.arr = arr;
    }

    public void sort() {
        for(int i = 0; i < arr.length; i++) {
            int min = i;
            for(int j = i + 1; j < arr.length; j++) {
                if(arr[j] < arr[min]) {
                    min = j;
                }
            }
            swap(min, i);
        }

        System.out.println(Arrays.toString(arr));
    }

    private void swap(int small, int big) {
        int temporary = arr[small];
        arr[small] = arr[big];
        arr[big] = temporary;
    }
}
