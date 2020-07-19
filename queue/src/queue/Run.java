package queue;

import java.util.Scanner;

public class Run {

	public static void main(String[] args) {
		Store store = new Store(7);
		Scanner input = new Scanner(System.in);
		
		run:while(true) {
			System.out.println("현재 매장에 입장한 고객 수는 " + store.size() + "명입니다.");
			System.out.println("입장 가능 인원은 " + store.capacity() + "명입니다.");
			System.out.println("====== 메뉴를 선택하세요. ======");
			System.out.println("1. 입장\n2. 퇴장\n3. 입장시간이 가장 오래된 고객\n4. 고객 명단\n5. 종료");
			
			int menu = input.nextInt();
			
			switch(menu) {
			case 1:
				System.out.println("고객의 성함을 입력하세요.");
				input.nextLine();
				String name = input.nextLine();
				System.out.println(store.enqueue(new Customer(name)));
				System.out.println();
				break;
			case 2:
				System.out.println(store.dequeue());
				System.out.println();
				break;
			case 3:
				System.out.println(store.peek());
				System.out.println();
				break;
			case 4:
				store.dump();
				break;
			default:
				System.out.println("종료되었습니다.");
				store.clear();
				break run;
			}
		}
	}
}
