package com.operation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.operation.dao.PracticeDAO;

@Service
public class PracticeService {
	@Autowired
	private PracticeDAO dao;
}
