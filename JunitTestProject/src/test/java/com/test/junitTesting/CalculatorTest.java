package com.test.junitTesting;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

class CalculatorTest {
	int cnt = 0;
	
	// @BeforeAll : 해당 어노테이션이 달린 메소드가 현재 클래스의 모든 메소드보다 먼저 실행
	// 				해당 메소드는 static 이어야 한다.
	//				Junit4의 @BeforeClass와 동일
	@BeforeAll
	public static void beforeClassMethod() {
		System.out.println("제일 먼저 실행되는 BeforeClass 메소드");
	}
	
	// @BeforeEach : 해당 어노테이션이 달린 메소드가 각 테스트 메소드 전에 실행된다.
	//				각 테스트 진행 전에 객체를 초기화하는 작업으로 많이 사용된다.
	//				Junit4의 @Before와 동일
	@BeforeEach
	public void beforeMethod() {
		System.out.println("테스트 진행 전!");
	}
	
	// @@Disabled : 테스트 클래스 또는 메소드를 비활성화
	//			Junit4의 @Ignore와 동일
	@Disabled
	public void ignoreMethod() {
		System.out.println("실행이 안된다!");
	}
	
	// @AfterAll : 해당 어노테이션이 달린 메소드가 현재 클래스의 맨 마지막에 실행
	// 				해당 메소드는 static 이어야 한다.
	//				Junit4의 @AfterClass와 동일
	@AfterAll
	public static void afterClassMethod() {
		System.out.println("제일 마지막에 실행되는 AfterClass 메소드");
	}
	
	// @AfterEach : 해당 어노테이션이 달린 메소드가 각 테스트 메소드 실행 후 실행
	//				각 테스트 진행 후에 테스트 실행 수를 세는 작업으로 많이 사용된다.
	//				Junit4의 @After와 동일
	@AfterEach
	public void afterMethod() {
		System.out.println("테스트 끝! " + (cnt++));
		System.out.println();
	}

	// @Test : 메소드가 테스트 메소드임을 나타냄
	//@Test
	public void testSum() {
		Calculator c = new Calculator();
		double result = c.sum(10, 50);
		
		// assertEquals(a, b, c) : 객체 A와 B가 일치하는지 확인
		//						a: 예상값, b: 결과값, c: 오차범위
		assertEquals(60, result, 0); // true
		
		// assertTrue(a) : 조건 a가 참인지 확인
		assertTrue(1 < 5); // true
		assertTrue(10 < 5); // false
		
		// assertNotNull(a) : 객체 a가 null이 아님을 확인
		assertNotNull(c); // true
		
		// assumptions : 특정 조건이 충족되는 경우에만 테스트 실행
		// 				실패 시 TestAbortedException 발생 -> 테스트 수행 X
		
		// Exception Testing
		// assertThrows() : 발생한 예외의 세부사항 확인, 예외 유형의 유효성 검사
		
	}
	
	// @RepeatedTest(반복횟수) : 반복횟수만큼 메소드 반복 실행
	@RepeatedTest(5)
	public void testRepeat() {
		System.out.println("메소드 반복 실행 중");
	}
	
	//@Test
	public void testArrayEquals() {
		String[] fruitsEng = {"apple", "grape", "pineapple"};
		String[] fruitsKor = {"사과", "포도", "파인애플"};
		
		// assertArrayEquals(a, b) : 배열 a와 b가 일치하는지 확인
		assertArrayEquals(fruitsEng, fruitsKor); // false
		assertArrayEquals(fruitsKor, fruitsKor); // true
	}
	
	//@Test
	public void testEquals() {
		Student student = new Student();
		Teacher teacher = new Teacher();
		
		// assertEquals(a, b) : 객체 a와 b가 일치하는지 확인
		assertEquals(student, teacher); // false
		assertEquals(student, new Student()); // false
		assertEquals(student, student); // true

		// assertSame(a, b) : 객체 a와 b가 같은 객체임을 확인
		assertSame(student, teacher); // false
		assertSame(student, new Student()); // false
		assertSame(student, student); // true
	}
	
	@Test
	public void groupAssertions() {
		int[] numbers = {0, 1, 2, 3, 4};
		// @assertAll : assertions를 그룹화 -> 그룹 내에서 실패한 assertions를
		//				MultipleFailuresError와 함께 기록
		//				실패한 지점을 정확히 파악 -> 보다 복잡한 assertion을 만들어도 안전
		assertAll("numbers",
				() -> assertEquals(numbers[0], 1),
				() -> assertEquals(numbers[3], 3),
				() -> assertEquals(numbers[4], 1));
	}
	
	// Assumptions : 특정 조건을 만족했을 경우에만 테스트 실행
	// 				실패 시 TestAbortExcpetion 발생 -> 테스트 수행 X
	@Test
	public void trueAssumption() {
		// assumeTrue : 조건이 ture일 때만 실행
		assumeTrue(5 > 3);
		assertEquals(5 + 2, 7);
	}
	
	@Test
	public void falseAssumption() {
		// assumeFalse : 조건이 false일 때만 실행
		assumeFalse(5 > 3);
		assertEquals("hi", "hi");
	}
	
	@Test
	public void assumptionThat() {
		String hi = "hi";
		assumingThat(hi.equals("hi"), () -> assertEquals(2 + 2, 4));
	}
	
	// Exception Testing
	// 1. 발생한 예외의 세부 사항을 확인
	//@Test
	public void shouldThrowException() {
		Throwable exception = assertThrows(UnsupportedOperationException.class,
				() -> {throw new UnsupportedOperationException("Not supported");
				});
		
		assertEquals(exception.getMessage(), "test");
	}
	
	// 2. 예외 유형의 유효성 검사
	@Test
	public void assertThrowsException() {
		String string = null;
		assertThrows(IllegalArgumentException.class, () -> {
			Integer.valueOf(string);
		});
	}
	
	// Test Suites : 테스트할 클래스가 하나가 아니라 여럿일 경우, 한 곳에서 할 수 있도록 다중 테스트 지원
	// @SelectPackages : Test Suites를 실행할 때 선택할 패키지 지정
	// @RunWith : 어떤 구동 클래스를 사용할 것인지 설정
	// @SelectClasses : Test Suites를 실행할 때 선택할 클래스 지정
	
	
	// @TestFactory : 동적 테스트를 위한 메소드
	//				런타임에 생성된 테스트 케이스를 선언하고 실행
	//				private 또는 static이면 안됨
	//				테스트 수는 ArrayList 크기에 따라 달라짐
	//				Stream, Collection, Iterable or Itertor를 반환해야 함
	//				BeforeEach, AfterEach가 적용되지 않는다.
}
