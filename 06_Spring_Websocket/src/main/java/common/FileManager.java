package common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {
	
	public String upload(String savePath, MultipartFile file) {
		// 게시판마다 사진 업로드할 때 경로 부분만 달라지므로 메소드 따로 생성해서 업로드 빌드 설정
		
		String filename = file.getOriginalFilename();
		
		// filename => test.text 중복시 test1.text 이므로 파일명과 확장자 분리 해야함
		// lastIndexOf(맨 뒤부터 인덱스를 센다)
		String onlyFilename = filename.substring(0, filename.lastIndexOf(".")); // test ( 0번째부터 .까지 )
		String extention = filename.substring(filename.lastIndexOf(".")); // .txt	( .부터 끝까지 )
		
		// 실제 업로드할 파일명
		String filepath = null;
		// 파일명 중복 시 뒤에 붙일 숫자
		int count = 0;
		
		while(true) {
			if(count == 0) {
				// 첫번째 검증인 경우 숫자 붙이지 않음
				filepath = onlyFilename + extention; // test.txt
			}else {
				filepath = onlyFilename + "_" + count + extention; // test_1.text
			}
			
			File checkFile = new File(savePath + filepath);
			if(!checkFile.exists()) {
				// 기존 파일이 존재하지 않을때
				break;
			}
			count++;
		}
		
		// 파일명 중복체크 끝 -> 업로드 파일명 확정 -> 파일 업로드 진행
		// 2-2. 중복처리가 끝난 파일 업로드
		try {
			FileOutputStream fos = new FileOutputStream(savePath + filepath);
			// 성능 향상을 위한 보조스트림 생성
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			// 파일 업로드
			byte[] bytes = file.getBytes();
			bos.write(bytes);
			bos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return filepath;
	}

	public boolean deleteFile(String savePath, String filepath) {
		// 파일이 저장된 경로에서 파일 삭제
		File delFile = new File(savePath + filepath);
		
		return delFile.delete();
	}
	
	public void filedown(String downFile, HttpServletResponse response, String fileName) {
		
		try {
			FileInputStream fis = new FileInputStream(downFile);
			BufferedInputStream bis = new BufferedInputStream(fis);
			ServletOutputStream sos = response.getOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(sos);
			
			// f.getFilename()
			String resFilename = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="+resFilename);
			
			while(true) {
				int read = bis.read();
				if(read != -1) {
					bos.write(read);
				}else {
					break;
				}
			}
			bos.close();
			bis.close();
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
