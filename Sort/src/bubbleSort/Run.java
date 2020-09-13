package bubbleSort;

public class Run {

    public static void main(String[] args) {
        //﻿[2, 3, 4, 6, 7, 8, 9, 1
        int[] bubble1 = {2, 3, 4, 6, 7, 8, 9, 1};
        int[] bubble2 = {2, 3, 4, 6, 7, 8, 9, 1};
        int[] bubble3 = {2, 3, 4, 6, 7, 8, 9, 1};
        BubbleSort bubbleSort = new BubbleSort(bubble1);
        bubbleSort.sort();

        System.out.println();
        BasicBubbleSort basicBubbleSort = new BasicBubbleSort(bubble2);
        basicBubbleSort.sort();

        System.out.println();
        CockailSort cockailSort = new CockailSort(bubble3);
        cockailSort.sortStandard();
    }
}
