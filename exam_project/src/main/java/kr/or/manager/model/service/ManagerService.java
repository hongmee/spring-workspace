package kr.or.manager.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.manager.model.dao.ManagerDao;

@Service
public class ManagerService {
	
	@Autowired
	private ManagerDao dao;
}
