package kr.or.iei.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// lombok
@Data // getter,setter
@NoArgsConstructor // 매개변수 없는 생성자
@AllArgsConstructor // 모든 매개변수가 있는 생성자

public class Member {
	private String name;
	private int age;
	private String addr;
}
