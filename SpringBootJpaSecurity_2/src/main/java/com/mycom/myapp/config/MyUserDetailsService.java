package com.mycom.myapp.config;

import java.util.Collection;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService{

	// UserDetails 인터페이스는 
	//   Collection<? extends GrantedAuthority> getAuthorities(); @Nullable String getPassword(); String getUsername(); 추상 메소드를 가지고 있다.
	//   Spring Security 는 사용자 인증에 사용하기 위해, UserDetails 를 구현한 클래스의 객체를 요구하고, 이를 통해서 인증 및 권한 처리
	// 우리가 직접 UserDetails 인퍼페이스를 구현한 클래스를 정의해도 된다.
	// 현 단계에서는 Spring Security 에서 제공하는 org.springframework.security.core.userdetails.User; 를 사용한다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 일반적으로 사용자 DB Accsss 를 통한 UserDetails 객체를 생성, 리턴
		// 현재 프로젝트에서는 하드 코딩
		// 	=> admin/1234, customer/1234
		return switch(username) {
			case "admin" -> User.builder()
								.username("admin")
								.password(DemoPasswords.ENCODED)
								.roles("ADMIN")
								.build();
			case "customer" -> User.builder()
								.username("customer")
								.password(DemoPasswords.ENCODED)
								.roles("CUSTOMER")
								.build();	
			default -> throw new UsernameNotFoundException("User Not Found");
		};
	}

}
