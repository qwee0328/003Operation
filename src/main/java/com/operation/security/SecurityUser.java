package com.operation.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.operation.dto.MemberDTO;

public class SecurityUser extends User{
	private String name;
	
	public SecurityUser(MemberDTO user) {
		super(user.getId(), user.getPw(), AuthorityUtils.createAuthorityList(user.getRole_id()));
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
}
