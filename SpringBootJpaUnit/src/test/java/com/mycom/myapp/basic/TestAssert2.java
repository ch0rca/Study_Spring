package com.mycom.myapp.basic;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

// 본격적인 테스트 모듈
// 우리가 원하는 건 단순 출력 X, 예외 발생 상황 X
// 비즈니스 로직을 구현, 테스트하는 것이 목적 => 내가 원하는 것 과 실제로 처리된 결과 비교, 검증 assert~~
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // @Order 사용 가능
public class TestAssert2 {

	// src/main/java 의 코드를 테스트 목적 <= webapp 패키지에서 진행
	// 현재는 로컬 메서드, 자료구조 등을 이용해서 assert 테스트
	
	// 예외 발생
	int getStringLength(String str) { return str.length(); }
	
	// assertThrows() 의 주요한 활용은 사용자 정의 예외 발생 확인 테스트
	@Test
	@Order(1)
	void test1() {
//		String str = "hello";
		String str = null;
//		assertThrows(NullPointerException.class, getStringLength(str), "NullPointerException 이 발생되어야 한다."); // lambda 사용해야 한다.
		assertThrows(NullPointerException.class, () -> getStringLength(str), "NullPointerException 이 발생되어야 한다.");
	}
	
	// 묶음 (그룹) 테스트
	int result = 0;
	int m1() { return 4; }
//	boolean m2() { return false; }
	boolean m2() { return true; }
	String m3() { return "hello"; }
	
	@Test
	@Order(2)
	void test2() {
		assertAll("묶음 테스트", 
			() -> assertEquals(4, m1()),
			() -> assertTrue(m2()),
			() -> assertNotNull( m3()),
			() -> assertEquals(0, result)
		);
	}
	
	// 배열 Array
	// int 가 아닌 객체 배열을 경우, 길이가 같아야 되고, 위치가 같은 객체의 두 equals & hashCode 의 결과가 같아야 한다.
	int[] expectedArray = {1, 2, 3};
//	int[] actualArray = {1, 2, 3};
//	int[] actualArray = {1, 2, 3, 4}; // 길이가 다르면 실패
	int[] actualArray = {1, 2, 4}; // 개별 항목이 다르면 실패
	
	@Test
	@Order(3)
	void test3() {
		assertArrayEquals(expectedArray, actualArray);
	}
	
	// Collection
	List<String> expectedList = List.of("abc", "def");
//	List<String> actualList = List.of("abc", "def");
//	List<String> actualList = List.of("def", "abc"); // 순서
//	List<String> actualList = List.of("abc", "def", "xyz"); // 갯수
	List<String> actualList = List.of("abc", new String("def"));
	
	@Test
	@Order(4)
	void test4() {
		assertIterableEquals(expectedList, actualList);
	}
	
	// 객체 비교
	@Test
	@Order(5)
	void test5() {
		String str1 = "Hello";
//		String str2  = str1;
		String str2  = new String("Hello");
		
		assertEquals(str1, str2);
	}
	
	@Test
	@Order(6)
	void test6() {
		String str1 = "Hello";
//		String str2  = str1;
		String str2  = new String("Hello");

		assertSame(str1, str2);  // Singleton Design Pattern 적용 객체 테스트
	}
	
	
	// 수행 시간 테스트
	// 작성한 BL 처리 메소드
	// Service -> Repository
	void testBL() {
		// 3초 대기 후 진행
		try {
			Thread.sleep(3000);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("testBL()");
	}
	
	@Test
	@Order(7)
	void test7() {
		assertTimeout(Duration.ofSeconds(1), () -> testBL(), "1초 미만 수행 테스트");
	}
}

	
	













