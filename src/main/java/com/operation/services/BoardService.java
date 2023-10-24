package com.operation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.operation.dao.BoardDAO;

@Service
public class BoardService {
	@Autowired
	private BoardDAO dao;
}
