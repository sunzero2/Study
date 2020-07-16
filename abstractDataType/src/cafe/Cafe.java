package cafe;

import java.util.Scanner;

public class Cafe {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Pos pos = new Pos(100, 100000);
		
		run:while(true) {
			System.out.println("============= 영업 중 =============");
			System.out.println("메뉴를 선택하세요.");
			System.out.println("1. 주문 \n2. 재고 확인 \n3. 잔고 확인 \n4. 재고 충전 \n5. 잔고 충전 \n6. 영업종료");
			int choose = input.nextInt();
			
			switch(choose) {
			case 1:
				System.out.println("메뉴를 선택하세요. (아메리카노, 카페라떼, 카푸치노, 카라멜마끼아또)");
				input.nextLine();
				String menu = input.next();
				
				System.out.println("고객께 받은 금액을 입력하세요.");
				int pay = input.nextInt();
				
				pos.order(menu, pay);
				break;
			case 2:
				pos.getStock();
				break;
			case 3:
				pos.getBalance();
				break;
			case 4:
				System.out.println("충전할 재고량을 입력하세요.");
				int coffee = input.nextInt();
				pos.stockFill(coffee);
				break;
			case 5:
				System.out.println("충전할 잔고액을 입력하세요");
				int money = input.nextInt();
				pos.balanceFill(money);
				break;
			case 6:
				System.out.println("영업을 종료합니다.");
				break run;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력하십시오.");
				break;
			}
		}
	}
}
