package kr.or.board.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileVO {
	private int fileNo;
	private int boardNo;
	private String filename;
	private String filepath;
}
