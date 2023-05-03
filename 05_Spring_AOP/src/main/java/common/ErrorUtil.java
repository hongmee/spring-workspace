package common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

// @Component를 포함하고 있는 annotation
// @Controller annotation 포함되어있는 객체에서 에러발생했을때 처리하는 annotation
@ControllerAdvice
public class ErrorUtil {
	
	@ExceptionHandler(Exception.class)
	public String handleException(Exception e) {
		// 빨간 글씨
		System.err.println("에러 발생");
		System.err.println(e.getMessage());
		
		return "error/error1";
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public String notFound(NoHandlerFoundException e) {
		System.err.println("RequestMapping 오류");
		System.err.println(e.getMessage());
		
		return "error/error2";
	}

}
