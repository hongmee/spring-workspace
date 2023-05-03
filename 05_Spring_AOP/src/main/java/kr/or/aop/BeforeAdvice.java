package kr.or.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

import kr.or.member.model.vo.Member;

public class BeforeAdvice {
	public void beforeTest(JoinPoint jp) {
		// advice가 동작하는 메소드에 대한 정보가 들어있는 객체
		Signature sig = jp.getSignature();
		System.out.println(sig.getName());
		System.out.println(sig.toLongString());
		System.out.println(sig.toShortString());
		// 실행 메소드의 매개변수를 가져올 수 있음
		// 실행하는 메소드의 매개변수의 타입과 개수가 어떠한 형태여도 모두 처리하기 위해 Object[]로 항상 리턴
		Object[] args = jp.getArgs();
		for(int i=0; i < args.length ; i++) {
			System.out.println("args : "+args[i]);
		}
		
		System.out.println("beforeTest 끝");
	}
	
	public void test2(JoinPoint jp) {
		// 로그인 할때 비밀번호 변경
		Signature sig = jp.getSignature();
		System.out.println("메소드 : "+sig.getName());
		
		Object[] args = jp.getArgs();
		Member member = (Member)args[0];
		System.out.println("입력 비밀번호 : " + member.getMemberPw());
		member.setMemberPw("asdf");
		System.out.println("변경완료");
	}
}
