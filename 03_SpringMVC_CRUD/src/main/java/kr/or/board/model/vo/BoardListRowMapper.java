package kr.or.board.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BoardListRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rset, int rowNum) throws SQLException {
		Board b = new Board();
		b.setBoardNo(rset.getInt("board_no"));
		b.setBoardTitle(rset.getString("board_title"));
		b.setBoardWriter(rset.getString("board_writer"));
		b.setBoardDate(rset.getString("board_date"));
		
		// 없는 걸 꺼내면 에러남
		
		return b;
	}

}
