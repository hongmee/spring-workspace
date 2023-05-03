package kr.or.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.manager.model.service.ManagerService;

@Controller
public class ManagerController {

	@Autowired
	private ManagerService service;
	
	@RequestMapping(value="/managerRegist.do")
	public String managerRegist() {
		return null;
	}
	
	@RequestMapping(value="/managerList.do")
	public String managerList() {
		return null;
	}
	
	@RequestMapping(value="/managerUpdate.do")
	public String managerUpdate() {
		return null;
	}
	
	@RequestMapping(value="/managerDelete.do")
	public String managerDelete() {
		return null;
	}
	
}
