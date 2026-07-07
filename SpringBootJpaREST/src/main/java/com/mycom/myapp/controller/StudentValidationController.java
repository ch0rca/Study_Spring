package com.mycom.myapp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myapp.dto.StudentDto;
import com.mycom.myapp.dto.StudentResultDto;
import com.mycom.myapp.dto.StudentValidationDto;
import com.mycom.myapp.service.StudentServiceCrud;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

// 클라이언트가 보내는 StudentDto 생성을 위한 파라미터 값들의 유효성 검사를 담당
// StudentDto 전에 StudentDto 와 유사한 별도의 Validation 규칙을 가진 Dto 생성
// Validation 성공 O : Dto -> StudentDto 변환
// Validation 성공 X : MethodArgumentNotValidException 예외 자동 발생
//						대응 코드를 GlobalExceptionHandler 에서 처리	
// 백엔드의 유효성 검사의 장, 단점
// 장점 : 프론트와 별개의 백엔드의 데이터 검증 - 비관적 입장
// 단점 : 프론트와 항상 유해성 로직 일치 유지 - 유효성 검사가 없다면 낙관적 입장

// studentValidation.html 과 대응
@RestController
@RequestMapping("/api/validation")
@RequiredArgsConstructor
public class StudentValidationController {
	// 생성자 DI with Lombok
	private final StudentServiceCrud studentServiceCrud;
	
	// 등록
	@PostMapping("/students")
	public StudentResultDto insertStudent(
			@Valid @RequestBody StudentValidationDto studentValidationDto) {
		// Validation 통과 
		// StudentValidationDto -> StudentDto 생성
		StudentDto studentDto = StudentDto.builder()
						.name(studentValidationDto.getName())
						.email(studentValidationDto.getEmail())
						.phone(studentValidationDto.getPhone())
						.build();
		
		return studentServiceCrud.insertStudent(studentDto);
	}
}
