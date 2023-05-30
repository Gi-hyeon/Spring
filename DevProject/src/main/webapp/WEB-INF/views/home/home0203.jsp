<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>4. 표현언어(EL)</h3>
	
	<p>4) 비교 연산자를 이용한 방법</p>
	<table border="1">
		<tr>
			<td>\${userId == "hongkd" }</td>
			<td>${userId == "hongkd" }</td>
		</tr>
		<tr>
			<td>\${userId == "hongkd" }</td>
			<td>${userId eq "hongkd" }</td>
		</tr>
		<tr>
			<td>\${userId == "hongkdf" }</td>
			<td>${userId == "hongkdf" }</td>
		</tr>
		<tr>
			<td>\${userId == "hongkdf" }</td>
			<td>${userId == "hongkdf" }</td>
		</tr>
	</table>
</body>
</html>







