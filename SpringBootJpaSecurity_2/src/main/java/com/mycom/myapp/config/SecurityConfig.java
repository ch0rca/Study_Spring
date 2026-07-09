package com.mycom.myapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// _2 프로젝트
// UserDetailsService 도입 - DB 연동 X, 하드 코딩
//    => DB 연동 기본 : password 암호화, 암호화된 password 비교
//                    1. 회원가입 : 사용자가 1234 패스워드 등록 -> BCrypt (1234) -> 23fasadr3wfasdfasadfasdfasdfas (테이블에 저장)
//					  2. 로그인   : 사용자가 123 패스워드 사용  -> BCrypt (123)  ->  asdfasdfasd234sadfasdf333r3f (테이블에 저장된 내용과 비교 로그인 실패)
//                                사용자가 123 패스워드 사용  -> BCrypt 복호화 (3fasadr3wfasdfasadfasdfasdfas (테이블에 저장)) -> 1234 비교 X
//    => PasswordEncoder DI 제공 ( BCryptPasswordEncoder )
// Role 기반 처리
//    => ADMIN, CUSTOMER
//    => ADMIN 모두 접근, CUSTOMER 는 일부 접근 <= Role 에 기반한 구별된 접근 권한
// 아래 설정만 하고 /admin/hello 등 요청하면 => Forbidden, status=403
@Configuration
public class SecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		// "/", "/index.html", "/.well-known/**" 허락
		// /.well-known/** <= Chrome 등 다양한 브라우저가 원활한 서버와 통신을 위해서 사용자의 요청 외, 다양한 요청 시도 <= 이 요청들이 가지는 패턴
		return http.authorizeHttpRequests( request -> request
										.requestMatchers("/", "/index.html", "/.well-known/**").permitAll() // 로그인 필요 X
//										// Role 기반 처리, 로그인 필요
//										// 로그인 하더라도 필요한 Role 이 있어야 접근 가능
										.requestMatchers("/customer/**").hasAnyRole("CUSTOMER", "ADMIN")
										.requestMatchers("/admin/**").hasRole("ADMIN")
										.anyRequest().authenticated() // 로그인 필요
				)
				// 로그인 성공하면 / 로 이동, 두번째 alwaysUse 파라미터를 true 로 설정하면 무조건 /
				//                       두번째 alwaysUse 파라미터가 생략되면 요청 url 이 있었으면 그 요청 url 로 이동, 없으면 / 이동
				.formLogin( form -> form.defaultSuccessUrl("/", true).permitAll()) 
				// /logout 경로로 쵸청을 하면 LogoutFilter 가 가로채서 HttpSession invalidate, SecurityContext 의 보관 인증 정보 제거
				// /logout permitAll()
				.logout(logout -> logout.permitAll())
				.build();
	}		
}
