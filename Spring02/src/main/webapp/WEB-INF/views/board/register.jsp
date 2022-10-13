<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성 페이지</title>
</head>
<body>
	<h2>글 작성 페이지</h2>
	<form action="register" method="POST">
	<!-- 등록을 누른순간 name이 (mybatis-config의 typeAliases의 패키지의 클래스와 매칭??)
	or (컨트롤러의 registerPOST 메소드의 BoardVO?? )setter와 매칭되어 들어감 -->
		<div>
			<p>제목 :</p>
			<input type="text" name="boardTitle" placeholder="제목입력" required>
		</div>
		<div>
			<p>작성자 :</p>
			<input type="text" name="memberId" value="TEST" readonly>
		</div>
		<div>
			<p>내용 :</p>
			<textarea rows="20" cols="120" name="boardContent" placeholder="내용입력"
				required></textarea> 
		</div>
		<div>
			<input type="submit" value="등록">
		</div>
	</form>
</body>
</html>