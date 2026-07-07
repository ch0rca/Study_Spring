package com.mycom.myapp.basic;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

// junit 6 version
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // @Order 사용 가능
public class TestBasic {

	@Test
	@Order(2)
	void test1() {
		// 이곳에 테스트 코드를 작성한다. 현재는 간단 출력
		System.out.println("test1()");
	}
	
	@Test
	@Order(1)
	void test2() {
		// 이곳에 테스트 코드를 작성한다. 현재는 간단 출력
		System.out.println("test2()");
	}
	
	@Test
	@Order(3)
	@DisplayName("회원 등록 테스트") // junit 결과 dash board 에 method 이름 대신 표현할 문자열
	void test3() {
		// 이곳에 테스트 코드를 작성한다. 현재는 간단 출력
		System.out.println("test3()");
	}	
	
	// Test 의 결과
	// 성공 : Error 없이 원하는 결과 O
	// Error : 테스트 도중 예외 발생  <= 일반적으로 버그, 해결 후 다시 테스트
	// 실패 : Error 없으나 원하는 결과 X
	
	@Test
	@Order(4)
	@DisplayName("예외 테스트")
	void test4() {
		String s = null;
		s.length();
		
		// 이곳에 테스트 코드를 작성한다. 현재는 간단 출력
		System.out.println("test4()");
	}	
	
	// 테스트 시나리오 중 초기화, 정리
	// @BeforeAll, @AfterAll <= 전체 테스트 수행 중 맨 앞 한 번, 맨 뒤 한 번 각각 수행, static
	// @Test 가 없으므로 테스트 대상 X -> 대시보드에 보이지 않는다.
	// @BeforeEach, @AfterEach <= 개별 테스트 수행 마다 맨 앞 한 번, 맨 뒤 한 번 각각 수행, static X
	// @Test 가 없으므로 테스트 대상 X -> 대시보드에 보이지 않는다.	
	@BeforeAll
	static void beforeAll() {
		// 전체 테스트 전 사전 작업, 초기화 등.
		System.out.println("beforeAll()");
	}
	
	@AfterAll
	static void afterAll() {
		// 전체 테스트 후 정리 작업, 리소스 반납
		System.out.println("afterAll()");
	}
	
	@BeforeEach
	void beforeEach() {
		// 모든 개별 테스트 전 사전 작업, 초기화 등.
		System.out.println("beforeEach()");
	}
	
	@AfterEach
	void afterEach() {
		// 모든 개별 테스트 후 정리 작업, 리소스 반납
		System.out.println("afterEach()");
	}	
}
