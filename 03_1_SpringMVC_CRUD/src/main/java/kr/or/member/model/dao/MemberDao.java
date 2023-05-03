package kr.or.member.model.dao;

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
		System.out.println("MemberDao 생성 완료");
	}

	public Member selectOneMember(String memberId) {
		// 1. PreparedStatement 방식으로 query 작성
		String query = "select * from member_tbl where member_id=?";
		
		// 2. 위치홀더가 1개라도 있는 경우 (만약 위치홀더가 1개라도 없으면 2번 과정 생략)
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
	
}
