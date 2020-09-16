package selectionSort;

public class Run {
    public static void main(String[] args) {
        int[] arr = {5, 2, 8, 3, 0, 1, 4};
        SelectionSort selectionSort = new SelectionSort(arr);
        selectionSort.sort();
    }
}
