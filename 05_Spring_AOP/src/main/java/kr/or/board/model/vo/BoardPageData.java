package kr.or.board.model.vo;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardPageData {
	private ArrayList<Board> list;
	private String pageNavi;
}
