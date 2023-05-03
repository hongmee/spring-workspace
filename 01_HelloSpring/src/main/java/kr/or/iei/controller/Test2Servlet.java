package kr.or.iei.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.iei.vo.Member;
import kr.or.iei.vo.MemberMgr;

/**
 * Servlet implementation class Test2Servlet
 */
public class Test2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test2Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// spring 설정 파일을 읽어오는 객체 생성
		AbstractApplicationContext cntx = new GenericXmlApplicationContext("memberContext.xml");
		
		// Object타입 리턴, bean id = member1
		Member member1 = (Member)cntx.getBean("member1");
		System.out.println(member1.getName());
		System.out.println(member1.getAge());
		System.out.println(member1.getAddr());

		
		Member member2 = (Member)cntx.getBean("member2");
		System.out.println(member2.getName());
		System.out.println(member2.getAge()); // 20
		System.out.println(member2.getAddr());
		member2.setAge(1000);
		System.out.println(member2.getAge()); // 1000
		Member member22 = (Member)cntx.getBean("member2");
		System.out.println(member22.getAge()); // 20(scope="prototype"), 1000
		
		
		MemberMgr mm = (MemberMgr)cntx.getBean("mm");
		Member member3 = mm.getMember();
		System.out.println(member3.getName());
		System.out.println(member3.getAge());
		System.out.println(member3.getAddr());
		
		response.sendRedirect("/");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
