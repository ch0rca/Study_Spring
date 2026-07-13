package com.mycom.myapp.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mycom.myapp.user.entity.User;
import com.mycom.myapp.user.entity.UserRole;
import com.mycom.myapp.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

// 1. dskim/1234 hard coding -> DB Access
// 2. DB Access 는 username 에 해당하는 필드를 우리는 email 사용 <= UserRepository 에 findByEmail()
// 3. UserDetails 구현 객체 ( Spring Security Format, Spring Security 인식하는 표준 User 객체 )
// 4. UserDetails 구현 클래스를 직접 작성 X, Spring Security 의 User 를 대신 사용
// 5. 우리 코드의 User 와 Spring Security 의 User 를 구별
@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	
	// 사용자에게 로그인 창에서 email, password 방식을 사용
	// email <- username 이 동일.
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<User> optionalUser = userRepository.findByEmail(email);
		
		if( optionalUser.isPresent() ) {
			User user = optionalUser.get();
			List<UserRole> listUserRole = user.getUserRoles();
			
			List<String> roleStrList = new ArrayList<>();
			listUserRole.forEach( userRole -> roleStrList.add(userRole.getName()));
			String[] roleStrArray = roleStrList.toArray(new String[0]); // 최종적으로 List<UserRole> -> List<String> -> String[]
			
			// UserDetails 구현 객체를 생성, return
			return org.springframework.security.core.userdetails.User.builder()
						.username(user.getEmail()) // username 필드 <= email
						.password(user.getPassword()) // BCrypt encoded 된 비밀번호 이고 이걸 사용자가 입력한 비밀번호를 encoding 해서 비교
						.roles(roleStrArray)
						.build();
		}
		
		throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
	}



}
