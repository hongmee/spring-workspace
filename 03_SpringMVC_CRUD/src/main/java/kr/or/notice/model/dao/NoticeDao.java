package kr.or.notice.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.board.model.vo.FileRowMapper;
import kr.or.board.model.vo.FileVO;
import kr.or.notice.model.vo.Notice;
import kr.or.notice.model.vo.NoticeFileRowMapper;
import kr.or.notice.model.vo.NoticeFileVO;
import kr.or.notice.model.vo.NoticeRowMapper;

@Repository
public class NoticeDao {

	@Autowired
	private JdbcTemplate jdbc;
	
	public NoticeDao() {
		super();
	}

	public ArrayList<Notice> selectAllNotice() {
		String query = "select * from notice order by 1 desc";
		
		List list = jdbc.query(query, new NoticeRowMapper());
		
		return (ArrayList<Notice>)list;
	}

	public int insertNotice(Notice n) {
		String query = "insert into notice values(notice_seq.nextval, ?, ?, ?, TO_CHAR(SYSDATE, 'YYYY-MM-DD'))";
		
		Object[] params = {n.getNoticeTitle(), n.getNoticeWriter(), n.getNoticeContent()};
		
		int result = jdbc.update(query, params);
		
		return result;
	}

	public Notice selectNoticeView(int noticeNo) {
		String query = "select * from notice where notice_no=?";
		
		Object[] params = {noticeNo};
		
		List list = jdbc.query(query, params, new NoticeRowMapper());
		
		if(list.isEmpty()) {
			return null;
		}else {
			Notice n = (Notice)list.get(0);
			
			return n;
		}
	}

	public int updateNotice(Notice n) {
		String query = "update notice set notice_title=?, notice_content=? where notice_no=?";
		
		Object[] params = {n.getNoticeTitle(), n.getNoticeContent(), n.getNoticeNo()};
		
		int result = jdbc.update(query, params);
		
		return result;
	}

	public int deleteNotice(int noticeNo) {
		String query = "delete from notice where notice_no=?";
		
		Object[] params = {noticeNo};
		
		int result = jdbc.update(query, params);
		
		return result;
	}

	public int selectNoticeNo() {
		String query = "select max(notice_no) from notice";
		
		// 만약 조회 결과가 1행 1열인 경우 -> int타입으로 리턴받음
		int noticeNo = jdbc.queryForObject(query, int.class);
		
		return noticeNo;
	}

	public int insertFile(NoticeFileVO file) {
		String query ="insert into notice_file_tbl values(notice_file_seq.nextval, ?, ?, ?)";
		
		Object[] params = {file.getNoticeNo(), file.getFilename(), file.getFilepath()};
		
		int result = jdbc.update(query, params);
		
		return result;
	}

	public ArrayList<NoticeFileVO> selectFileList(int noticeNo) {
		String query = "select * from notice_file_tbl where notice_no=? order by 1";
		
		Object[] params = {noticeNo};
		
		List list = jdbc.query(query, params, new NoticeFileRowMapper());
		
		return (ArrayList<NoticeFileVO>)list;
	}

	public int deleteFile(int no) {
		String query = "delete from notice_file_tbl where file_no=?";
		
		Object[] params = {no};
		
		int result = jdbc.update(query, params);
				
		return result;
	}

	public NoticeFileVO selectDownFile(int fileNo) {
		String query = "select * from notice_file_tbl where file_no=?";
		
		Object[] params = {fileNo};
		
		List list = jdbc.query(query, params, new NoticeFileRowMapper());
		
		return (NoticeFileVO)list.get(0);
	}

	
}
