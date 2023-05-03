package kr.or.iei.controller;

import java.util.HashMap;

public class HandlerMapping {
	private HashMap<String, Controller> mappings;

	public HandlerMapping() {
		super();
		mappings = new HashMap<String, Controller>();
		mappings.put("/login.do", new LoginController());
		mappings.put("/search.do", new SearchMember());
	}

	public Controller getController(String uri) {
		return mappings.get(uri);
	}
}
