package kr.or.notice.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import common.FileManager;
import kr.or.board.model.vo.FileVO;
import kr.or.notice.model.service.NoticeService;
import kr.or.notice.model.vo.Notice;
import kr.or.notice.model.vo.NoticeFileVO;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService service; 
	@Autowired
	private FileManager fileManager;
	
	public NoticeController() {
		super();
	}

	@RequestMapping(value="/noticeList.do")
	public String noticeList(Model model) {
		ArrayList<Notice> noticeList = service.selectAllNotice();
		model.addAttribute("noticeList", noticeList);
		
		return "notice/noticeList";
	}
	
	@RequestMapping(value="/noticeFrm.do")
	public String noticeFrm() {
		return "notice/noticeFrm";
	}
	
	@RequestMapping(value="insertNotice.do")
	public String insertNotice(Notice n, MultipartFile[] noticeFile, HttpServletRequest request) {
		
		ArrayList<NoticeFileVO> fileList = new ArrayList<NoticeFileVO>();
		
		if(!noticeFile[0].isEmpty()) {
			// 1. 파일 업로드 경로 설정
			String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/notice/");
			
			// 2. 반복문을 이용한 파일 업로드 처리
			for(MultipartFile file : noticeFile) {
				
				// 2-1. 파일명 중복 체크
				// 사용자가 업로드한 파일 이름 추출
				String filename = file.getOriginalFilename();
				
				// 파일 업로드 빌드 설정
				String filepath = fileManager.upload(savePath, file);
				
				// FileVo 객체 생성해서 fileList 추가
				NoticeFileVO noticefileVO = new NoticeFileVO();
				noticefileVO.setFilename(filename);
				noticefileVO.setFilepath(filepath);
				fileList.add(noticefileVO);
			}
		}

		// notice, file 모두 insert
		int result = service.insertNotice(n, fileList);
		
		if(result == fileList.size() + 1) {
			return "redirect:/noticeList.do";
		}else {
			return "redirect:/";
		}
		
	}
	
	@RequestMapping(value="/noticeView.do")
	public String noticeView(int noticeNo, Model model) {
		
		Notice n = service.selectNoticeView(noticeNo);
		
		if(n != null) {
			model.addAttribute("n", n);
			return "notice/noticeView";
		}else {
			return "redirect:/noticeList.do";
		}
	}
	
	@RequestMapping(value="/updateNoticeFrm.do")
	public String updateNoticeFrm(int noticeNo, Model model) {
		
		Notice n = service.selectNoticeView(noticeNo);
		
		model.addAttribute("n", n);
		
		return "notice/updateNoticeFrm";
	}
	
	@RequestMapping(value="/updateNotice.do")
	public String updateNotice(Notice n, int[] fileNo, String[] filepath, MultipartFile[] noticeFile, HttpServletRequest request) {
		
		ArrayList<NoticeFileVO> fileList = new ArrayList<NoticeFileVO>();
		String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/notice/");
		
		if(!noticeFile[0].isEmpty()) {
			for(MultipartFile file : noticeFile) {
				String filename = file.getOriginalFilename();
				String upfilepath = fileManager.upload(savePath, file);
				
				NoticeFileVO noticeFileVO = new NoticeFileVO();
				noticeFileVO.setFilename(filename);
				noticeFileVO.setFilepath(upfilepath);
				fileList.add(noticeFileVO);
			}
		}
		int result = service.updateNotice(n, fileList, fileNo);
		// 업데이트 성공 조건 result => 삭제한 파일 수 + 추가한 파일 수 + 1
		if(fileNo != null && (result == (fileList.size() + fileNo.length + 1))) {
			for(String delFile : filepath) {
				boolean delResult = fileManager.deleteFile(savePath, delFile);
				if(delResult) {
					System.out.println("삭제완료");
				}else {
					System.out.println("삭제실패");
				}
			}
			
			return "redirect:/noticeView.do?noticeNo="+n.getNoticeNo();
		}else if(fileNo == null && (result == fileList.size() + 1)) {
			return "redirect:/noticeView.do?noticeNo="+n.getNoticeNo();
		}else {
			return "redirect:/noticeList.do";
		}
		
	}
	
	@RequestMapping(value="/deleteNotice.do")
	public String deleteNotice(int noticeNo, HttpServletRequest request) {
		
		// DB 삭제하고, 서버에 업로드된 파일들 지우기위해서 파일 목록 가져옴
		ArrayList<NoticeFileVO> list = service.deleteNotice(noticeNo);
		
		if(list == null) {
			// 실패
			return "redirect:/noticeView.do?noticeNo="+noticeNo;
		}else {
			// 파일이 저장된 경로 (request 필요하므로 매개변수에 작성)
			String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/notice");
			
			for(NoticeFileVO file : list) {
				boolean deleteResult = fileManager.deleteFile(savePath, file.getFilepath());
				
				if(deleteResult) {
					System.out.println("파일 삭제 성공");
				}else {
					System.out.println("파일 삭제 실패");
				}
			}
			return "redirect:/noticeList.do";
		}
	}
	
	@RequestMapping(value="/noticeFileDown.do")
	public void noticeFileDown(int fileNo, HttpServletRequest request, HttpServletResponse response) {
		// fileNo : DB에서 filename, filepath를 조회하기 위한 용도
		// request : 파일 위치 찾을 때 사용
		// response : 파일 다운로드 로직 구현
		// 페이지 이동 없음 -> 리턴없음 void
		
		// filename, filepath 조회
		NoticeFileVO f = service.selectDownFile(fileNo);
		
		// 파일 경로 가져오기
		String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/notice/");
		String downFile = savePath + f.getFilepath();
		
		fileManager.filedown(downFile, response, f.getFilename());
	}
}
