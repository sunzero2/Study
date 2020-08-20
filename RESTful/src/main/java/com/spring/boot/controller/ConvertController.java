package com.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ConvertController {
	
	@RequestMapping("/login")
	public String moveLoginPage() {
		return "login";
	}
	
	@RequestMapping("/signup")
	public String moveJoinPage() {
		return "join";
	}
	
	@RequestMapping("/mypage")
	public String moveMyPage() {
		return "mypage";
	}
}
