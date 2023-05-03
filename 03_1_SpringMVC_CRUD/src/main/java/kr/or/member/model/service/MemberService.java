package kr.or.member.model.service;

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
		System.out.println("MemberService 생성 완료");
	}

	public Member selectOneMember(String memberId) {
		Member member = dao.selectOneMember(memberId);
		
		return member;
	}
	
	
}
