package cafe;

public class Pos {
	private int stock;
	private int balance;
	
	public Pos() {
		super();
	}

	public Pos(int stock, int balance) {
		super();
		this.stock = stock;
		this.balance = balance;
	}

	public int getStock() {
		System.out.println("현재 재고는 " + stock + "개 입니다.");
		System.out.println();
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getBalance() {
		System.out.println("현재 잔고는 " + balance + "원 입니다.");
		System.out.println();
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "현재 커피 재고는 " + stock + "개, 잔고는 " + balance + "원입니다.";
	}
	
	public String order(String menu, int pay) {
		int changeMoney;
		int coffee;
		int price;
		
		System.out.println("============= Order =============");
		System.out.println(menu + "을(를) 주문하셨습니다.");
		
		// 해당 메뉴가 존재하지 않을 경우 menu_recheck 반환
		switch(menu) {
		case "아메리카노":
			coffee = 15;
			price = 4500;
			break;
		case "카페라떼":
			coffee = 10;
			price = 6000;
			break;
		case "카푸치노":
			coffee = 20;
			price = 5500;
			break;
		case "카라멜마끼아또":
			coffee = 40;
			price = 6700;
			break;
		default:
			System.out.println("주문하신 메뉴는 존재하지 않습니다. 다시 확인하십시오.");
			return "menu_recheck";
		}
		
		// pay가 price보다 작을 경우 pay_recheck 반환
		if(pay < price) {
			System.out.println("가격보다 적은 금액을 지불하였습니다. 다시 확인하십시오.");
			return "pay_recheck";
		} else {
			changeMoney = pay - price;			
		}
		
		// 재고가 부족할 경우 stk_empty 반환
		if(stock < coffee) {
			System.out.println("재고가 부족합니다. 다시 확인하십시오.");
			return "stk_empty";
		} else {
			System.out.println(stock + "에서 " + coffee + "개 차감하였습니다.");
			stock -= coffee;
			System.out.println("현재 재고는 " + stock + "개 입니다.");
		}
			
		// balance가 changeMoney보다 작을 경우 bal_empty 반환
		if(balance < changeMoney) {
			System.out.println("잔고가 부족합니다. 잔고를 먼저 채운 후 다시 진행하십시오.");
			return "bal_empty";
		} else {
			System.out.println(balance + "에서 " + changeMoney + "원 만큼 차감하였습니다.");
			balance -= changeMoney;
			System.out.println("현재 잔고는 " + balance + "원 입니다.");
		}
		System.out.println();
		return "true";
	}
	
	public void balanceFill(int money) {
		System.out.println("============= Balance Fill =============");
		System.out.println(money + "원을 잔고에 저장합니다.");
		balance += money;
		System.out.println("현재 잔고는 " + balance + "원 입니다.");
		System.out.println();
	}
	
	public void stockFill(int coffee) {
		System.out.println("============= Stock Fill =============");
		System.out.println(coffee + "개를 재고에 추가합니다.");
		stock += coffee;
		System.out.println("현재 재고는 " + stock + "개 입니다.");
		System.out.println();
	}
}
