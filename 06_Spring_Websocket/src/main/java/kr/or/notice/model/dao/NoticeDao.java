package kr.or.notice.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.notice.model.vo.Notice;

@Repository
public class NoticeDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public ArrayList<Notice> selectNoticeList() {
		
		List list = sqlSession.selectList("notice.selectAllNotice"); 
		
		return (ArrayList<Notice>)list;
	}

	public int insertNotice(Notice notice) {
		
		int result = sqlSession.insert("notice.insertNotice", notice);
		
		return result;
	}

	public Notice selectOneNoitce(int noticeNo) {
		
		Notice n = sqlSession.selectOne("notice.selectOneNotice", noticeNo);
		
		return n;
	}

	public int updateNotice(Notice notice) {
		
		int result = sqlSession.update("notice.updateNotice", notice);
		
		return result;
		
	}

	public int deleteNotice(int noticeNo) {
		
		int result = sqlSession.delete("notice.deleteNotice", noticeNo);
		
		return result;
	}
	
	
}
