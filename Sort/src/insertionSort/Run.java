package insertionSort;

public class Run {
    public static void main(String[] args) {
        int[] arr = {7, 22, 5, 11, 32, 120, 68, 70};
        InsertionSort insertionSort = new InsertionSort(arr);
        insertionSort.sort();
    }
}
