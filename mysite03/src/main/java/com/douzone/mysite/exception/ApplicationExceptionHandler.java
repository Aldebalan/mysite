package com.douzone.mysite.exception;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.douzone.mysite.dto.JSONResult;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class ApplicationExceptionHandler {
	private static final Log LOGGER = LogFactory.getLog(ApplicationExceptionHandler.class);
			
	@ExceptionHandler(Exception.class)
	public void handlerException(
			HttpServletRequest request,
			HttpServletResponse response,
			Exception e) throws Exception {
		
		//1. 로깅(logging)
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		LOGGER.error(errors.toString());
		
		//2. 요청 구분
		// JSON 요청: request header의 Accept에 application/json(o)
		// html 요청: request header의 Accept에 application/json(x)
		
		String accept = request.getHeader("accept");
		
		if(accept.matches(".*application/json.*")) {
			// 3. json 응답
			response.setStatus(HttpServletResponse.SC_OK);
			
			JSONResult jsonResult = JSONResult.fail(errors.toString());
			String jsonString = new ObjectMapper().writeValueAsString(jsonResult);
			
			OutputStream os = response.getOutputStream();
			os.write(jsonString.getBytes("UTF-8"));
			os.close();
		} else {
			// 3. 사과 페이지(종료)
			request.setAttribute("exception", errors.toString());
			request
				.getRequestDispatcher("/WEB-INF/views/error/exception.jsp")
				.forward(request, response);
		}
	}
}