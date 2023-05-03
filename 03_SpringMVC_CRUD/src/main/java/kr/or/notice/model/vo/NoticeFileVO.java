package kr.or.notice.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeFileVO {
	private int fileNo;
	private int noticeNo;
	private String filename;
	private String filepath;
}
