package kr.or.member.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.google.gson.Gson;

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
		System.out.println("서비스 호출 전");
		Member loginMember = service.selectOneMember(member);
		System.out.println("서비스 호출 후");
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
		// mypage.jsp에서는 비밀번호 변경 안함 -> 새 jsp 만들어서 비밀번호만 따로 변경
		int result = service.updateMemberNoPw(member);
		
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
	
	@RequestMapping(value="/pwChangeFrm.do")
	public String pwChangeFrm() {
		return "member/pwChange";
	}
	
	// jsp 페이지 이동이 아닌 값 자체를 리턴 
	@ResponseBody
	@RequestMapping(value="/pwCheck.do")
	public String checkPw(Member member) {
		Member m = service.selectOneMember(member);
		if(m != null) {
			return "ok";
		}else {
			return "error";
		}
	}
	
	@RequestMapping(value="/pwChange.do")
	public String pwChange(Member m, String memberPwRe) {

		// AOP 조건 못 맞추겠을때 쓰는 방법(AOP추가).. 별로임..
		int result = service.updateMemberPw(m, memberPwRe);
		// AOP 비밀번호 암호화 조건에 맞아야 암호화됨
//		int result = service.pwChangeMember(m);
		
		if(result > 0) {
			return "member/mypage";
		}else {
			return "redirect:/";
		}
	}
	
	@ResponseBody // 문자열 값 자체를 리턴!
	@RequestMapping(value="/idCheck.do")
	public String idCheck(Member member) {
		// ajax에서 memberId를 보냈지만 Member타입으로 받아도 무방 ! (재사용 목적)
		Member m = service.selectOneMemberId(member);
		
		if(m == null) {
			// 사용 가능
			return "ok";
			// resolver가 파일 경로를 지정해줌.. 따라서 어노테이션 하나 더 지정!
		}else {
			// 중복 아이디
			return "dup";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/ajaxAllMember.do", produces = "application/json;charset=utf-8")
	// 되돌려주는 타입 json, 한글 지원
	public String ajaxAllMember() {
		ArrayList<Member> list = service.selectAllMember();
		
		// Gson으로 처리 -> pom.xml에 라이브러리 추가
		Gson gson = new Gson();
		String result = gson.toJson(list);
		System.out.println(result);
		
		return result;
	}

	
	
	// 에러 처리
	@RequestMapping(value="/test1.do")
	public String test1() {
		return "member/test1";
	}
}
