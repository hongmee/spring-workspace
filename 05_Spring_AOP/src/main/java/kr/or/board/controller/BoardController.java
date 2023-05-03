package kr.or.board.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import common.FileManager;
import kr.or.board.model.service.BoardService;
import kr.or.board.model.vo.Board;
import kr.or.board.model.vo.BoardPageData;
import kr.or.board.model.vo.FileVO;


@Controller
public class BoardController {
	@Autowired
	private BoardService service;
	@Autowired
	private FileManager manager;
	
	@RequestMapping(value="/boardList.do")
	public String boardList(int reqPage, Model model) {
		BoardPageData bpd = service.selectBoardList(reqPage);
		
		model.addAttribute("list", bpd.getList());
		model.addAttribute("pageNavi", bpd.getPageNavi());
		
		return "board/boardList";
	}
	
	@RequestMapping(value="/boardWriteFrm.do")
	public String boardWriteFrm() {
		return "board/boardWriteFrm";
	}
	
	@RequestMapping(value="/boardWrite.do")
	public String boardWrite(Board b, MultipartFile[] boardFile, HttpServletRequest request) {
		
		ArrayList<FileVO> fileList = new ArrayList<FileVO>();
		
		if(!boardFile[0].isEmpty()) {
			// 파일이 있을 때
			String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/board/");
			
			for(MultipartFile file : boardFile) {
				String filename = file.getOriginalFilename();
				// 업로드
				String filepath = manager.upload(savePath, file);
				
				FileVO fileVO = new FileVO();
				fileVO.setFilename(filename);
				fileVO.setFilepath(filepath);
				fileList.add(fileVO);
			}
		}
		
		int result = service.insertBoard(b, fileList);
		
		if(result == (fileList.size()+1)) {
			// insert한 게시글 1개 + insert한 파일 개수
			return "redirect:/boardList.do?reqPage=1";
		}else {
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="/boardView.do")
	public String boardView(int boardNo, Model model) {
		Board b = service.selectOneBoard(boardNo);
		
		model.addAttribute("b", b);
		
		return "board/boardView";
	}
	
	@RequestMapping(value="/boardUpdateFrm.do")
	public String boardUpdateFrm(int boardNo, Model model) {
		Board b = service.selectOneBoard(boardNo);
		
		model.addAttribute("b", b);
		
		return "board/boardUpdateFrm";
	}
	
	@RequestMapping(value="/boardUpdate.do")
	public String boardUpdate(Board b, int[] fileNo, String[] filepath, MultipartFile[] boardFile, HttpServletRequest request) {
		// boardNo, boardTitle, boardContent
		// 사진 여러개 삭제를 위해 배열로 받음 -> fileNo, filepath
		// 첨부파일 추가를 위한 MultipartFile[] boardFile
		// 파일 경로 request
		ArrayList<FileVO> fileList = new ArrayList<FileVO>();
		String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/board/");
		
		if(!boardFile[0].isEmpty()) {
			// 추가한 첨부파일이 있을 때
			 for(MultipartFile file : boardFile) {
				 String filename = file.getOriginalFilename();
				 String upfilepath = manager.upload(savePath, file);
				 
				 FileVO fileVO = new FileVO();
				 fileVO.setFilename(filename);
				 fileVO.setFilepath(upfilepath);
				 fileList.add(fileVO);
			 }
		}
		int result = service.boardUpdate(b, fileList, fileNo);
		// 업데이트 성공 조건 result => 삭제한 파일 수 + 추가한 파일 수 + 1
		if(fileNo != null && (result == (fileList.size() + fileNo.length + 1))){
			
			for(String delFile : filepath) {
				boolean delResult = manager.deleteFile(savePath, delFile);
				if(delResult) {
					System.out.println("삭제완료");
				}else {
					System.out.println("삭제실패");
				}
			}
			
			return "redirect:/boardView.do?boardNo="+b.getBoardNo();
		}else if(fileNo == null && (result == fileList.size() + 1)) {
			return "redirect:/boardView.do?boardNo="+b.getBoardNo();
		}else {
			return "redirect:/boardList.do?reqPage=1";
		}
	}
	
	@RequestMapping(value="/boardDelete.do")
	public String boardDelete(int boardNo, HttpServletRequest request) {
		
		// DB 삭제하고, 서버에 업로드 되어있는 파일들을 지우기 위해서 파일 목록을 가져옴
		ArrayList<FileVO> list = service.deleteBoard(boardNo);
		
		if(list == null) {
			// 실패
			return "redirect:/boardView.do?boardNo="+boardNo;
		}else {
			// 파일이 저장된 경로 (request 필요하므로 매개변수에 작성)
			String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/board/");
			
			for(FileVO file : list) {
				
				boolean deleteResult = manager.deleteFile(savePath,file.getFilepath());
				
				if(deleteResult) {
					System.out.println("파일삭제성공");
				}else {
					System.out.println("파일삭제실패");
				}
			}
			return "redirect:/boardList.do?reqPage=1";
		}		
	}
}
