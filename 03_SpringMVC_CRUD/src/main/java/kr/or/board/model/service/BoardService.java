package kr.or.board.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.board.model.dao.BoardDao;
import kr.or.board.model.vo.Board;
import kr.or.board.model.vo.FileVO;

@Service
public class BoardService {

	@Autowired // dao 객체 주소를 주입
	private BoardDao dao;

	public int insertBoard(Board b, ArrayList<FileVO> fileList) {
		// board insert, file_tbl insert
		// file테이블에서 board를 참조하고 있으므로 board 먼저 insert 해야함 !
		int result = dao.insertBoard(b);
		
		if(result > 0) {
			// file_tbl에 insert 하기 위해서 boardNo 조회 해야함
			// -> 가장 최근 insert된 board_no 조회
			int boardNo = dao.selectBoardNo();
			
			for(FileVO file : fileList) {
				// 방금 insert한 boardNo를 setter로 값 변경
				file.setBoardNo(boardNo);
				// result 누계
				result += dao.insertFile(file);
			}
		}
		
		return result;
	}

	public ArrayList<Board> selectBoardList() {
		ArrayList<Board> list = dao.selectBoardList();
		
		return list;
	}

	public Board selectOneBoard(int boardNo) {
		
		// 게시글 조회
		Board b = dao.selectOneBoard(boardNo);
		
		// 파일 조회
		if(b != null) {
			ArrayList<FileVO> fileList = dao.selectFileList(boardNo);
			// 리턴 합치지않고 setter
			b.setFileList(fileList);
		}
		
		return b;
	}

	public ArrayList<FileVO> deleteBoard(int boardNo) {
		
		// 삭제할 파일 리스트 조회
		ArrayList<FileVO> fileList = dao.selectFileList(boardNo);
		
		// cascade로 인해 게시글 삭제시 해당 파일들도 DB에서 삭제됨
		int result = dao.deleteBoard(boardNo);
		
		if(result > 0) {
			return fileList;
		}else {
			return null;
		}
	}

	public int boardUpdate(Board b, ArrayList<FileVO> fileList, int[] fileNo) {
		
		// 1. board 테이블 수정
		int result = dao.updateBoard(b);
		
		if(result > 0) {
			
			// 2. 기존 첨부파일 삭제 (삭제한 첨부파일이 있을때만)
			if(fileNo != null) {
				for(int no : fileNo) {
					result += dao.deleteFile(no);
				}
			}
			
			// 3. 새 첨부파일이 있으면 추가
			for(FileVO f : fileList) {
				f.setBoardNo(b.getBoardNo());
				result += dao.insertFile(f);
			}
			
		}
		
		return result;
	}

	public FileVO selectDownFile(int fileNo) {
		FileVO f = dao.selectDownFile(fileNo);
		
		return f;
	}
}
