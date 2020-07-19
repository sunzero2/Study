package stack;

import java.util.Scanner;

public class Run {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Stack stack = new Stack(64);
		
		while(true) {
			System.out.println("현재 데이터 수 : " + stack.size() + " / " + stack.capacity());
			System.out.println("1. 푸시 / 2. 팝 / 3. 피크 / 4. 덤프 / 0. 종료");
			int menu = sc.nextInt();
			
			if(menu == 0) {
				break;
			}
			
			int data;
			int res;
			switch (menu) {
			case 1:
				System.out.println("저장할 데이터를 입력하세요.");
				data = sc.nextInt();
				res = stack.push(data);
				if(res < 0) {
					System.out.println("스택이 가득 찼습니다.");
				}
				break;
			case 2:
				res = data = stack.pop();
				if(res < 0) {
					System.out.println("스택이 비어있습니다.");
				} else {
					System.out.println("꺼내온 데이터는 " + data + "입니다.");
				}
				break;
			case 3:
				res = data = stack.peek();
				if(res < 0) {
					System.out.println("스택이 비어있습니다.");
				} else {
					System.out.println("피크한 데이터는 " + data + "입니다.");
				}
				break;
			case 4:
				stack.dump();
				break;
			default:
				break;
			}
		}
	}
}
