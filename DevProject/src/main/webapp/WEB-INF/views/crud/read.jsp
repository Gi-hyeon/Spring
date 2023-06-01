<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</head>
<body>
	<h2>Read</h2>
	<table>
		<tr>
			<td>제목</td>
			<td>${board.title }</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${board.writer }</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${board.content }</td>
		</tr>
		<tr>
			<td>작성일</td>
			<td>${board.regDate }</td>
		</tr>
	</table>
	<!-- 공통으로 사용할 form태그를 하나 생성한다. -->
	<form action="/crud/board/remove" method="post" id="delForm">
		<input type="hidden" name="boardNo" value="${board.boardNo }">
	</form>
	<button type="button" id="btnModify">수정</button>
	<button type="button" id="btnDelete">삭제</button>
	<button type="button" id="btnList">목록</button>
</body>
<script type="text/javascript">
$(function() {
	var delForm = $('#delForm');
	var btnModify = $('#btnModify');
	var btnDelete = $('#btnDelete');
	var btnList = $('#btnList');
	
	// 수정 버튼 클릭 시 이벤트
	btnModify.on('click', function() {
		delForm.attr('action', '/crud/board/modify');
		delForm.attr('method', 'get');
		delForm.submit();
	});
	
	// 삭제 버튼 클릭 시 이벤트
	btnDelete.on('click', function() {
		if (confirm("정말로 삭제하시겠습니까?")) {
			delForm.submit();
		}
	});
	
	// 목록 버튼 클릭 시 이벤트
	btnList.on('click', function() {
		location.href = "/crud/board/list";
	});
	
	
	
});
</script>
</html>




















