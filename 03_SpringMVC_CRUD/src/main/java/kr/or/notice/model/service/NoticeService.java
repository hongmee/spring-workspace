package kr.or.notice.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.board.model.vo.FileVO;
import kr.or.notice.model.dao.NoticeDao;
import kr.or.notice.model.vo.Notice;
import kr.or.notice.model.vo.NoticeFileVO;

@Service
public class NoticeService {

	@Autowired
	private NoticeDao dao;
	
	public NoticeService() {
		super();
	}

	public ArrayList<Notice> selectAllNotice() {
		ArrayList<Notice> list = dao.selectAllNotice();
		
		return list;
	}

	public int insertNotice(Notice n, ArrayList<NoticeFileVO> fileList) {
		
		// 1. 공지사항 작성
		int result = dao.insertNotice(n);
		
		if(result > 0) {
			// 파일 테이블에 insert 하기 위해 방금 insert된 notice_no 조회
			int noticeNo = dao.selectNoticeNo();
			
			for(NoticeFileVO file : fileList) {
				// 방금 insert한 noticeNo를 setter로 값 변경
				file.setNoticeNo(noticeNo);
				// result 누계
				result += dao.insertFile(file);
			}
		}

		return result;
	}

	public Notice selectNoticeView(int noticeNo) {
		
		// 공지사항 조회
		Notice n = dao.selectNoticeView(noticeNo);
		
		// 파일 조회
		if(n != null) {
			ArrayList<NoticeFileVO> fileList = dao.selectFileList(noticeNo);
			// 리턴 합치지않고 setter
			n.setFileList(fileList);
		}
		
		return n;
	}

	public int updateNotice(Notice n, ArrayList<NoticeFileVO> fileList, int[] fileNo) {
		
		// 1. notice 테이블 수정
		int result = dao.updateNotice(n);
		
		if(result > 0) {
			// 2. 기존 첨부파일 삭제 (삭제한 첨부파일이 있을때만)
			if(fileNo != null) {
				for(int no : fileNo) {
					result += dao.deleteFile(no);
				}
			}
			
			// 3. 새 첨부파일이 있으면 추가
			for(NoticeFileVO f : fileList) {
				f.setNoticeNo(n.getNoticeNo());
				result += dao.insertFile(f);
			}
		}
		return result;
	}

	public ArrayList<NoticeFileVO> deleteNotice(int noticeNo) {
		
		// 삭제할 파일 리스트 조회
		ArrayList<NoticeFileVO> fileList = dao.selectFileList(noticeNo);
		
		// cascade로 인해 게시글 삭제시 파일 테이블도 db에서 삭제됨
		int result = dao.deleteNotice(noticeNo);
		
		if(result > 0) {
			return fileList;
		}else {
			return null;
		}
	}

	public NoticeFileVO selectDownFile(int fileNo) {
		NoticeFileVO f = dao.selectDownFile(fileNo);
		
		return f;
	}
}
