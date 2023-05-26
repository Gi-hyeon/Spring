<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Read05</title>
</head>
<body>
	<h3>Read05 Result</h3>
	
	<p>user.address</p>
	user.address.postCode : ${user.address.postCode }<br>
	user.address.location : ${user.address.location }<br>
	
	<p>user.cardList</p>
	<c:forEach items="${user.cardList }" var="card">
		<c:out value="${card.no }"/><br>
		<c:out value="${card.validMonth }"/><br>
		
		<%-- <c:out value="${card.no } ${card.validMonth }"></c:out> --%>
	</c:forEach>
	
	
</body>
</html>