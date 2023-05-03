package kr.or.member.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.member.model.vo.Member;

@Repository
public class MemberDao {
	@Autowired
	private SqlSessionTemplate sqlSession;

	public ArrayList<Member> selectAllMember() {
		
		List list = sqlSession.selectList("member.selectAllMember");
		
		return (ArrayList<Member>)list;
	}
	
	
	public int insertMember(Member m) {
		
		int result = sqlSession.insert("member.insertMember",m);

		return result;

		}
}
