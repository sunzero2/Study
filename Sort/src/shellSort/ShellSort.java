package shellSort;

public class ShellSort {
    public void shellSort(int[] arr, int n) {
        do {
            n = n / 3 + 1;
            for (int i = 0; i < arr.length && i + n < arr.length; i++) {
                int tmp = arr[i];
                if (arr[i] > arr[i + n]) {
                    arr[i] = arr[i + n];
                    arr[i + n] = tmp;
                }
            }
        } while(n == 1);

//        int h;
//        for (h = 1; h < n / 9; h = h * 3 + 1);
//        for (; h > 0; h /= 3) {
//            for (int i = h; i < n; i++) {
//                int j;
//                int tmp = arr[i];
//                for (j = i - h; j >= 0 && arr[j] > tmp; j -= h) {
//                    arr[j + h] = arr[j];
//                }
//                arr[j + h] = tmp;
//            }
//        }
    }
}
