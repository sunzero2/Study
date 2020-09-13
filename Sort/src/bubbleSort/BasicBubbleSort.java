package bubbleSort;

import java.util.Arrays;

public class BasicBubbleSort {
    int[] array = null;
    int numberOfExchange = 0;
    int numberOfComparisons = 0;

    public BasicBubbleSort(int[] array) { // 생성자를 통해 배열을 전달받아 사용한다.
        this.array = array;
    }

    public void sort() { // 정렬을 진행할 sort 함수
        for(int i = (array.length - 1); i > 0; i--) {
            for(int j = 0; j < i; j++) {
                numberOfComparisons++;
                if(array[j] > array[j + 1]) {
                    swap(j + 1, j);
                    numberOfExchange++;
                }
            }
        }

        System.out.println("비교를 총 " + numberOfComparisons + "회 했습니다.");
        System.out.println("교환을 " + numberOfExchange + "회 했습니다.");
        System.out.println(Arrays.toString(array));
    }

    private void swap(int small, int big) { // 외부에서 호출할 수 없도록 private 선언
        int temporary = array[big];
        array[big] = array[small];
        array[small] = temporary;
    }
}
