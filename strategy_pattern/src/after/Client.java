package after;

public class Client {
	public static void main(String[] args) {
		Coffee coffee = new Mocha();
		Customer customer = new Customer(coffee);
		customer.coffeePrice();
	}
}
