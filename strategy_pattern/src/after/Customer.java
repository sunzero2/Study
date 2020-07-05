package after;

public class Customer {
	private Coffee coffee;
	
	public Customer() {
		
	}
	
	public Customer(Coffee coffee) {
		this.coffee = coffee;
	}
	
	public void coffeePrice() {
		this.coffee.price();
	}
}
