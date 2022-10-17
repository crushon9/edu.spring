<%@page import="edu.spring.ex03.domain.BoardVO"%>
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
	<!-- ${criteria.page } : BoardController의 detail()에서 model에 담겨온 attribute	-->
	<a href="list?page=${criteria.page }&numsPerPage=${criteria.numsPerPage }"><input type="button" value="글 목록"></a>
	<a href="update?boardId=${vo.boardId }&page=${criteria.page }&numsPerPage=${criteria.numsPerPage }"><input type="button" value="글 수정"></a>
	<form action="delete" method="post" style="display: inline;">
		<input type="hidden" name="boardId" value="${vo.boardId }">
		<input type="hidden" name="page" value="${criteria.page }">
		<input type="hidden" name="numsPerPage" value="${criteria.numsPerPage }">
		<input type="submit" value="글 삭제">
	</form>
	<hr>
	
	<!-- 댓글 입력 -->
	<div style="text-align: center;">
			<input type="hidden" id="boardId" value="${vo.boardId }">
			<input type="text" id="memberId">
			<input type="text" id="replyContent">
			<button id="btn_add">작성</button>
	</div>
		
	<hr>
	<!-- 댓글을 출력할 div공간 마련 -->
	<div style="margin-left: 40px">
		<div id="replies"></div>
	</div>
	<div>
		<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	</div>
	
	
	<script type="text/javascript">
		$(document).ready(function() {
			getAllReplies();

			// 버튼 클릭시 댓글 추가
			$('#btn_add').click(function() {
				var boardId = $('#boardId').val();
				var memberId = $('#memberId').val();
				var replyContent = $('#replyContent').val();
				// $.ajax로 댓글 입력
				$.ajax({
					type : 'POST',
					url : '/ex03/replies',
					headers : { // 정보를 전송할때는 (GET방식을 빼고는) headers 넣어야함
						'Content-Type' : 'application/json',
						'X-HTTP-Method-Override' : 'POST'
					},
					data : JSON.stringify({
						'boardId' : boardId,
						'memberId' : memberId,
						'replyContent' : replyContent
					}), // JSON으로 변환
					success : function(result, status) {
						console.log('댓글입력결과 : ' + result);
						console.log('HttpStatus : ' + status);
						if (result == 1) {
							getAllReplies();
						}
					} // end ajax.success.function
				}); // end ajax
			}); // end btn_add.click

			// 게시판 댓글 전체 가져오기 
			function getAllReplies() {
				console.log('getAllReplies() 호출');
				var boardId = $('#boardId').val();;
				var memberId = $('#memberId').val();
				var url = '/ex03/replies/all/' + boardId; // REST API 방식 적용
				// $.getJSON 방식이므로 JSON.stringify하지 않아도 되고, header도 없어도됨
				$.getJSON(			
						url,
					function(data) {// 서버에서 온 data가 저장되어있음
						var replyList = '';
						$(data).each(function() {
							var replyDateCreated = new Date(this.replyDateCreated); // string 날짜를 다시 Date로 변환
							var btn_disabled = 'disabled';
							if (memberId == this.memberId) { // this : data 컬렉션의 한줄 데이터를 의미
								btn_disabled = '';
							}
							replyList += '<div class="reply_item">' // 여러개가 생성될거니깐 class를 부여했고, 댓글 한줄마다 호출시 구분해주는 역할
								+ '<pre>'
								+ '<input type="hidden" class="replyId" value="' + this.replyId + '"/>'
								+ '<input type="hidden" class="memberId" value="' + this.memberId + '"/>'
								+ this.memberId // getJSON으로 받아온 data에 저장된 memberId 의미
								+ '&nbsp;&nbsp;'
								+ '<input type="text" class="replyContent" value="' + this.replyContent + '" readonly/>'
								+ '&nbsp;&nbsp;'
								+ replyDateCreated
								+ '&nbsp;&nbsp;'
								+ '<button class="btn_update" ' + btn_disabled + '>수정</button>'
								+ '<button class="btn_delete" ' + btn_disabled + '>삭제</button>'
								+ '</pre>'
								+ '</div>';
						}); // end data.each
						$('#replies').html(replyList); // 반복문으로 생성된 html태그 출력
					}
				); // end getJSON
			} // end getAllReplies
			
			// 수정 버튼을 클릭하면 댓글 수정
			$('#replies').on('click', '.reply_item .btn_update', function(){
				var isReadOnly = $(this).prevAll('.replyContent').prop('readonly');
				if (isReadOnly == true) { // readonly가 true면
					// readonly 속성제거 후 버튼 변경
					$(this).prevAll('.replyContent').removeAttr('readonly');
					$(this).prevAll('.replyContent').css({"border-color":"red"});
					$(this).text("수정확인");
					$(this).nextAll('.btn_delete').hide();
				} else { // 아니라면 댓글 수정
					// 선택된 댓글의 replyId, replyContent 값을 저장
					// prevAll() : 선택된 노드 이전에 위치해 있는 모든 형제 노드를 접근
					var replyId = $(this).prevAll('.replyId').val();
					var replyContent = $(this).prevAll('.replyContent').val();
					console.log("수정 replyId : " + replyId + ", replyContent : " + replyContent);
					// ajax로 서버로 수정 데이터 전송
					$.ajax({
						type : 'PUT',
						url : '/ex03/replies/' + replyId,
						headers : {
							'Content-Type' : 'application/json',
							'X-HTTP-Method-Override' : 'PUT'
						},
						data : JSON.stringify({ // ****JSON으로 파싱해서 보내야 오류가 안남!
							'replyId' : replyId,
							'replyContent' : replyContent
						}),
						success : function(result) {
							console.log("댓글수정결과 : " + result);
							getAllReplies();
						}
					});
				}
			}); // end replies.on.btn_update
			
			// 삭제 버튼을 클릭하면 선택된 댓글 삭제
			$('#replies').on('click', '.reply_item .btn_delete', function(){
				var boardId = $('#boardId').val();;
				var replyId = $(this).prevAll('.replyId').val();
				console.log("삭제 replyId : " + replyId + " , boardId : " + boardId);
				$.ajax({
					type : 'DELETE',
					url : '/ex03/replies/' + replyId, // 이 데이터는 담기지 않네?
					headers : {
						'Content-Type' : 'application/json',
						'X-HTTP-Method-Override' : 'DELETE'
					},
					data : JSON.stringify({
						'boardId' : boardId
					}), // 여기 데이터는 vo에 자동으로 담기는데
					success : function(result) {
						console.log("댓글삭제결과 : " + result);
						getAllReplies();
					}
				});
			}); // end replies.on.btn_delete
			

		}); // end document
	</script>
	
</body>
</html>