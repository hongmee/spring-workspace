package kr.or.iei.controller;

public class ViewResolver {
	public String getView(String result) {
		// 결과처리 중 공통 되는 부분
		return "/view/"+result+".jsp";
	}
}
