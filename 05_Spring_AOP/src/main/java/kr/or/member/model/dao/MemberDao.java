package kr.or.member.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.member.model.vo.Member;
import kr.or.member.model.vo.SearchVO;

@Repository
public class MemberDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	

	public Member selectOneMember(Member member) {
		System.out.println("DB 작업 전");

		// memberSQL.xml에서 mapper namespace="member"의  select id="selectOneMember" 코드 실행
		Member m = sqlSession.selectOne("member.selectOneMember", member);
		// mybatis-config에서 <typeAlias type="kr.or.member.model.vo.Member" alias="m"/> "패키지이름.vo클래스"를 별칭 설정하고
		// memberSQL.xml에서 parameterType="m" resultType="m"로 간단히 작성 가능
		// (전달받은 데이터타입)(결과 데이터타입)
		
		System.out.println("DB 작업 후");
		return m;

	}


	public Member selectOneMemberId(Member member) {
		
		Member m = sqlSession.selectOne("member.selectOneMember", member);
		
		return m;
	}


	public ArrayList<Member> selectAllMember() {

		List list = sqlSession.selectList("member.selectAllMember");
		
		return (ArrayList<Member>)list;
	}


	public int insertMember(Member member) {
		
		// 파리미터 매개변수는 하나만 가능하므로 리스트나 해쉬맵을 쓰든 하나로 묶어서 보내야만 함!
		int result = sqlSession.insert("member.insertMember", member);
		
		return result;
	}


	public int deleteMember(int memberNo) {
		
		int result = sqlSession.delete("member.deleteMember", memberNo);
		
		return result;
	}


	public int updateMember(Member member) {
		
		int result = sqlSession.update("member.updateMember", member);
		
		return result;
	}


	public ArrayList<Member> search1(SearchVO search) {
		
		List list = sqlSession.selectList("member.search1", search);
		
		return (ArrayList<Member>)list;
	}


	public ArrayList<Member> search2(Member member) {
		
		List list = sqlSession.selectList("member.search2", member);
		
		return (ArrayList<Member>)list;
	}


	public ArrayList<String> search3() {
		
		List list = sqlSession.selectList("member.search3");
		
		return (ArrayList<String>)list;
	}


	public ArrayList<Member> search4(String[] memberId) {
		
		List list = sqlSession.selectList("member.search4", memberId);
		
//		// Mybatis에서 매개변수로 배열을 전달하는 경우
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		map.put("array", memberId);	
//		List list = sqlSession.selectList("member.search4", map);
		// array = {'user02', 'user03', 'user05'}
		
		return (ArrayList<Member>)list;
	}


	public ArrayList<Member> search5() {
		
		List list = sqlSession.selectList("member.search5");
		
		return (ArrayList<Member>)list;
	}


	public int pwChangeMember(Member m) {
		
		int result = sqlSession.update("member.pwChange", m);
		
		return result;
	}
}
