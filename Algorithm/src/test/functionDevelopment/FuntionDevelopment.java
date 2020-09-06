package test.functionDevelopment;

import java.util.Arrays;

public class FuntionDevelopment {
	
	public static void main(String[] args) {
		int[] progresses = {30, 40, 50};
		int[] speeds = {30, 4, 50};
		
		System.out.println(Arrays.toString(solution(progresses, speeds)));
	}
	
	public static int[] solution (int[] progresses, int[] speeds) {
		int[] completeTimes = new int[progresses.length];
		int size = 1;
		
		for (int i = 0; i < progresses.length; i++) {
			int complete = 0;
			
			while (progresses[i] < 100) {
				progresses[i] = progresses[i] + speeds[i];
				complete++;
			}
			
			completeTimes[i] = complete;
			
			if(i != 0) {
				if(completeTimes[i - 1] < complete) {
					size++;
				}
			}
		}
		
		System.out.println(Arrays.toString(completeTimes));
		
		int[] answer = new int[size];
		Arrays.fill(answer, 1);
		int index = 0;
		int count = 1;
		
		for (int i = 1; i < completeTimes.length; i++) {
			if(completeTimes[i - 1] >= completeTimes[i]) {
				count++;
			} else {
				answer[index++] = count;
				count = 1;
			}
		}
		
		return answer;
	}
}
