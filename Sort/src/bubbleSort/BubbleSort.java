package bubbleSort;

import java.util.Arrays;

public class BubbleSort {
    int[] array = null;
    int pass = 1;
    int numberOfExchange = 0;
    int numberOfComparisons = 0;

    public BubbleSort(int[] array) {
        this.array = array;
    }

    public void sort() {
        outter:for(int i = (array.length - 1); i > 0; i--) {
            String out = "패스" + pass++ + ": \n";
            int exchange = 0;
            int lastChangeIndex = i;

            for(int j = 0; j < i; j++) {
                out += stringConcat(0, j);
                numberOfComparisons++;

                if(array[j] > array[j + 1]) {
                    out += stringConcat(true, j, j + 1);
                    swap(j + 1, j);
                    exchange++;
                    numberOfExchange++;
                    lastChangeIndex = j + 1;
                } else {
                    out += stringConcat(false, j, j + 1);
                }
                out += stringConcat(j + 2, array.length) + "\n";
            }

            i = lastChangeIndex;

            if(exchange < 1) {
                System.out.println("더 이상의 정렬이 필요하지 않아 정렬을 종료합니다.\n");
                break outter;
            }

            System.out.println(out);
        }
        System.out.println("비교를 총 " + numberOfComparisons + "회 했습니다.");
        System.out.println("교환을 " + numberOfExchange + "회 했습니다.");
        System.out.println(Arrays.toString(array));
    }

    private String stringConcat(int start, int and) {
        String out = "";
        for(; start < and; start++) {
            out += array[start] + " ";
        }
        return out;
    }

    private String stringConcat(boolean isBigger, int first, int second) {
        if(isBigger) {
            return array[first] + "+" + array[second] + " ";
        }

        return array[first] + "-"+ array[second] + " ";
    }

    private void swap(int small, int big) {
        int temporary = array[big];
        array[big] = array[small];
        array[small] = temporary;
    }
}
