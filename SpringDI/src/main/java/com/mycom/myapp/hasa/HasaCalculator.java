package com.mycom.myapp.hasa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HasaCalculator {
	// Has A 관계의 DI 3가지 방법
	
	// #1. Field DI
//	@Autowired
//	Calculator calculator;
	
	// #2. Setter DI
//	Calculator calculator;
//	
//	@Autowired
//	public void setCalculator(Calculator calculator) {
//		this.calculator = calculator;
//	}
	
	// #3. Constructor DI <= 추천
	Calculator calculator;
	
//	@Autowired 필요 X
	public HasaCalculator(Calculator calculator) {
		this.calculator = calculator;
	}
	
	
	
	public int add(int n1, int n2) {
		System.out.println("HasaCalculator add()");
		return calculator.add(n1, n2);
	}
}
