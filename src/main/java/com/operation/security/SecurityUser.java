package com.operation.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.operation.dto.MemberDTO;

public class SecurityUser extends User{
	private String loginNicname;
	
	public SecurityUser(MemberDTO user) {
		super(user.getId(), user.getPw(), AuthorityUtils.createAuthorityList(user.getRole_id()));
		this.loginNicname = user.getNickname();
	}

	public String getLoginNicname() {
		return loginNicname;
	}

	public void setLoginNicname(String loginNicname) {
		this.loginNicname = loginNicname;
	}
	
	
}
