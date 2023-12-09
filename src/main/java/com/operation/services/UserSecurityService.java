package com.operation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.operation.dao.MemberDAO;
import com.operation.dto.MemberDTO;
import com.operation.security.SecurityUser;

import jakarta.servlet.http.HttpSession;

@Service
public class UserSecurityService implements UserDetailsService{
	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println(username+"UserSecurityService");
		MemberDTO user = dao.selectInfoById(username);
		SecurityUser su = new SecurityUser(user);
		su.setLoginNicname(user.getNickname());
		
		session.setAttribute("loginID", user.getId());
		session.setAttribute("loginNickName", user.getNickname()); // 닉네임
		return su;
	}
}
