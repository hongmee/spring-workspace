package kr.or.member.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.member.model.vo.Member;
import kr.or.member.model.vo.MemberRowMapper;

@Repository
public class MemberDao {

	@Autowired
	private JdbcTemplate jdbc;
	
	public MemberDao() {
		super();
		System.out.println("MemberDao 생성 완료!");
	}

	public Member selectOneMember(String memberId) {
		// 1. PreparedStatement 방식으로 query 작성
		String query = "select * from member_tbl where member_id=?";
		
		// 2. 위치홀더가 1개라도 있는 경우 (만약 위치홀더가 1개도 없으면 2번 과정 생략)
		Object[] params = {memberId};
		
		// 3. 쿼리문 실행 (select인 경우 query() 메소드 사용)
		// query 매개변수 3개 (쿼리문, 위치홀더에 들어갈 값 배열, 결과를 처리할 객체)
		// 조회 결과는 개수 상관없이 무조건 List로 변환
		List list = jdbc.query(query, params, new MemberRowMapper());
		
		if(list.isEmpty()) {
			return null;
		}else {
			Member m = (Member)list.get(0);
			
			return m;
		}
		
	}

	public ArrayList<Member> selectAllMember() {
		String query = "select * from member_tbl";
		List list = jdbc.query(query, new MemberRowMapper());
		
		return (ArrayList<Member>)list;
	}

	public int insertMember(Member m) {
		String query = "insert into member_tbl values(member_seq.nextval, ?, ?, ?, ?, ?)";
		
		// 위치 홀더
		Object[] params = {m.getMemberId(), m.getMemberPw(), m.getMemberName(), m.getMemberPhone(), m.getMemberEmail()};
		
		// insert/update/delete -> update()
		int result = jdbc.update(query, params);
		// select가 아니므로 RowMapper 필요없음 !
		
		return result;
	}

	public Member selectOneMember(Member member) {
		String query = "select * from member_tbl where member_id=? and member_pw=?";
		
		Object[] params = {member.getMemberId(), member.getMemberPw()};
		
		List list = jdbc.query(query, params, new MemberRowMapper());
		
		if(list.isEmpty()) {
			return null;
		}else {
			Member loginMember = (Member)list.get(0);
			
			return loginMember;
		}
	}

	public int updateMember(Member member) {
		String query = "update member_tbl set member_pw=?, member_phone=?, member_email=? where member_id=?";
		
		Object[] params = {member.getMemberPw(), member.getMemberPhone(), member.getMemberEmail(), member.getMemberId()};
		
		int result = jdbc.update(query, params);
		
		return result;
	}

	public int deleteMember(int memberNo) {
		String query = "delete from member_tbl where member_no=?";
		
		Object[] params = {memberNo};
		
		int result = jdbc.update(query, params);
		
		return result;
	}

}
