package com.mycom.myapp.configuration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

	public static void main(String[] args) {
		// DI 이전
//		Calculator calculator = new Calculator();
//		System.out.println(calculator.add(3, 7));
		
		// Spring DI #1. xml
		// 백엔드 서버가 아닌 main() 에서 테스트
		// main() 에서 Spring Framework 과 같은 환경을 먼저 만들어 줘야 한다.
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CalcConfiguration.class);
		Calculator calculator = (Calculator) context.getBean("calculator"); // Spring 에게 객체 DI 의뢰
		System.out.println(calculator.add(3, 7));
		
		context.close();
	}

}
