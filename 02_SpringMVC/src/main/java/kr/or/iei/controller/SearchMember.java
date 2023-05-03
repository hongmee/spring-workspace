package kr.or.iei.controller;

import javax.servlet.http.HttpServletRequest;

public class SearchMember implements Controller {

	@Override
	public String request(HttpServletRequest request) {
		// 2. 값 추출
		String memberId = request.getParameter("memberId");
		// 3. 비즈니스 로직
		// memberId가 user01이면 조회성공 아니면 실패
		if(memberId.equals("user01")) {
			return "searchSuccess";
		}else {
			return "searchFail";
		}
	}

}
