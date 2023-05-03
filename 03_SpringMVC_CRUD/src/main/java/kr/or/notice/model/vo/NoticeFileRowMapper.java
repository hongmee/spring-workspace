package kr.or.notice.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class NoticeFileRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		NoticeFileVO file = new NoticeFileVO();
		
		file.setFilename(rs.getString("filename"));
		file.setFileNo(rs.getInt("file_no"));
		file.setFilepath(rs.getString("filepath"));
		file.setNoticeNo(rs.getInt("notice_no"));
		
		return file;
	}

}
