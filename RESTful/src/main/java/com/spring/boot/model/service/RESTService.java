package com.spring.boot.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.model.dao.RESTDao;
import com.spring.boot.model.vo.Member;

@Service
public class RESTService {

	@Autowired
	private RESTDao restDao;
	
	public int join(Member member) {
		return restDao.join(member);
	}
	
	public Member login(Member member) {
		return restDao.login(member);
	}
	
	public int changePassword(Member member) {
		return restDao.changePassword(member);
	}
	
	public int withdraw(String id) {
		return restDao.withdraw(id);
	}
}
