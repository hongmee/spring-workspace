package kr.or.student.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentVO {
	private int studentNumber;
	private String studentName;
	private int koreanScore;
	private int englishScore;
	private int mathScore;
}
