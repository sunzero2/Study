package com.spring.boot.model.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.boot.model.vo.Member;

@Repository
public class RESTDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int join(Member member) {
		return sqlSession.insert("createMember", member);
	}
	
	public Member login(Member member) {
		return sqlSession.selectOne("readMember", member);
	}
	
	public int changePassword(Member member) {
		return sqlSession.update("updateMember", member);
	}
	
	public int withdraw(String id) {
		return sqlSession.delete("deleteMember", id);
	}
}
