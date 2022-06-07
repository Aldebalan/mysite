<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<div id="navigation">
			<ul>
				<li><a href="${pageContext.request.contextPath }">김성현</a></li>
				<li><a href="${pageContext.request.contextPath }/guestbook">방명록</a></li>
				<li><a href="${pageContext.request.contextPath }/board/index">게시판</a></li>
			</ul>
		</div>