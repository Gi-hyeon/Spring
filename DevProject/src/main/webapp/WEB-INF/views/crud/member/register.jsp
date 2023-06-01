<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</head>
<body>
	<h2>Register</h2>
	<form action="/crud/member/register" method="post" id="member">
		<table>
			<tr>
				<td>userId</td>
				<td>
					<input type="text" id="userId" name="userId">
				</td>
			</tr>
			<tr>
				<td>userPw</td>
				<td>
					<input type="text" id="userPw" name="userPw">
				</td>
			</tr>
			<tr>
				<td>userName</td>
				<td>
					<input type="text" id="userName" name="userName">
				</td>
			</tr>
		</table>
		<input type="button" id="btnRegister" value="Register">
		<input type="button" id="btnList" value="List">
	</form>
</body>
<script type="text/javascript">
$(function() {
	var member = $('#member');	
	var btnRegister = $('#btnRegister');	
	var btnList = $('#btnList');	

	btnRegister.on('click', function() {
		var userId = $('#userId').val();		// id
		var userPw = $('#userPw').val();		// pw
		var userName = $('#userName').val();	// name
		
		if (userId == null || userId == '') {
			alert("아이디를 입력해주세요.");
			return false;
		}
		
		if (userPw == null || userPw == '') {
			alert("비밀번호를 입력해주세요.");
			return false;
		}
		
		if (userName == null || userName == '') {
			alert("이름을 입력해주세요.");
			return false;
		}
		
		member.submit();	// 데이터 전송
		
	});

	btnList.on('click', function() {
		location.href = "/crud/member/list";
	});

});
</script>
</html>
























