package com.operation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.operation.dao.KioskDAO;

@Service
public class KioskService {
	@Autowired
	private KioskDAO dao;
	
	
}
