package com.mycom.myapp.user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.dto.UserResultDto;
import com.mycom.myapp.user.entity.User;
import com.mycom.myapp.user.entity.UserRole;
import com.mycom.myapp.user.repository.UserRepository;
import com.mycom.myapp.user.repository.UserRoleRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
	private final UserRoleRepository userRoleRepository;
	
	@Override
	@Transactional
	public UserResultDto insertUser(UserDto userDto) {
		UserResultDto userResultDto = new UserResultDto();

		// User 의 Role 처리
		// B2C 회원 가입 - 사용자가 직접 회원 가입 처리 - 일반 사용자 Role
		// ERP 인사팀 사원 등록 - Role 과 연동 복잡 - 팀 + Role 고려 => IT
		// User Role 의 "IT" 문자열 => User Role 영속화 필요 => UserRoleRepository 필요
		
		// #1. userDto 의 email 검증
		if( userRepository.existsByEmail(userDto.getEmail()) ) {
			// 이미 회원가입, 사원등록 된 사용자 회원가입은 실패
			userResultDto.setResult("fail");
			return userResultDto;
		}
		
		// #2. IT UserRole 이름으로 find(), UserDto -> User 변환
//		User user = new User();
//		user.fromUserDto(userDto);
//		
//		List<UserRole> userRoles = List.of(userRoleRepository.findByName("IT"));
//		user.setUserRoles(userRoles);
//		userRepository.save(user);
		
/*
Hibernate: select u1_0.id from user u1_0 where u1_0.email=? limit ?
Hibernate: select ur1_0.id,ur1_0.name from user_role ur1_0 where ur1_0.name=?
Hibernate: insert into user (email,name,password) values (?,?,?)
Hibernate: insert into user_user_roles (user_id,user_roles_id) values (?,?)		
 */
		
		// #3. test_role 이름의 새로운 role 을 회원가입, 사원등록과 함께 신규 추가
		//     회원가입마다 test_role 이 추가되는 상황 고려 (인지) <= 시나리오를 간단하게 하려는 목적
		//     UserRole 영속화 X => TransientPropertyValueException 예외 발생
//		UserRole userRole = new UserRole();
//		userRole.setName("test_role");
//		
//		User user = new User();
//		user.fromUserDto(userDto);
//		
//		List<UserRole> userRoles = List.of(userRole);
//		user.setUserRoles(userRoles);
//		
//		userRepository.save(user);
		
		// #4. test_role 이름의 새로운 role 을 회원가입, 사원등록과 함께 신규 추가
		//     회원가입마다 test_role 이 추가되는 상황 고려 (인지) <= 시나리오를 간단하게 하려는 목적
		//     userRoleRepository 를 이용해서 UserRole 영속화
//		UserRole userRole = new UserRole();
//		userRole.setName("test_role");
//		
//		User user = new User();
//		user.fromUserDto(userDto);
//		
//		List<UserRole> userRoles = List.of(userRole);
//		user.setUserRoles(userRoles);
//		
//		userRoleRepository.save(userRole); // UserRole 영속화
//		userRepository.save(user);
//		
//		userResultDto.setResult("success");
		
/*
Hibernate: select u1_0.id from user u1_0 where u1_0.email=? limit ?
Hibernate: insert into user_role (name) values (?)
Hibernate: insert into user (email,name,password) values (?,?,?)
Hibernate: insert into user_user_roles (user_id,user_roles_id) values (?,?)		
 */
		// #5. test_role 이름의 새로운 role 을 회원가입, 사원등록과 함께 신규 추가
		//     회원가입마다 test_role 이 추가되는 상황 고려 (인지) <= 시나리오를 간단하게 하려는 목적
		//     User 에 cascade=CascadeType.PERSIST 추가
		//     userRoleRepository 를 이용해서 UserRole 영속화 X
//		UserRole userRole = new UserRole();
//		userRole.setName("test_role");
//		
//		User user = new User();
//		user.fromUserDto(userDto);
//		
//		List<UserRole> userRoles = List.of(userRole);
//		user.setUserRoles(userRoles);
//		
////		userRoleRepository.save(userRole); // UserRole 영속화 X
//		userRepository.save(user);
//		
//		userResultDto.setResult("success");
/*
Hibernate: select u1_0.id from user u1_0 where u1_0.email=? limit ?
Hibernate: insert into user (email,name,password) values (?,?,?)
Hibernate: insert into user_role (name) values (?)
Hibernate: insert into user_user_roles (user_id,user_roles_id) values (?,?)		
 */
		
		//////////////// OneToMany cascade=CascadeType.PERSIST)  <= 실무에서 사용 X, 학습용 !!!!!
		
		//	OneToMany cascade=CascadeType.PERSIST 은 원복 (사용 x)
		//////////////// Transaction 처리
		
		// #a. test_role 이름의 새로운 role 을 회원가입, 사원등록과 함께 신규 추가
		//     회원가입마다 test_role 이 추가되는 상황 고려 (인지) <= 시나리오를 간단하게 하려는 목적
		//     userRoleRepository 를 이용해서 UserRole 영속화
		//     @Transactional 없으면 UserRole insert O, User insert X
		//     @Transactional 있으면 UserRole insert O 가 Rollback 처리		
//		UserRole userRole = new UserRole();
//		userRole.setName("test_role");
//		
//		User user = new User();
//		user.fromUserDto(userDto);
//		
//		List<UserRole> userRoles = List.of(userRole);
//		user.setUserRoles(userRoles);
//		
//		userRoleRepository.save(userRole); // UserRole 영속화
//		
//		// NullPointerException 발생
//		String s = null;
//		s.length();
//		
//		
//		userRepository.save(user);
		
		// #b. try-catch 적용
		//     예외가 발생하더라도 userResultDto 객체로 응답 하려고 함.
		//	   @Transactional 있어도 UserRole insert O, User inser X <= Rollback 처리 실패
		//     발생한 예외가 Transaction 을 관리하는 Proxy 에게 전달 X <= catch 로 차단
		//     catch 에 throw 예외 <= try-catch 적용 전과 동일한 상황
		//     TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); 를 catch 에 추가
		//     try-catch 사용하면서도 rollback 에 대한 처리 가능
		try {
			UserRole userRole = new UserRole();
			userRole.setName("test_role");
			
			User user = new User();
			user.fromUserDto(userDto);
			
			List<UserRole> userRoles = List.of(userRole);
			user.setUserRoles(userRoles);
			
			userRoleRepository.save(userRole); // UserRole 영속화
			
			// NullPointerException 발생
//			String s = null;
//			s.length();
						
			userRepository.save(user);
			userResultDto.setResult("success");	
			
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			userResultDto.setResult("fail");	
		}

		
		
		return userResultDto;
	}

}

















