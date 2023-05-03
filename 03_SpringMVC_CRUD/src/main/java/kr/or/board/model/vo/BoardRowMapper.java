package kr.or.board.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BoardRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rset, int rowNum) throws SQLException {
		Board b = new Board();
		b.setBoardNo(rset.getInt("board_no"));
		b.setBoardTitle(rset.getString("board_title"));
		b.setBoardWriter(rset.getString("board_writer"));
		b.setBoardContent(rset.getString("board_content"));
		b.setBoardDate(rset.getString("board_date"));
		
		return b;
	}
	

}
