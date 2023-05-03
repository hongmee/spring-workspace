package kr.or.aop;

import java.util.ArrayList;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import kr.or.member.model.vo.Member;

@Component
@Aspect
public class AdviceClass {
	
	@Pointcut(value="execution(* kr.or.member.model.service.MemberService.selectOneMember(..))")
	public void loginPointcut() {}
	@Pointcut(value="execution(* kr.or.member.model.service.MemberService.selectAllMember())")
	public void selectAllPointcut() {}

	// annotation으로 pointcut 적용
	// @Before(value="loginPointcut()") // 메소드 이름이 id 역할
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
	
	@AfterReturning(value="selectAllPointcut()", returning = "returnObj")
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
