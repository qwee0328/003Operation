package com.operation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.operation.dao.FileDAO;

@Service
public class FileService {
	@Autowired
	private FileDAO dao;
}
