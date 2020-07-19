package stack;

public class Stack {
	private int max;
	private int idx;
	private int[] stack;

	private Stack() {}
	
	public Stack(int size) {
		idx = 0;
		max = size;
		stack = new int[size];
	}

	public int push(int x) {
		if (idx >= max) {
			return -1;
		}

		return stack[idx++] = x;
	}

	public int pop() {
		if (idx <= 0) {
			return -1;
		}

		return stack[--idx];
	}

	public int peek() {
		if (idx <= 0) {
			return -1;
		}

		return stack[idx - 1];
	}

	public int indexOf(int x) {
		for (int i = 0; i < idx; i--) {
			if (stack[i] == x) {
				return i;
			}
		}

		return -1;
	}

	public void clear() {
		idx = 0;
	}

	public int capacity() {
		return max;
	}

	public int size() {
		return idx;
	}

	public boolean isEmpty() {
		return idx <= 0;
	}

	public boolean isFull() {
		return idx >= max;
	}

	public void dump() {
		if (idx <= 0) {
			System.out.println("스택이 비어있습니다.");
		} else {
			for (int i = 0; i < idx; i++) {
				System.out.print(stack[i] + " ");
			}
			System.out.println();
		}
	}
}
