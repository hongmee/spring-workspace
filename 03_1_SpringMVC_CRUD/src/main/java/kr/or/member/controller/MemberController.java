package kr.or.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.member.model.service.MemberService;
import kr.or.member.model.vo.Member;

@Controller // Spring 객체로 생성
public class MemberController {

	@Autowired 	// service를 자동으로 주입
	private MemberService service;

	public MemberController() {
		super();
		System.out.println("MemberController 생성 완료");
	}
	
	@RequestMapping(value="/searchMember.do")
	// 매핑 설정, 서블릿 필요없음
	public String searchMember(String memberId, Model model) {
		// memberId 값 추출
		
		// 3. 비즈니스 로직
		Member member = service.selectOneMember(memberId);
		
		// 4. 결과 처리
		if(member == null) {
			return "member/searchFail";
		}else {
			model.addAttribute("member", member); // request.setAttribute
			return "member/searchMember";
		}
	}
	
}
