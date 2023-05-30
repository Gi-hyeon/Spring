<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>7장 JSP</h3>
	<p>JSTL 태그들의 Example ::: c:when, c:otherwise</p>
	
	<c:choose>
		<c:when test="${member.gender == 'M' }">
			<p>남자</p>
		</c:when>
		<c:when test="${member.gender == 'F' }">
			<p>여자</p>
		</c:when>
		<c:otherwise>
			<p>Others</p>
		</c:otherwise>
	</c:choose>
</body>
</html>