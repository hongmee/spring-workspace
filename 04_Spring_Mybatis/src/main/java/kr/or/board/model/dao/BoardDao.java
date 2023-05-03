package kr.or.board.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.or.board.model.vo.Board;
import kr.or.board.model.vo.FileVO;

@Repository
public class BoardDao {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public ArrayList<Board> selectBoardList(HashMap<String, Object> map) {
		List list = sqlSession.selectList("board.selectBoardList", map);
		
		return (ArrayList<Board>)list;
	}

	public int selectBoardCount() {
		int totalCount = sqlSession.selectOne("board.totalCount");
		
		return totalCount;
	}

	public int insertBoard(Board b) {
		int result = sqlSession.insert("board.insertBoard", b);
				
		return result;
	}

	public int selectBoardNo() {
		int boardNo = sqlSession.selectOne("board.selectBoardNo");
		
		return boardNo;
	}

	public int insertFile(FileVO file) {
		int result = sqlSession.insert("board.insertFile", file);
		
		return result;
	}

	public Board selectOneBoard(int boardNo) {
		Board b = sqlSession.selectOne("board.selectOneBoard", boardNo);
		
		return b;
	}

	public ArrayList<FileVO> selectFileList(int boardNo) {
		List list = sqlSession.selectList("board.selectFileList", boardNo);
		
		return (ArrayList<FileVO>)list;
	}

	public int updateBoard(Board b) {
//		String query = "update board set board_title=?, board_content=? where board_no=? ";
//		
//		Object[] params = {b.getBoardTitle(), b.getBoardContent(), b.getBoardNo()};
//		
//		int result = jdbc.update(query, params);
//
//		return result;
		
		int result = sqlSession.update("board.updateBoard", b);
		
		return result;
	}

	public int deleteFile(int no) {
//		String query = "delete from file_tbl where file_no=?";
//		
//		Object[] params = {no};
//		
//		int result = jdbc.update(query, params);
//		
//		return result;
		
		int result = sqlSession.delete("board.deleteFile", no);
		
		return result;
	}

	public int deleteBoard(int boardNo) {
		
		int result = sqlSession.delete("board.deleteBoard", boardNo);
		
		return result;
	}
}
