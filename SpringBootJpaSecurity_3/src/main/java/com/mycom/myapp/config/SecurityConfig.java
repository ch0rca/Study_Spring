package com.mycom.myapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// _3 프로젝트
// 사용자 정의 login.html 사용
//    /login, /login.html 을 permitAll() 추가
// csrf
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
										.requestMatchers(
											"/", 
											"/index.html",
											"/.well-known/**",
											"/login",
											"/login.html"
										).permitAll() // 로그인 필요 X
//										// Role 기반 처리, 로그인 필요
//										// 로그인 하더라도 필요한 Role 이 있어야 접근 가능
										.requestMatchers("/customer/**").hasAnyRole("CUSTOMER", "ADMIN")
										.requestMatchers("/admin/**").hasRole("ADMIN")
										.anyRequest().authenticated() // 로그인 필요
				)
				.csrf( csrf -> csrf.disable()) // csrf 처리 X  // _2 프로젝트는 security 가 제공하는 form 페이지 사용, 현재 프로젝트는 우리의 html 페이지를 사용하기 때문
				.formLogin( form -> form
									.loginPage("/login.html")
									.loginProcessingUrl("/login")
									.defaultSuccessUrl("/")
									.permitAll()
				) 
				// /logout 경로로 쵸청을 하면 LogoutFilter 가 가로채서 HttpSession invalidate, SecurityContext 의 보관 인증 정보 제거
				// /logout permitAll()
				.logout(logout -> logout.permitAll())
				.build();
	}		
}
