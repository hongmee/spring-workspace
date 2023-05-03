package kr.or.iei.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatcherServlet
 */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// (1) 최초 요청
		// Dispatcher -> HandlerMapping한테 uri 전달
		
		// 1. 인코딩
		request.setCharacterEncoding("utf-8");
		// 사용자가 요청한 컨트롤러 주소 확인
		String uri = request.getRequestURI();
		System.out.println("사용자 요청 uri : "+uri);
		
		HandlerMapping map = new HandlerMapping();
		// (2) 컨트롤 주소 리턴
		Controller controller = map.getController(uri);
		// (3) 작업 , (5) 결과
		String result = controller.request(request);
		// System.out.println(result);
		
		ViewResolver resolver = new ViewResolver();
		// (6) 결과 처리 view/result/.jsp
		String view = resolver.getView(result);
		// (7) 페이지 이동
		response.sendRedirect(view);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
