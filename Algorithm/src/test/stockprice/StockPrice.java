package test.stockprice;

import java.util.Arrays;

public class StockPrice {

	public static void main(String[] args) {
		int[] prices = {1, 2, 3, 4, 5, 3, 2, 0};
		System.out.println(Arrays.toString(solution(prices)));
	}

	public static int[] solution(int[] prices) {
		int[] answer = new int[prices.length];

		for (int i = 0; i < prices.length - 1; i++) {
			int count = 0;
			for (int j = i + 1; j < answer.length; j++) {
				count++;
				if(prices[i] > prices[j]) {
					break;
				}
			}
			answer[i] = count;
		}
		return answer;
	}
}
