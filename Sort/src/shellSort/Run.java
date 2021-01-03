package shellSort;

public class Run {
    public static void main(String[] args) {
        int[] array = {4, 8, 3, 5, 9, 6, 2, 1, 7};

        ShellSort shellSort = new ShellSort();
        shellSort.shellSort(array, array.length);
    }
}
