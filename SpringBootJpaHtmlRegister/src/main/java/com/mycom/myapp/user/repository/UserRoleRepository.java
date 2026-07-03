package com.mycom.myapp.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycom.myapp.user.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer>{
	// 기본 crud 는 자동 구현
	
	// 문자열 (이름) 으로부터 UserRole 가져오는 findBy~
	// Optional X
	UserRole findByName(String name);
}
