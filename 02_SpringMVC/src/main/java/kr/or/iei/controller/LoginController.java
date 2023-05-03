package kr.or.iei.controller;

import javax.servlet.http.HttpServletRequest;

public class LoginController implements Controller {

	@Override
	public String request(HttpServletRequest request) {
		// 2. 값 추출
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		// 3. 비즈니스 로직
		// memberId : user01이고, memberPw : 1234 이면 로그인 성공, 아님 실패
		if(memberId.equals("user01") && memberPw.equals("1234")) {
			return "loginSuccess";
		}else {
			return "loginFail";
		}
	}
}
