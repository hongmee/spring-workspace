package kr.or.notice.model.vo;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notice {
	private int noticeNo;
	private String noticeTitle;
	private String noticeWriter;
	private String noticeContent;
	private String noticeDate;
	private ArrayList<NoticeFileVO> fileList;
}
