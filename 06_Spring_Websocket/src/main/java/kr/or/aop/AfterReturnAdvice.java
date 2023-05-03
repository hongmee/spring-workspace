package kr.or.aop;

import java.util.ArrayList;

import org.aspectj.lang.JoinPoint;

import kr.or.member.model.vo.Member;

public class AfterReturnAdvice {
	
	public void arTest(JoinPoint jp, Object returnObj) {
		// returnObj : 수행되고 리턴되는 데이터
		String methodName = jp.getSignature().getName();
		
		System.out.println(methodName);
		
		ArrayList<Member> list = (ArrayList<Member>)returnObj;
		
		for(Member m : list) {
			m.setMemberPw("비밀번호는 안알려줄래");	
		}
	}
}
