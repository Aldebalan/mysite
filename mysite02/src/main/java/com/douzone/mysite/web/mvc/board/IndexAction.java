package com.douzone.mysite.web.mvc.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;

public class IndexAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pageNum = request.getParameter("p");
		String kwd = request.getParameter("kwd");
		
		if(pageNum == null) {
			pageNum = "1";
		}
		int limit = 5;
		
		HashMap<String, Integer> pages = new HashMap<String, Integer>();
		int currentPage = Integer.parseInt(pageNum);
		
		int startPage = limit * ((int)Math.ceil((double)currentPage / limit)-1)+1;
		if(startPage < 1) {
			startPage = 1;
		}
		
		
		int totalPage = (new BoardRepository().countBoard() % limit) == 0 ? new BoardRepository().countBoard()/limit : new BoardRepository().countBoard()/limit+1;
		int lastPage = startPage + (limit - 1) > totalPage ? totalPage : startPage + (limit-1);
		int prevPage = currentPage - 1 < 0 ? 1 : currentPage - 1;
		int nextPage = currentPage + 1 > totalPage ? totalPage : currentPage + 1;
		
		pages.put("currentPage", currentPage);
		pages.put("startPage", startPage);
		pages.put("totalPage", totalPage);
		pages.put("lastPage", lastPage);
		pages.put("prevPage", prevPage);
		pages.put("nextPage", nextPage);
		
		int totalBoard = new BoardRepository().countBoard();
		request.setAttribute("totalBoard", totalBoard);
		/*--------------------------------------------------------------*/		
		List<BoardVo> list = new ArrayList<BoardVo>(); 				
		list = new BoardRepository().findAll(currentPage, kwd);
		
		request.setAttribute("list", list);
		request.setAttribute("pages", pages);
		
		
		WebUtil.forward(request, response, "board/index");
	}

}
