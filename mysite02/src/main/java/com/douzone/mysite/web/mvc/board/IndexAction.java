package com.douzone.mysite.web.mvc.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.core.FrameworkListener;
import org.apache.tomcat.util.http.fileupload.util.LimitedInputStream;
import org.eclipse.jdt.internal.compiler.ast.AND_AND_Expression;
import org.eclipse.jdt.internal.compiler.lookup.ImplicitNullAnnotationVerifier;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;

public class IndexAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*--------------------------------------------------------------
		 * 페이징 : p / 검색 : kwd
		 * --------------------------------------------------------------
		 */
		
		String pageNum = request.getParameter("p"); // 페이지 파라미터(ex p=1 <<< 1페이지)
		String kwd = request.getParameter("kwd"); // 검색 파라미터 (ex kwd=글 << 제목에 글이 들어간 게시글 검색)
		
		if(pageNum == null) { // 페이지 파라미터가 없을 경우 초기화면 즉 1페이지를 나타냄
			pageNum = "1";
		}
		int limit = 5;	// 한 페이지 당 보여 줄 게시글
		
		HashMap<String, Integer> pages = new HashMap<String, Integer>(); // map => 키+값으로 구성
		int currentPage = Integer.parseInt(pageNum);
		
		int startPage = limit * ((int)Math.ceil((double)currentPage / limit)-1)+1;
		if(startPage < 1) {
			startPage = 1;
		}
		
		
		int totalPage = (new BoardRepository().countBoard() % limit) == 0 ? new BoardRepository().countBoard()/limit : new BoardRepository().countBoard()/limit+1;
		int lastPage = startPage + (limit - 1) > totalPage ? totalPage : startPage + (limit-1);
		int prevPage = currentPage - 1 < 0 ? 1 : currentPage - 1;
		int nextPage = currentPage + 1 > totalPage ? totalPage : currentPage + 1;
		
		pages.put("currentPage", currentPage);	// 현재 페이지
		pages.put("startPage", startPage);		// 시작페이지
		pages.put("totalPage", totalPage);		// 밑에 (1 2 3 4... ) 개수
		pages.put("lastPage", lastPage);		// 마지막페이지
		pages.put("prevPage", prevPage);		// 이전페이지
		pages.put("nextPage", nextPage);		// 다음 페이지
		
		int totalBoard = new BoardRepository().countBoard();	// 전체 게시글 수 구하기
		request.setAttribute("totalBoard", totalBoard);
		/*--------------------------------------------------------------*/		
		List<BoardVo> list = new ArrayList<BoardVo>(); 				
		list = new BoardRepository().findAll(currentPage, kwd);	// 페이지별 게시글 SELECT 해오기
		
		request.setAttribute("list", list);
		request.setAttribute("pages", pages);
		
		
		WebUtil.forward(request, response, "board/index");
	}

}
