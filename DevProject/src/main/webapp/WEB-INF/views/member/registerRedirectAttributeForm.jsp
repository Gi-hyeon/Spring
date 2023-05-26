<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RegisterRedirectAttributeForm</title>
</head>
<body>
	<h3>registerRedirectAttributeForm</h3>
	<form action="/redirectattribute/register" method="post">
		userId : <input type="text" name="userId" value="hongkd"><br>
		password : <input type="text" name="password" value="1234"><br>
		<input type="submit" value="register">
	</form>
</body>
</html>