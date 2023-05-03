package kr.or.dm.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import kr.or.dm.model.service.DmService;
import kr.or.dm.model.vo.DirectMessage;

@Controller
public class DmController {
	@Autowired
	private DmService service;

	@RequestMapping(value="/dmMain.do")
	public String dmMain() {
		return "dm/dmMain";
	}
	
	@ResponseBody // ajax는 페이지 이동이 필요없으므로 써야함
	@RequestMapping(value="/insertDm.do")
	public String insertDm(DirectMessage dm) {
		int result = service.insertDm(dm);
		
//		if(result > 0) {
//			return "1";
//		}else {
//			return "0";
//		}
		return String.valueOf(result);
	}

	@ResponseBody
	@RequestMapping(value="myDmList.do", produces = "application/json;charset=utf-8")
	public String myDmList(DirectMessage dm) {
		ArrayList<DirectMessage> list = service.selectDmList(dm);
		
		return new Gson().toJson(list);
	}
	
	@ResponseBody
	@RequestMapping(value="/dmDetail.do", produces = "application/json;charset=utf-8")
	public String dmDetail(int dmNo) {
		DirectMessage dm = service.selectOneDm(dmNo);
		
		return new Gson().toJson(dm);
	}
	
	
}
