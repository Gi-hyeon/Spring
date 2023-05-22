<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>READ</title>
</head>
<body>
	<h3>READ</h3>
	
	<form action="/board/get" method="get">
		<button type="submit" name="modify">Params 매핑(get?modify)</button>
		<button type="submit" name="remove">Params 매핑(get?remove)</button>
	</form>
	
	<a href="/board/get?list">Params 매핑(get?list)</a>
</body>
</html>