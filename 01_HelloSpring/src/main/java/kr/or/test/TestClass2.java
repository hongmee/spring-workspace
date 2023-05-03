package kr.or.test;

import org.springframework.stereotype.Component;

// servlet-context.xml에서 패키지 경로 맞춰줘야함!
@Component
public class TestClass2 {
	public TestClass2() {
		super();
		System.out.println("TestClass2 생성 완료!");
	}
}
