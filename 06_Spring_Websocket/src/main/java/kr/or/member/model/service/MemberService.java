package kr.or.member.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.aop.LogAdvice1;
import kr.or.aop.LogAdvice2;
import kr.or.member.model.dao.MemberDao;
import kr.or.member.model.vo.Member;
import kr.or.member.model.vo.SearchVO;

@Service
public class MemberService {

	@Autowired
	private MemberDao dao;

	public MemberService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Member selectOneMember(Member member) {
		System.out.println("dao 호출 전");
		Member m = dao.selectOneMember(member);
		System.out.println("dao 호출 후");
		return m;
//		return dao.selectOneMember(member);
	}

	public Member selectOneMemberId(Member member) {
		return dao.selectOneMemberId(member);
	}

	public ArrayList<Member> selectAllMember() {
		return dao.selectAllMember();
	}

	@Transactional
	public int insertMember(Member member) {
		// 두번째 insert할때 유니크 조건 위배되므로
		// insert,delete,update할때 트랜잭션 사용 하면 최종 실패시 첫번째 insert rollback됨
		// 원래는 첫번째 insert는 auto commit 되므로 최종 실패시에도 insert됨
		// 트랜잭션
		// dao.insertMember(member);
		return dao.insertMember(member);
	}

	@Transactional
	public int deleteMember(int memberNo) {
		return dao.deleteMember(memberNo);
	}

	@Transactional
	public int updateMemberNoPw(Member member) {
		return dao.updateMember(member);
	}

	public ArrayList<Member> search1(String type, String keyword) {
		// 쿼리문 수행을 위해서는 type, keyword 값 2개를 모두 Mybatis에 전송을 해줘야 함
		// Mybatis는 매개변수를 1개밖에 받을 수 없음
		// 해결방법 1) type과 keyword를 묶을 VO 생성
		// 해결방법 2) HashMap<String, Object>
		SearchVO search = new SearchVO(type, keyword);
		ArrayList<Member> list = dao.search1(search);
		
		return list;
	}

	public ArrayList<Member> search2(Member member) {
		ArrayList<Member> list = dao.search2(member);
		
		return list;
	}

	public ArrayList<String> search3() {
		ArrayList<String> list = dao.search3();
		
		return list;
	}

	public ArrayList<Member> search4(String[] memberId) {
		ArrayList<Member> list = dao.search4(memberId);
		
		return list;
	}

	public ArrayList<Member> search5() {
		return dao.search5();
	}

	@Transactional
	public int updateMemberPw(Member m, String memberPwRe) {
		return dao.pwChangeMember(m);
	}
	
//	public int pwChangeMember(Member m) {
//		return dao.pwChangeMember(m);
//	}

}
