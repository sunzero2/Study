package array;

import java.util.Arrays;

public class ArrayTest {
	public static void main(String[] args) {
		int[] array = new int[3];
		array[0] = 10;
		System.out.println(Arrays.toString(array));
		
		int move = array[0];
		array[0] = 5;
		array[1] = move;
		System.out.println(Arrays.toString(array));
		
		array[0] = 0;
		System.out.println(Arrays.toString(array));
	}
}
