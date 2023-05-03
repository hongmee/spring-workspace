package kr.or.iei.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.vo.BeanFactory;
import kr.or.iei.vo.TV;

/**
 * Servlet implementation class Test1Servlet
 */
public class Test1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test1Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 결합도 높음 -> 5줄을 수정해야함
//		SamsungTV tv = new SamsungTV();
//		tv.powerOn();
//		tv.powerOff();
//		tv.volumeUp();
//		tv.volumeDown();
//		
//		LgTV tv = new LgTV();
//		tv.on();
//		tv.off();
//		tv.soundUp();
//		tv.soundDown();
		
		
		// 2. 인터페이스 (결합도 낮추는 과정) -> 한줄만 바꾸면 됨
//		SamsungTV tv = new SamsungTV();
//		LgTV tv = new LgTV();
		
		// 3. 다형성 (결합도 낮음) -> 객체 생성 함수(오른쪽)만 바꿔주면 됨
////	TV tv = new SamsungTV();
//		TV tv = new LgTV();
//		
//		tv.powerOn();
//		tv.powerOff();
//		tv.volumeUp();
//		tv.volumeDown();

		
		// 4. 사용자 선택 (라디오 타입) -> 코드 변경 필요 없음
		String brand = request.getParameter("brand");
		System.out.println(brand);
		
		BeanFactory factory = new BeanFactory();
		
		TV tv = factory.getBean(brand);
		tv.powerOn();
		tv.powerOff();
		tv.volumeUp();
		tv.volumeDown();
		
		
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
