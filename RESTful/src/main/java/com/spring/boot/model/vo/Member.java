package com.spring.boot.model.vo;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

@Component
@Alias("member")
public class Member {
	private String memId;
	private String memPw;

	public Member() {
		super();
	}

	public Member(String memId, String memPw) {
		super();
		this.memId = memId;
		this.memPw = memPw;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPw() {
		return memPw;
	}

	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}

	@Override
	public String toString() {
		return "Member [memId=" + memId + ", memPw=" + memPw + "]";
	}

}
