package kr.or.manager.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerVO {
	private int managerNumber;
	private String managerName;
	private String managerHireDate;
}
