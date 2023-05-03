package kr.or.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.student.model.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	private StudentService service;
	
	@RequestMapping(value="/studentRegist.do")
	public String studentRegist() {
		return null;
	}
	
	@RequestMapping(value="/studentList.do")
	public String studentList() {
		return null;
	}
	
	@RequestMapping(value="/studentUpdate.do")
	public String studentUpdate() {
		return null;
	}
	
	@RequestMapping(value="/studentDelete.do")
	public String studentDelete() {
		return null;
	}
}
