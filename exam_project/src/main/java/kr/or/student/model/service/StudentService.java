package kr.or.student.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.student.model.dao.StudentDao;

@Service
public class StudentService {

	@Autowired
	private StudentDao dao;
}
