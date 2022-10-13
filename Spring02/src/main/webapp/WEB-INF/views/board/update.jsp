<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정 페이지</title>
</head>
<body>
	<h2>글 수정 페이지</h2>
	<form action="update" method="POST">
		<div>
			<p>글번호 : ${vo.boardId }</p>
			<input type="hidden" name="boardId" value="${vo.boardId}">
		</div>
		<div>
			<p>제목 : <input type="text" name="boardTitle" value=" ${vo.boardTitle}"></p>
		</div>
		<div>
			<p>작성자 : ${vo.memberId }</p>
			<p>작성일 : ${vo.boardDateCreated }</p>
		</div>
		<div>
			<textarea rows="20" cols="120" name="boardContent">${vo.boardContent }</textarea>
		</div>
		<div>
			<input type="submit" value="수정">
		</div>
	</form>
</body>
</html>