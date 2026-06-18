<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mycom.myapp.user.dto.UserDto" %>
<%
	UserDto userDto = (UserDto) session.getAttribute("userDto");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

<title>게시판</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg bg-light">
	  <div class="container">
	    <a class="navbar-brand" href="#">
			<img src="/assets/img/user/<%= userDto.getUserProfileImage()%>" style="width: 32px; height: 32px; border-radius: 50%;">
		</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	        <li class="nav-item">
	          <a class="nav-link active" aria-current="page" href="#">Home</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="#">Link</a>
	        </li>
	      </ul>
		  <ul class="navbar-nav">
  	        <li class="nav-item">
			  <!-- a 태그를 클릭 -> 페이지 요청 -> 페이지 요청을 처리하는 Controller 에서 Session Invalidate & Login Page 이동 -->
			  <!-- 단순 페이지 이동이 아니라, 마지막 로그인시각 기록 등 별도의 백엔드 처리가 필요하다. 
				   페이지 요청 처리 Controller 보다 Login Controller 에 데이터 요청 응답 받아서 처리 (Javascript ) 더 효율적이다. -->
  	          <a class="nav-link" href="/pages/logout">Logout</a>
  	        </li>
  	      </ul>		  
	    </div>
	  </div>
	</nav>
	
	
</body>
</html>