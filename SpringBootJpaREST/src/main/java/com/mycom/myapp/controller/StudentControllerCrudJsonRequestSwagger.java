package com.mycom.myapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mycom.myapp.dto.StudentDto;
import com.mycom.myapp.dto.StudentResultDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

// Swagger 전용 Interface
// 이 인터페이스를 구현하는 클래스 코드레벨에 Doc 관련 annotation 을 분리 운영
@Tag(name="JSON Student CRUD REST API", description="JSON 요청을 통해 Student 의 등록, 수정, 삭제, 조회, 상세 조회 기능을 REST API 로 제공합니다.")
public interface StudentControllerCrudJsonRequestSwagger {
	// 목록
	@Operation(summary="학생 목록", description="전체 학생 목록을 응답합니다.")
	@GetMapping("/students")
	StudentResultDto listStudent();

	// 등록
	@Operation(summary="학생 등록", description="JSON 요청을 통해 신규 학생 1명을 등록합니다.")
	@PostMapping("/students")
	StudentResultDto insertStudent(StudentDto studentDto);
	
}
