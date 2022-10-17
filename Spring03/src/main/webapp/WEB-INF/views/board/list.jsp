<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 포맷형태 바꾸는 taglib 이걸로 서버에서 받아온 날짜포맷변경할거-->
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<style type="text/css">
table, th, td {
	border-style: solid;
	border-width: 1px;
	text-align: center;
}

ul {
	list-style-type: none;
}

li {
	display: inline-block;
}
</style>

<meta charset="UTF-8">
<title>게시판 메인 페이지</title>
</head>
<body>
	<h1>게시판 메인</h1>
	
	<hr>
	<a href="register"><input type="button" value="글작성"></a>
	
	<table>
		<thead>
			<tr>
				<th style="width: 50px">번호</th>
				<th style="width: 650px">제목</th>
				<th style="width: 120px">작성자</th>
				<th style="width: 120px">작성일</th>
				<th style="width: 60px">댓글수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="vo" items="${list }">
				<tr>
					<td>${vo.boardId}</td>
					<!-- ${pageMaker.criteria.page} : 게시글을 클릭한 후 글 목록으로 돌아올때 현재페이지로 돌아오기위해 파라미터를 넘김 
					파라미터는 String만 되니깐 criteria자체로 넘길수는 없음-->
					<td><a href="detail?boardId=${vo.boardId}&page=${pageMaker.criteria.page}&numsPerPage=${pageMaker.criteria.numsPerPage}">${vo.boardTitle}</a></td>
					<td>${vo.memberId}</td>
					<fmt:formatDate value="${vo.boardDateCreated}" pattern="yyyy-MM-dd HH:mm:ss" var="fmtBoardDateCreated"/>
					<td>${fmtBoardDateCreated}</td>
					<td>${vo.replyCnt}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<ul style="display: inline;">
		<c:if test="${pageMaker.hasPrev}">
			<li><a href="list?page=1&numsPerPage=${pageMaker.criteria.numsPerPage}">맨앞</a></li>
			<li><a href="list?page=${pageMaker.startPageNo - 1}&numsPerPage=${pageMaker.criteria.numsPerPage}">이전</a></li>
		</c:if>
		<c:forEach var="i" begin="${pageMaker.startPageNo}" end="${pageMaker.endPageNo}" step="1">
			<li><a href="list?page=${i}&numsPerPage=${pageMaker.criteria.numsPerPage}">${i}</a></li>
		</c:forEach>
		<c:if test="${pageMaker.hasNext}">
			<li><a href="list?page=${pageMaker.endPageNo + 1}&numsPerPage=${pageMaker.criteria.numsPerPage}">다음</a></li>
			<li><a href="list?page=${pageMaker.getRealEndPageNo()}&numsPerPage=${pageMaker.criteria.numsPerPage}">맨뒤</a></li>
		</c:if>
	</ul>
	
	<form action="list?page=${pageMaker.criteria.page}&numsPerPage=${pageMaker.criteria.numsPerPage}" method="get" style="display: inline;">
		<select name="numsPerPage">
				<option>3</option>
				<option>5</option>
				<option>10</option>
		</select>
		<input type="submit" value="적용">
	</form>
	
	<!-- ${xxxxx_result } : BoardController의 registerPOST() 에서 RedirectAttributesfh 보낸 데이터 -->
	<input type="hidden" id="insertResult" value="${insert_result }">
	<input type="hidden" id="updateResult" value="${update_result }">
	<input type="hidden" id="deleteResult" value="${delete_result }">

	<script type="text/javascript">
		var insertResult = $('#insertResult').val();
		if (insertResult == 'success') {
			alert('새 글 작성 성공!');
		}
		var updateResult = $('#updateResult').val();
		if (updateResult == 'success') {
			alert('글 수정 성공!');
		}
		var deleteResult = $('#deleteResult').val();
		if (deleteResult == 'success') {
			alert('글 삭제 성공!');
		}
	</script>

</body>
</html>