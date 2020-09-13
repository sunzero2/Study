package bubbleSort;

import java.util.Arrays;

public class CockailSort {
    int[] array = null;
    int numberOfExchange = 0;
    int numberOfComparisons = 0;

    public CockailSort(int[] array) {
        this.array = array;
    }

    public void sortStandard() {
       for(int i = array.length - 1; i > 0 ; i--) {
           boolean change = true;
           if (i % 2 == 0) {
               change = evenNumber(i);
           } else {
               change = oddNumber(i);
           }


           if (!change) {
               break;
           }
       }

        System.out.println("비교를 총 " + numberOfComparisons + "회 했습니다.");
        System.out.println("교환을 " + numberOfExchange + "회 했습니다.");
        System.out.println(Arrays.toString(array));
    }

    private boolean evenNumber(int max) {
        boolean change = false;
        for(int i = 0; i < max; i++) {
            numberOfExchange++;
            if (array[i] > array[i + 1]) {
                numberOfComparisons++;
                int temporary = array[i + 1];
                array[i + 1] = array[i];
                array[i] = temporary;
                change = true;
            }
        }
        return change;
    }

    private boolean oddNumber(int start) {
        boolean change = false;
        for(; start > 0; start--) {
            numberOfExchange++;
            if (array[start] < array[start - 1]) {
                numberOfComparisons++;
                int temporary = array[start - 1];
                array[start - 1] = array[start];
                array[start] = temporary;
                change = true;
            }
        }
        return change;
    }
}
