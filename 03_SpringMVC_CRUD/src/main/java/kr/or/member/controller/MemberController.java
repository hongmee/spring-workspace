package kr.or.member.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.or.member.model.service.MemberService;
import kr.or.member.model.vo.Member;

@Controller // Spring 객체
public class MemberController {
	
	@Autowired // 해당 타입 객체를 컨테이너에서 찾아 자동으로 주입
	private MemberService service;
	
	public MemberController() {
		super();
		System.out.println("MemberController 생성 완료!");
	}
	
	@RequestMapping(value="/searchMember.do")
	// 서블릿 생성할 필요 없음
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

	@RequestMapping(value="/allMember.do")
	public String allMember(Model model) {
		ArrayList<Member> list = service.selectAllMember();
		model.addAttribute("list", list);
		
		return "member/allMember";
		
	}
	
	@RequestMapping(value="/joinFrm.do")
	public String joinFrm() {
		// 회원 가입 양식이 있는 페이지로 바로 이동
		return "member/joinFrm";
	}
	
	@RequestMapping(value="/join.do")
	public String join(Member m) {
		// joinFrm에서 name 속성 다르게 가져오면 null
//		System.out.println(m);
		
		int result = service.insertMember(m);
		
		if(result > 0) {
			// viewresolver를 거치지 않고 페이지를 이동하고 싶은 경우 (다른 컨트롤러로 호출하고 싶은 경우)
			return "redirect:/"; // 메인
		}else {
			return "member/joinFrm";			
		}
	}
	
	@RequestMapping(value="/login.do")
	public String login(Member member, HttpSession session) {
		// (String memberId, String memberPw, HttpServletRequest request)
		Member loginMember = service.selectOneMember(member);
		
		if(loginMember != null) {
		// HttpSession session = request.getSession();
			session.setAttribute("m", loginMember);
		}
		return "redirect:/";			
	}
	
	@RequestMapping(value="/logout.do")
	public String logout(HttpSession session) {
		// 세션 파기
		session.invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/mypage.do")
	public String mypage() {
		return "member/mypage";
	}
	
	
	/*
	 @SessionAttribute(required = false) Member m
	 : 세션에서 이름이 m인 객체를 꺼내서 Member타입으로 저장
	 : required = false : 이름이 m인 객체가 없으면 null을 꺼내옴
	 -> required = false가 없는 경우 이름이 m인 객체가 없으면 에러 발생
	*/
	
	@RequestMapping(value="/updateMember.do")
	public String updateMember(Member member, @SessionAttribute(required = false ) Member m) {
		// HttpSession session의 Member m
		int result = service.updateMember(member);
		
		if(result > 0) {
			// 세션 갈아끼우기
			//Member m = (Member)session.getAttribute("m");
			m.setMemberPw(member.getMemberPw());
			m.setMemberPhone(member.getMemberPhone());
			m.setMemberEmail(member.getMemberEmail());
//			session.setAttribute("m", member);
			
			return "redirect:/mypage.do";
		}else {
			System.out.println("정보수정실패");
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="/deleteMember.do")
	public String deleteMember(int memberNo) {
		int result = service.deleteMember(memberNo);
		
		if(result > 0) {
			return "redirect:/logout.do";
		}else {
			return "redirect:/";
		}
	}
	
}
