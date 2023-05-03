package kr.or.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

public class AroundAdvice {

	public Object aroundTest(ProceedingJoinPoint pjp) throws Throwable{
		// ProceedingJoinPoint는 JoinPoint를 상속해서 만들어진 객체
		// -> JoinPoint가 가지고 있는 기능은 모두 사용할 수 있고, 추가 기능이 존재
		String methodName = pjp.getSignature().getName();
		Object[] args = pjp.getArgs();
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		// proceed -> 서비스 메소드를 수행하는 메소드
		// return은 서비스가 리턴하는 객체
		Object obj = pjp.proceed();
		stopWatch.stop();
		
		System.out.println(methodName+"() 메소드 수행 시간 : "+stopWatch.getTotalTimeMillis()+"(ms)");
		
		return obj;
	}
}
