package com.operation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.operation.dao.GameDAO;

@Service
public class GameService {
	@Autowired
	private GameDAO dao;
}
