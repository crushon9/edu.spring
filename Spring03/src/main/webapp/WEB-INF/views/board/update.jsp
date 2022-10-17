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
<!-- <form action="update?page=${criteria.page }&numsPerPage=${criteria.numsPerPage }" method="POST">
	이렇게 GET으로 페이지 정보보내고 POST로 VO정보를 보내도 오류가 나지는 않음
	근데 post로 한번에 다보내는게 논리상 맞는거 같음!-->
		<input type="hidden" name="page" value="${criteria.page }">
		<input type="hidden" name="numsPerPage" value="${criteria.numsPerPage }">
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