package com.mycom.myapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

// 개별 컨트롤러 레벨이 아닌 전체 문서 관련 설정
@Configuration
public class SwaggerConfig {

	@Bean
	OpenAPI openApi() {
		return new OpenAPI()  // OpenAPI 루트 객체
					.components(new Components())
					.info(apiInfo());
	}
	
	private Info apiInfo() { // Info Swagger UI 문서 최상단에 표시되는 API 정보 (Info 색션)
		return new Info()
					.title("학생 관리 API")
					.description("REST API 로 구현딘 학생 관리 기능을 테스트합니다.")
					.version("v0.9");
	}
}
