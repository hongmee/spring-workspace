package kr.or.notice.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class NoticeRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rset, int rowNum) throws SQLException {
		Notice n = new Notice();
		n.setNoticeNo(rset.getInt("notice_no"));
		n.setNoticeTitle(rset.getString("notice_title"));
		n.setNoticeWriter(rset.getString("notice_writer"));
		n.setNoticeContent(rset.getString("notice_content"));
		n.setNoticeDate(rset.getString("notice_date"));
		
		return n;
	}

}
