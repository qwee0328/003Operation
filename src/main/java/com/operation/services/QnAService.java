package com.operation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.operation.dao.QnADAO;

@Service
public class QnAService {
	@Autowired
	private QnADAO dao;
}
