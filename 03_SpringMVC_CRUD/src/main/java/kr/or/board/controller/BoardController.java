package kr.or.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import common.FileManager;
import kr.or.board.model.service.BoardService;
import kr.or.board.model.vo.Board;
import kr.or.board.model.vo.FileVO;

@Controller
public class BoardController {
	
	@Autowired // 만들어진 service를 주입
	private BoardService service;
	@Autowired
	private FileManager fileManager;
	
	
	@RequestMapping(value="/boardList.do")
	public String boardList(Model model) {
		
		ArrayList<Board> list = service.selectBoardList();
		model.addAttribute("list", list);
		
		return "board/boardList";
	}
	
	@RequestMapping(value="/boardWriteFrm.do")
	public String boardWriteFrm() {
		return "board/boardWriteFrm";
	}
	
	@RequestMapping(value="/boardWrite.do")
	public String boardWrite(Board b, MultipartFile[] boardFile, HttpServletRequest request) {
		
		// 파일 목록을 저장할 list 생성
		ArrayList<FileVO> fileList = new ArrayList<FileVO>();
		
		// 제목, 내용, 작성자는 매개변수 b에 모두 들어옴
		// System.out.println(b);
		
		// 첨부파일은 input type=file의 name으로 매개변수를 생성
		// 첨부파일 개수만큼 배열 길이 생성
		// System.out.println(boardFile.length);
		// 단, 첨부파일 없어도 배열은 길이 1 -> 첫번째 배열이 비었으면 첨부파일 업로드 x
		if(boardFile[0].isEmpty()) {
			// 첨부파일이 없는 경우 진행할 로직이 없음
			// if(!boardFile[0].isEmpty())로 작성하는게 좋음
		}else {
			// 첨부파일이 있는 경우 파일 업로드 작업 수행
			// 1. 파일 업로드 경로 설정
			// getRealPath() -> webapp
			String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/board/");
			
			// 2. 반복문을 이용한 파일 업로드 처리
			for(MultipartFile file : boardFile) {
				// 파일명이 기존 업로드한 파일명과 중복되면 기존 파일을 삭제하고 새 파일로 덮어쓰기 됨
				// -> 파일명이 중복되지않게 처리를 직접 해야함 ( cos.jar의 DefaultRenamePolicy()없음 )
				
				// 2-1. 파일명 중복체크
				// 사용자가 업로드한 파일 이름 추출
				String filename = file.getOriginalFilename();
				
				// 파일 업로드 빌드 설정		
				String filepath = fileManager.upload(savePath, file);
				
				// 파일 업로드 끝 (1개)
				// DB에 저장하기 위해 FileVO 형태 객체 생성해서 fileList 추가
				FileVO fileVO = new FileVO();
				fileVO.setFilename(filename);
				fileVO.setFilepath(filepath);
				fileList.add(fileVO);
				
			}// for문 끝나는 지점
		}
		
		// Board, File 모두 insert
		int result = service.insertBoard(b, fileList);
		
		if(result == fileList.size()+1) {
			// 게시글 insert 1개 + 파일 개수 insert n개 = 파일개수 + 1 만큼 result
			
			// 단지 jsp로 이동이 아니라 .do 거쳐서 이동해야함
			// return "board/boardList";
			return "redirect:/boardList.do";
		}else {
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="/boardView.do")
	public String boardView(int boardNo, Model model) {
		
		Board b = service.selectOneBoard(boardNo);
		
		if(b != null) {
			model.addAttribute("b", b);
			return "board/boardView";
		}else {
			return "redirect:/boardList.do";
		}
		
	}
	
	@RequestMapping(value="/boardFileDown.do")
	public void boardFileDown(int fileNo, HttpServletRequest request, HttpServletResponse response) {
		// fileNo : DB에서 filename, filepath를 조회하기 위한 용도
		// request : 파일 위치 찾을 때 사용
		// response : 파일 다운로드 로직 구현
		// 페이지 이동 없음 -> 리턴없음 void
		
		// filename, filepath 조회
		FileVO f = service.selectDownFile(fileNo);
		
		// 파일 경로 가져오기
		String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/board/");
		String downFile = savePath + f.getFilepath();
		
		fileManager.filedown(downFile, response, f.getFilename());
		
//		try {
//			FileInputStream fis = new FileInputStream(downFile);
//			BufferedInputStream bis = new BufferedInputStream(fis);
//			ServletOutputStream sos = response.getOutputStream();
//			BufferedOutputStream bos = new BufferedOutputStream(sos);
//			
//			String resFilename = new String(f.getFilename().getBytes("UTF-8"), "ISO-8859-1");
//			response.setContentType("application/octet-stream");
//			response.setHeader("Content-Disposition", "attachment;filename="+resFilename);
//			
//			while(true) {
//				int read = bis.read();
//				if(read != -1) {
//					bos.write(read);
//				}else {
//					break;
//				}
//			}
//			bos.close();
//			bis.close();
//			
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
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
				
				boolean deleteResult = fileManager.deleteFile(savePath,file.getFilepath());
				
				if(deleteResult) {
					System.out.println("파일삭제성공");
				}else {
					System.out.println("파일삭제실패");
				}
			}
			return "redirect:/boardList.do";
		}		
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
				 String upfilepath = fileManager.upload(savePath, file);
				 
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
				boolean delResult = fileManager.deleteFile(savePath, delFile);
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
			return "redirect:/boardList.do";
		}
		
	}
	
}
