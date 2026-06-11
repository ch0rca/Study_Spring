package com.mycom.myapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Java Configuration DI 는 일반 비즈니스 로직을 위한 객체 대상이 아닌
// 스프링 환경 설정과 관련된 객체 DI 를 위해 일반적으로 사용
@Configuration
public class CalcConfiguration {

	@Bean
	Calculator calculator() { // method 이름
		return new Calculator();
	}
}
