package kr.or.board.model.vo;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	private int boardNo;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;
	private String boardDate;
	private ArrayList<FileVO> fileList;
}
