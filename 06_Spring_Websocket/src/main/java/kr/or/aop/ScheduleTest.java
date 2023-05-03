package kr.or.aop;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.or.member.model.service.MemberService;
import kr.or.member.model.vo.Member;


// @Component
public class ScheduleTest {
	
	@Autowired
	private MemberService memberService;
	
	@Scheduled(cron="0 0 0 * *")
	public void sendBirthCoupon() {
		// 오늘 날짜 기준으로 생일인 회원 조회 (예시)
//		ArrayList<Member> list = memberService.selectBirthMember();
//		 조회된 회원에게 생일 쿠폰 자동 insert
//		memberService.insertBirth(list);
	}
	
	
	// 하루 86400000ms, 1초 1000ms
	@Scheduled(fixedDelay = 5000)
	public void scheduleTest1() {
		// 가만히 있어도 실행되는 메소드이므로 매개변수가 없어야한다!
		System.out.println("5초에 한번 자동으로 실행 !!");
	}
	
//	@Scheduled(cron = "* * * * * *") // 1초에 1번 출력
//	@Scheduled(cron = "10 * * * * *") // 10초에 1번 출력
//	@Scheduled(cron = "20-23 * * * * *") // 매분 20,21,22,23초마다 동작(1분에 4번)
//	@Scheduled(cron = "0/15 * * * * *") // 매분 0초에서 시작해 15초마다 반복
	@Scheduled(cron = "0/10 * * * * *")
	public void scheduleTest2() {
		System.out.println("10초에 한번 자동으로 실행");
	}
}
