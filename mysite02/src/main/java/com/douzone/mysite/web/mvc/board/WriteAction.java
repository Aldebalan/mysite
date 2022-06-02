package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//-----------------------------------------------------
		// 접근 제어
		HttpSession session = request.getSession();
		if(session == null) {
			WebUtil.redirect(request, response, request.getContextPath());
			return;
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		// 접근 제어
		if(authUser == null) {
			WebUtil.redirect(request, response, request.getContextPath());
			return;
		}		
		//-----------------------------------------------------
		
		// 답글 구분
		String no = request.getParameter("no");
		String gno = request.getParameter("gno");
		String ono = request.getParameter("ono");
		String depth = request.getParameter("depth");	
		
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(contents);	
		vo.setUser_no(authUser.getNo());
		
		
		if("".equals(gno) || "".equals(ono) || "".equals(depth)) {
			new BoardRepository().insert(vo);
			WebUtil.redirect(request, response, request.getContextPath() + "/board");
			return;
		}
		
		vo.setNo(Integer.parseInt(no));
		vo.setG_no(Integer.parseInt(gno));
		vo.setO_no(Integer.parseInt(ono));
		vo.setDepth(Integer.parseInt(depth));
		
		new BoardRepository().insertComent(vo);
		
		WebUtil.redirect(request, response, request.getContextPath() + "/board");
		
			
	}

}
