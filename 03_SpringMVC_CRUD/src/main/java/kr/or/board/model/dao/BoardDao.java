package kr.or.board.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.board.model.vo.Board;
import kr.or.board.model.vo.BoardListRowMapper;
import kr.or.board.model.vo.BoardRowMapper;
import kr.or.board.model.vo.FileRowMapper;
import kr.or.board.model.vo.FileVO;

@Repository
public class BoardDao {

	@Autowired
	private JdbcTemplate jdbc;

	public int insertBoard(Board b) {
		String query = "insert into board values(board_seq.nextval, ?, ?, ?, to_char(sysdate, 'yyyy-mm-dd'))";
		
		Object[] params = {b.getBoardTitle(), b.getBoardWriter(), b.getBoardContent()};
		
		int result = jdbc.update(query, params);
				
		return result;
	}

	public int selectBoardNo() {
		String query = "select max(board_no) from board";
		
		// 만약에 조회 결과가 1행 1열인 경우 -> int타입으로 리턴받음
		int boardNo = jdbc.queryForObject(query, int.class);
		
		return boardNo;
	}

	public int insertFile(FileVO file) {
		String query = "insert into file_tbl values(file_seq.nextval, ?, ?, ?)";
		
		Object[] params = {file.getBoardNo(), file.getFilename(), file.getFilepath()};
		
		int result = jdbc.update(query, params);
		
		return result;
	}

	public ArrayList<Board> selectBoardList() {
		String query = "select board_no, board_title, board_writer, board_date from board order by 1 desc";
		
		List list = jdbc.query(query, new BoardListRowMapper());
		
		return (ArrayList<Board>)list;
	}

	public Board selectOneBoard(int boardNo) {
		String query = "select * from board where board_no=?";

		Object[] params = {boardNo};
		
		List list = jdbc.query(query, params, new BoardRowMapper());
		
		if(list.isEmpty()) {
			return null;
		}else {
			return (Board)list.get(0);			
		}
	}

	public ArrayList<FileVO> selectFileList(int boardNo) {
		String query = "select * from file_tbl where board_no=? order by 1";
		
		Object[] params = {boardNo};
		
		List list = jdbc.query(query, params, new FileRowMapper());
		
		return (ArrayList<FileVO>)list;
	}

	public int deleteBoard(int boardNo) {
		String query = "delete from board where board_no=?";
		
		Object[] params = {boardNo};
		
		int result = jdbc.update(query, params);
		
		return result;
	}

	public int updateBoard(Board b) {
		String query = "update board set board_title=?, board_content=? where board_no=? ";
		
		Object[] params = {b.getBoardTitle(), b.getBoardContent(), b.getBoardNo()};
		
		int result = jdbc.update(query, params);

		return result;
	}

	public int deleteFile(int no) {
		String query = "delete from file_tbl where file_no=?";
		
		Object[] params = {no};
		
		int result = jdbc.update(query, params);
		
		return result;
	}

	public FileVO selectDownFile(int fileNo) {
		String query = "select * from file_tbl where file_no=?";
		
		Object[] params = {fileNo};
		
		List list = jdbc.query(query, params, new FileRowMapper());
		
		return (FileVO)list.get(0);
	}
	
}
