package list;

import java.util.LinkedList;
import java.util.List;

public class ListTest {
	public static void main(String[] args) {
		List<Integer> list = new LinkedList<>();
		System.out.println("처음 생성 시 list의 크기 : " + list.size());
		
		list.add(10);
		System.out.println(list);
		
		list.add(0, 5);
		System.out.println(list);
		
		list.remove(0);
		System.out.println(list);
	}
}
