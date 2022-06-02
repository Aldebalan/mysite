package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//-----------------------------------------------------
		// 접근 제어
		HttpSession session = request.getSession();
		if(session == null) {
			WebUtil.redirect(request, response, request.getContextPath()+ "/board");
			return;
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		// 접근 제어
		if(authUser == null) {
			WebUtil.redirect(request, response, request.getContextPath()+ "/board");
			return;
		}					
		//-----------------------------------------------------
		
		long no = Integer.parseInt(request.getParameter("no"));
	
		new BoardRepository().delete(no, authUser.getNo());
		
		WebUtil.redirect(request, response, request.getContextPath() + "/board");
	}

}
