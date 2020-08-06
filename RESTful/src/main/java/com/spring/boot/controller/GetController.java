package com.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetController {

	@GetMapping("/get-test")
	public String getTest(Model model) {
		model.addAttribute("id", "getUser");
		return "get";
	}
}
