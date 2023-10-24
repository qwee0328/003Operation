package com.operation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.operation.dao.ReplyDAO;

@Service
public class ReplyService {
	@Autowired
	private ReplyDAO dao;
}
