<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.servletContext.contextPath }/board" method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					
					<c:set var='count' value='${fn:length(list) }' />
					<c:set var='newline' value='\n' />
					<c:forEach items='${list }' var='vo' varStatus='status'>
					
					<tr>
						<td>${vo.no }</td>
						<td style="text-align: left; padding-left:${vo.depth * 10}px">
								<c:if test="${vo.depth > 0 }">
									<img src='${pageContext.servletContext.contextPath }/assets/images/reply.png' />
								</c:if>
								<c:if test="${kwd != ''}"> 	
									<a  href="${pageContext.request.contextPath }/board?a=view&postNo=${vo.no}&page=${currentPage}&kwd=${kwd}">${vo.title }</a>
								</c:if>
								<c:if test="${kwd == ''}">
									<a  href="${pageContext.request.contextPath }/board?a=view&postNo=${vo.no}&page=${currentPage}">${vo.title }</a>
								</c:if></td>
						<td>${vo.userNo }</td>
						<td>${vo.hit }</td>
						<td>${vo.regDate }</td>
						<td><a href="${pageContext.request.contextPath }/board?a=deleteform" class="del"><img
							src='${pageContext.servletContext.contextPath }/assets/images/recycle.png' class='del'/>삭제</a></td>
					</tr>
					</c:forEach>
				</table>

				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li class="selected">2</li>
						<li><a href="">3</a></li>
						<li><a href="">▶</a></li>
					</ul>
				</div>
				<!-- pager 추가 -->

				<div class="bottom">
					<a
						href="${pageContext.request.contextPath }/board?a=write"
						id="new-book">글쓰기</a>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>