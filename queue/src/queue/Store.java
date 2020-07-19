package queue;

public class Store {
	private Customer[] customers;
	private int rear; // 큐의 맨 끝 요소를 가리킬 변수
	private int front; // 큐의 맨 처음 요소를 가리킬 변수
	private int max; // 큐의 크기를 저장할 변수
	private int elements; // 큐에 저장된 요소들의 크기를 저장할 변수
	
	private Store() {}
	
	public Store(int size) {
		rear = 0;
		front = 0;
		elements = 0;
		this.max = size;
		customers = new Customer[size];
	}
	
	public String enqueue(Customer customer) {
		if(elements >= max) {
			return "수용인원이 초과하여 매장에 입장하실 수 없습니다.";
		}
		
		customers[rear++] = customer;
		elements++;
		
		if(rear == max) {
			rear = 0;
		}
		
		return customer.getName() + "님이 입장하셨습니다.";
	}
	
	public String dequeue() {
		if(elements <= 0) {
			return "아직 입장한 고객이 없습니다.";
		}
		
		Customer customer = customers[front++];
		elements--;
		
		if(front == max) {
			front = 0;
		}
		
		return customer.getName() + "님이 퇴장하셨습니다.";
	}
	
	public String peek() {
		if(elements <= 0) {
			return "아직 입장한 고객이 없습니다.";
		}
		
		return "가장 먼저 입장한 고객님은 " + customers[front].getName() + "닙 입니다.";
	}
	
	public String indexOf(String name) {
		for(int i = 0; i < elements; i++) {
			if(customers[i].getName().equals(name)) {
				return name + "님은 " + i + "번째로 입장하셨습니다.";
			}
		}
		
		return name + "님은 입장하지 않으셨습니다.";
	}
	
	public void clear() {
		elements = front = rear = 0;
	}
	
	public int capacity() {
		return max;
	}
	
	public int size() {
		return elements;
	}
	
	public boolean isEmpty() {
		return elements <= 0;
	}
	
	public boolean isFull() {
		return elements >= max;
	}
	
	public void dump() {
		if(elements <= 0) {
			System.out.println("아직 입장한 고객이 없습니다");
		} else {
			System.out.println("=============== 입장 고객 명단 ===============");
			for (int i = 0; i < elements; i++) {
				System.out.println((i + 1) + "번째 : " + customers[i].getName() + "님");
			}
		}
	}
}
