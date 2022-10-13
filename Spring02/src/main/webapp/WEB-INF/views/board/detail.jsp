<%@page import="edu.spring.ex02.domain.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 포맷형태 바꾸는 taglib 이걸로 서버에서 받아온 날짜포맷변경할거-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<title>${vo.boardTitle}</title>
</head>
<body>
	<h2>글 보기</h2>
	<div>
		<p>글번호 : ${vo.boardId }</p>
	</div>
	<div>
		<p>제목 : 
		<input type="text" name="boardTitle" value=" ${vo.boardTitle}" readonly></p>
	</div>
	<div>
		<p>작성자 : ${vo.memberId }</p>
		<fmt:formatDate value="${vo.boardDateCreated}" pattern="yyyy-MM-dd HH:mm:ss" var="fmtBoardDateCreated"/>
		<p>작성일 : ${fmtBoardDateCreated }</p>
	</div>
	<div>
		<textarea rows="20" cols="120" readonly>${vo.boardContent }</textarea>
	</div>
	<!-- ${page }, ${numsPerPage } : BoardController의 detail()에서 model에 담겨온 attribute	-->
	<a href="list?page=${page }&numsPerPage=${numsPerPage }"><input type="button" value="글 목록"></a>
	<a href="update?boardId=${vo.boardId }"><input type="button" value="글 수정"></a>
	<form action="delete" method="post" style="display: inline;">
		<input type="hidden" name="boardId" value="${vo.boardId }">
		<input type="submit" value="글 삭제">
	</form>
	
</body>
</html>