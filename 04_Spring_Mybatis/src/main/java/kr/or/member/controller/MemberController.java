package kr.or.member.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.or.member.model.service.MemberService;
import kr.or.member.model.vo.Member;

@Controller
public class MemberController {

	@Autowired
	private MemberService service;

	public MemberController() {
		super();
	}

	@RequestMapping(value="/login.do")
	public String login(Member member, HttpSession session) {
		
		Member loginMember = service.selectOneMember(member);
		
		if(loginMember != null) {
			session.setAttribute("m", loginMember);
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/searchMemberId.do")
	public String searchMemberId(Member member, Model model) {
		
		Member m = service.selectOneMemberId(member);
		
		if(m != null) {
			model.addAttribute("m", m);
			return "member/searchMemberId";
		}else {
			return "redirect:/";
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
		return "member/joinFrm";
	}
	
	@RequestMapping(value="/join.do")
	public String join(Member member) {
		int result = service.insertMember(member);
		
		if(result > 0) {
			return "redirect:/";
		}else {
			return "redirect:/joinFrm.do";			
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
	
	@RequestMapping(value="/mypage.do")
	public String mypage() {
		return "member/mypage";
	}
	
	@RequestMapping(value="/updateMember.do")
	public String updateMember(Member member, @SessionAttribute(required = false) Member m) {
		int result = service.updateMember(member);
		
		if(result > 0) {
			// 세션 setAttribute로 교체보다는 멤버타입으로 가져와서 수정할 정보만 setter
			m.setMemberPw(member.getMemberPw());
			m.setMemberEmail(member.getMemberEmail());
			m.setMemberPhone(member.getMemberPhone());
			
			return "redirect:/mypage.do";
		}else {
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="/searchMember1.do")
	public String searchMember1(String type, String keyword, Model model) {
		// 아이디로 검색하면 : where member_id=?
		// 이름으로 검색하면 : where member_name like %검색어%
		// 조회결과가 하나일수도 여러개일수도 있으므로 ArrayList로 받아야함 !
		
		ArrayList<Member> list = service.search1(type,keyword);
		
		// System.out.println("type : "+type);
		// System.out.println("keyword : "+keyword);
		
		model.addAttribute("list", list);
		
		return "member/allMember";
	}
	
	@RequestMapping(value="/searchMember2.do")
	public String searchMember2(Member member, Model model) {
		// System.out.println(member);
		
		ArrayList<Member> list = service.search2(member);
		model.addAttribute("list", list);
		
		return "member/allMember";
	}
	
	@RequestMapping(value="/searchMember3.do")
	public String searhMember3(Model model) {
		ArrayList<String> idList = service.search3();
		
		model.addAttribute("idList", idList);
		
		return "member/idList";
	}
	
	@RequestMapping(value="/searchMember4.do")
	public String searchMember4(String[] memberId, Model model) {
		// 아이디 목록을 배열로 받아옴
		ArrayList<Member> list = service.search4(memberId);
		
		model.addAttribute("list", list);
		
		return "member/allMember";
	}
	
	@RequestMapping(value="/searchMember5.do")
	public String searchMember5(Model model) {
		ArrayList<Member> list = service.search5();
		
		model.addAttribute("list", list);
		
		return "member/allMember";
	}
	
}
