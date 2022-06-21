package com.douzone.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {
	private static final Log LOGGER = LogFactory.getLog(ApplicationExceptionHandler.class);
			
	@ExceptionHandler(Exception.class)
	public String handlerException(Model model, Exception e) {
		//1. 로깅(logging)
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		LOGGER.error(errors.toString());
		
		//2. 사과 페이지(종료)
		model.addAttribute("exception", errors.toString());
		return "error/exception";
	}
}
