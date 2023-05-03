package kr.or.dm.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.dm.model.vo.DirectMessage;

@Repository
public class DmDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public int insertDm(DirectMessage dm) {
		int result = sqlSession.insert("dm.insertDm", dm);
		
		return result;
	}

	public ArrayList<DirectMessage> selectDmList(DirectMessage dm) {
		List list = sqlSession.selectList("dm.selectDmList", dm);
		
		return (ArrayList<DirectMessage>)list;
	}

	public DirectMessage selectOneDm(int dmNo) {
		DirectMessage dm = sqlSession.selectOne("dm.selectOneDm", dmNo);
		
		return dm;
	}

	public void updateReadCheck(int dmNo) {
		sqlSession.update("dm.updateReadCheck", dmNo);
	}

	public int selectDmCount(String memberId) {
		
		int dmCount = sqlSession.selectOne("dm.dmCount", memberId);
		
		return dmCount;
	}


}
