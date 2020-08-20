package com.spring.boot.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.cj.x.protobuf.MysqlxCrud.UpdateOperation.UpdateType;
import com.spring.boot.model.service.RESTService;
import com.spring.boot.model.vo.Member;

@Controller
public class RESTController {
	
	@Autowired
	private RESTService restService;
	
	@PostMapping("/members/signup")
	public String join(String id, String pw) {
		Member member = new Member();
		member.setMemId(id);
		member.setMemPw(pw);
		
		restService.join(member);
		
		return "index";
	}
	
	@PostMapping("/members/login")
	public String login(String id, String pw, HttpSession session) {
		Member member = new Member();
		member.setMemId(id);
		member.setMemPw(pw);
		
		member = restService.login(member);
		
		if(member != null) {
			session.setAttribute("login", member);
		}
		return "index";
	}
	
	@PutMapping("/members/{id}")
	@ResponseBody
	public int changePassword(@PathVariable("id") String id, String pw) {
		Member member = new Member();
		member.setMemId(id);
		member.setMemPw(pw);
		
		return restService.changePassword(member);
	}
	
	@DeleteMapping("/members/{id}")
	public String withdraw(@PathVariable("id") String id, HttpSession session) {
		System.out.println(id);
		int result = restService.withdraw(id);
		System.out.println(result);
		if(result > 0) {
			session.removeAttribute("login");
		}
		
		return "index";
	}
}
