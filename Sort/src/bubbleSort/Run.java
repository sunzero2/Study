import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        int[] bubble = {6, 3, 2, 10, 5, 4, 11, 9};
        int pass = 1;
        int cnt = 0;
        int numberOfComparisons = 0;

        for(int i = (bubble.length - 1); i > 0; i--) {
            String out = "패스" + pass++ + ": \n";

            for(int j = 0; j < i; j++) {
                out += arrayOut(0, j, bubble);
                numberOfComparisons++;
                if(bubble[j] > bubble[i]) {
                    out += bubble[j] + "+" + bubble[i] + " ";
                    cnt++;
                    int temporary = bubble[j];
                    bubble[j] = bubble[i];
                    bubble[i] = temporary;
                } else {
                    out += bubble[j] + "-" + bubble[i] + " ";
                }
                out += arrayOut(j + 1, bubble.length - 1, bubble) + "\n";
            }

            System.out.println(out);
        }
        System.out.println("비교를 총 " + numberOfComparisons + "회 했습니다.");
        System.out.println("교환을 " + cnt + "회 했습니다.");
    }

    public static String arrayOut(int start, int and, int[] array) {
        String out = "";

        for(; start < and; start++) {
            out += array[start] + " ";
        }

        return out;
    }
}
