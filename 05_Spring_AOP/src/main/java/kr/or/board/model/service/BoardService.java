package kr.or.board.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.board.model.dao.BoardDao;
import kr.or.board.model.vo.Board;
import kr.or.board.model.vo.BoardPageData;
import kr.or.board.model.vo.FileVO;

@Service
public class BoardService {

	@Autowired
	private BoardDao dao;

	public BoardPageData selectBoardList(int reqPage) {
		// 한 페이지당 보여줄 게시물 수 : 3
		int numPerPage = 3;
		// reqPage가 1이면 게시물 1번 ~ 2번, 2이면 3번 ~ 4번
		int end = reqPage * numPerPage; // 한 페이지당 마지막 게시물 번호
		int start = end - numPerPage + 1; // 한 페이지당 처음 게시물 번호
		
		// 계산된 start, end를 가지고 게시물 목록 조회
		// mybatis는 매개변수를 1개만 설정이 가능 -> 필요한 값이 여러개면 1개 묶어야함(VO, Map);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		// 게시글 조회
		ArrayList<Board> list = dao.selectBoardList(map);
		
		// pageNavi 제작 시작
		// 전체 페이지 수 계산 필요 -> 전체 게시물 수 조회
		int totalCount = dao.selectBoardCount();
		
		// 전체 게시물로 전체 페이지 수 계산
//		int totalPage = 0;		
//		if(totalCount % numPerPage == 0) {
//			totalPage = totalCount / numPerPage;			
//		}else {
//			totalPage = totalCount / numPerPage + 1;
//		}
		// 소수점 생기면(나머지가 있으면) 올림해서 페이지수 하나 더 생성
		int totalPage = (int)Math.ceil(totalCount / (double)numPerPage);
		
		// 페이지 네비 사이즈
		int pageNaviSize = 5;
		// 요청한 페이지를 페이지 네비에서 항상 가운데로 하기, 4페이지 요청시 ->  2 3 '4' 5 6
		int pageNo = 1; // 페이지네비 시작번호
		if(reqPage > 3) {
			pageNo = reqPage - 2;
		}
		
		// 페이지 네비 생성 시작
		String pageNavi = "";
		// 이전 버튼
		if(pageNo != 1) {
			pageNavi += "<a href='/boardList.do?reqPage="+(pageNo-1)+"'>[이전]</a>";
		}
		// 페이지 숫자 생성
		for(int i=0; i<pageNaviSize; i++) {
			if(pageNo == reqPage) {
				pageNavi += "<span>"+pageNo+"</span>";
			}else {
				pageNavi += "<a href='/boardList.do?reqPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
			if(pageNo > totalPage) {
				break;
			}
		}
		// 다음 버튼
		if(pageNo <= totalPage) {
			pageNavi += "<a href='/boardList.do?reqPage="+pageNo+"'>[다음]</a>";
		}
		BoardPageData bpd = new BoardPageData(list, pageNavi);
		
		return bpd;
	}

	public int insertBoard(Board b, ArrayList<FileVO> fileList) {
		// 1. Board에 insert
		// System.out.println(b);
		int result = dao.insertBoard(b);
		// System.out.println(b); // insert하면서 boardNo 조회 가능 !
		
		if(result > 0) {
			// 2. 방금 insert한 board_no 조회
			// int boardNo = dao.selectBoardNo();
			
			// 3. File_tbl에 insert
			for(FileVO file : fileList) {
				// setter로 게시글번호 값 변경
				file.setBoardNo(b.getBoardNo());
				// 파일 개수만큼 insert
				result += dao.insertFile(file);				
			}
		}
		
		return result;
	}

	public Board selectOneBoard(int boardNo) {
		// 1. board 테이블 조회
		Board b = dao.selectOneBoard(boardNo);
		// 2. file_tbl 테이블 조회
//		if(b != null) {
//			ArrayList<FileVO> fileList = dao.selectFileList(boardNo);
//			// 조회한 fileList를 객체타입에 setter
//			b.setFileList(fileList);
//		}
		return b;
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
}
