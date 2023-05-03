package kr.or.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.or.member.model.vo.Member;

@Component
@Aspect
public class PasswordEncAdvice {
	@Autowired
	private SHA256Enc passEnc;
	
	// 로그인, 회원가입, 정보수정(비밀번호) 메소드
	@Pointcut(value="execution(* kr.or.member.model.service.MemberService.*Member(kr.or.member.model.vo.Member))")
	public void encPointcut() {};
	
	// 비밀번호 변경시 AOP 못맞추겠을때 쓰는 AOP 추가하는 방법.. 별로임..
	@Pointcut(value="execution(* kr.or.member.model.service.MemberService.updateMemberPw(..))")
	public void pwChange() {};
	
	
	
	@Before(value="encPointcut()")
	public void passwordEnc(JoinPoint jp) throws Exception{
		String methodName = jp.getSignature().getName();
		System.out.println("비밀번호 암호화 동작 메소드 : "+methodName);
		
		Object[] args = jp.getArgs();
		Member member = (Member)args[0];
		String memberPw = member.getMemberPw();
		System.out.println("사용자 입력 비밀번호 : "+memberPw);
		String encPw = passEnc.encData(memberPw);
		System.out.println("암호화 비밀번호 : "+encPw);
		
		member.setMemberPw(encPw);
	}
	
	
	@Before(value="pwChange()")
	public void passPw(JoinPoint jp) throws Exception{
		// AOP 못맞추겠을 때 쓰는 방법..
		
		String methodName = jp.getSignature().getName();
		System.out.println("비밀번호 변경 메소드 : " +methodName);
		
		Object[] args = jp.getArgs();
		Member member = (Member)args[0];
		String memberPwRe = (String)args[1];
		
		System.out.println("pw : "+member.getMemberPw());
		System.out.println("pwRe : "+memberPwRe);
		
		String encPw = passEnc.encData(member.getMemberPw());
		member.setMemberPw(encPw);
	}
	
	
}
