package com.mycom.myapp.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

// 본격적인 테스트 모듈
// 우리가 원하는 건 단순 출력 X, 예외 발생 상황 X
// 비즈니스 로직을 구현, 테스트하는 것이 목적 => 내가 원하는 것 과 실제로 처리된 결과 비교, 검증 assert~~
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // @Order 사용 가능
public class TestAssert1 {

	// src/main/java 의 코드를 테스트 목적 <= webapp 패키지에서 진행
	// 현재는 로컬 메서드, 자료구조 등을 이용해서 assert 테스트
	
	int getA() { return 4; }
	int getB() { return 3; }
	
	@Test
	@Order(1)
	void test1() {
		// 우리의 비즈니스 로직은 getA() 에 구현, getB() 는 레거시 결과 <= 두 결과가 일치해야 한다.
		int a = getA();
		int b = getB();
		System.out.println(a);
		System.out.println(b);
//		assertEquals(a, b);  // expected, actual, 4 와 4 테스트
//		assertEquals(a, b);  // expected, actual, 4 와 3 테스트 => AssertionFailedError
//		assertEquals(a, b, "a 와 b 는 같아야 한다.");  // expected, actual, failure message, 4 와 3 테스트 => AssertionFailedError
		assertEquals(getA(), getB(), "a 와 b 는 같아야 한다.");  // expected, actual, failure message, 4 와 3 테스트 => AssertionFailedError
	}
	
	@Test
	@Order(2)
	void test2() {
		assertNotEquals(getA(), getB(), "a 와 b 는 달라야 한다.");  
	}
	
	boolean getResult() { return true; }
	boolean result = false;
	
	@Test
	@Order(3)
	void test3() {
		// 이렇게 작성 X, 테스트가 동일 대상으로 다르게 진행
//		assertEquals(result, getResult()); // expected, actual
		assertTrue(getResult()); // actual, expected 는 true 고정
//		assertFalse(getResult()); // actual, expected 는 false 고정
	}
	
//	String getString() { return "123"; }
	String getString() { return null; }
	
	@Test
	@Order(4)
	void test4() {
		assertNull( getString(), "결과가 Null 이어야 한다.");
	}
	
	@Test
	@Order(5)
	void test5() {
		assertNotNull( getString(), "결과가 Not Null 이어야 한다.");
	}
}

	
	













