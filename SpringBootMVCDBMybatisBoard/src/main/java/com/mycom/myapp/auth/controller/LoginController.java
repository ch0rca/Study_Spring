package com.mycom.myapp.auth.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycom.myapp.user.dto.UserDto;

@Controller
@ResponseBody
@RequestMapping("/auth")
public class LoginController {

	@PostMapping("/login")
	public Map<String, String> login(UserDto userDto){
		Map<String, String> map = new HashMap<>();
		map.put("result", "success");
		return map;
	}
}
