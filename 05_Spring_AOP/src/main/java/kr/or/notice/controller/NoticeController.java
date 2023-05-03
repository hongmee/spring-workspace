package kr.or.notice.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.notice.model.service.NoticeService;
import kr.or.notice.model.vo.Notice;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService service;
	
	@RequestMapping(value="/noticeList.do")
	public String noticeList(Model model) {
		ArrayList<Notice> list = service.selectNoticeList();
		
		model.addAttribute("list", list);
		
		return "notice/noticeList";
	}
	
	@RequestMapping(value="/noticeWriteFrm.do")
	public String noticeFrm() {
		return "notice/noticeWriteFrm";
	}
	
	@RequestMapping(value="/noticeWrite.do")
	public String insertNotice(Notice n) {
		int result = service.insertNotice(n);
		
		if(result > 0) {
			return "redirect:/noticeList.do";
		}else {
			return "redirect:/noticeWriteFrm.do";			
		}
	}
	
	@RequestMapping(value="/noticeView.do")
	public String noticeView(int noticeNo, Model model) {
		Notice n = service.selectOneNotice(noticeNo);
		
		if(n != null) {
			model.addAttribute("n", n);
			return "notice/noticeView";
		}else {
			return "redirect:/noticeList.do";
		}
	}
	
	@RequestMapping(value="/noticeUpdateFrm.do")
	public String noticeUpdateFrm(int noticeNo, Model model) {
		
		Notice n = service.selectOneNotice(noticeNo);
		
		if(n != null) {
			model.addAttribute("n", n);
			return "notice/noticeUpdateFrm";			
		}else {
			return "redirect:/noticeView.do?noticeNo="+noticeNo;
		}
	}
	
	@RequestMapping(value="/updateNotice.do")
	public String updateNotice(Notice notice) {
		
		int result = service.updateNotice(notice);

		if(result > 0) {
			return "redirect:/noticeView.do?noticeNo="+notice.getNoticeNo();			
		}else {
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="/deleteNotice.do")
	public String deleteNotice(int noticeNo) {
		int result = service.deleteNotice(noticeNo);
		
		if(result > 0) {
			return "redirect:/noticeList.do";			
		}else {
			return "redirect:/noticeView.do?noticeNo="+noticeNo;
		}
	}
}
