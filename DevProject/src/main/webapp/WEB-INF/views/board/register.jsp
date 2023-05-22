<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>REGISTER</title>
</head>
<body>
	<h3>REGISTER</h3>
	
	<form action="/board/post" method="post">
		<button type="submit" name="register">Params 매핑(post?register)</button>
	</form>
	
	<a href="/board/get?list">Params 매핑(get?list)</a>
</body>
</html>