<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</head>
<body>
	<h2>Register</h2>
	<form:form modelAttribute="board" method="post" action="/crud/board/register">
		<table>
			<tr>
				<td>제목</td>
				<td>
					<input type="text" id="title" name="title" value="">
				</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>
					<input type="text" id="writer" name="writer" value="">
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<textarea rows="10" cols="30" name="content" id="content"></textarea>
				</td>
			</tr>
		</table>
		<div>
			<input type="button" id="btnRegister" value="등록">
			<input type="button" id="btnList" value="목록">
		</div>
	</form:form>
</body>
<script type="text/javascript">
$(function() {
	
	var board = $('#board');
	var btnRegister = $('#btnRegister');
	var btnList = $('#btnList');
	
	btnRegister.on('click', function() {
		var title = $('#title').val();		// 제목 가져온다
		var writer = $('#writer').val();	// 작성자 가져온다
		var content = $('#content').val();	// 내용 가져온다
		
		if (title == null || title == "") {
			alert("제목을 입력해주세요.");
			return false;
		}
		
		if (content == null || content == "") {
			alert("내용을 입력해주세요.");
			return false;
		}
		
		if (writer == null || writer == "") {
			alert("작성자를 입력해주세요.");
			return false;
		}
		
		board.submit();		// submit이벤트를 날려 서버로 데이터를 전송한다.
	});
	
	// 목록 버튼 클릭 시 이벤트
	btnList.on('click', function() {
		location.href = "/crud/board/list";	
	});
	
});
</script>
</html>


















