package com.operation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.operation.dao.MemberDAO;

@Service
public class MemberService {
	@Autowired
	private MemberDAO dao;
}
