package kr.or.member.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.member.model.dao.MemberDao;
import kr.or.member.model.vo.Member;

@Service
public class MemberService {
	
	@Autowired
	private MemberDao dao;

	public MemberService() {
		super();
		System.out.println("MemberService 생성 완료!");
	}

	public Member selectOneMember(String memberId) {
		Member member = dao.selectOneMember(memberId);
		
		return member;
	}

	public ArrayList<Member> selectAllMember() {
		ArrayList<Member> list = dao.selectAllMember();
		
		return list;
	}

	public int insertMember(Member m) {
		int result = dao.insertMember(m);
		
		return result;
	}

	public Member selectOneMember(Member member) {
//		Member loginMember = dao.selectOneMember(member);
//		return loginMember;
		return dao.selectOneMember(member);
	}

	public int updateMember(Member member) {
		int result = dao.updateMember(member);
		
		return result;
	}

	public int deleteMember(int memberNo) {
		int result = dao.deleteMember(memberNo);
		
		return result;
	}

}
