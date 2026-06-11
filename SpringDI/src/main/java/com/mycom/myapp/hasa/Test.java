package com.mycom.myapp.hasa;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		// DI 이전
//		Calculator calculator = new Calculator();
//		System.out.println(calculator.add(3, 7));
		
		// Spring DI #1. xml
		// 백엔드 서버가 아닌 main() 에서 테스트
		// main() 에서 Spring Framework 과 같은 환경을 먼저 만들어 줘야 한다.
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("xml/hasa-calc-annotation.xml");
		HasaCalculator hasaCalculator = (HasaCalculator) context.getBean("hasaCalculator"); // Spring 에게 객체 DI 의뢰
		System.out.println(hasaCalculator.add(3, 7));
		
		context.close();
	}

}
