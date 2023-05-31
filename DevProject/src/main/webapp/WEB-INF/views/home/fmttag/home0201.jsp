<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>2) type 속성을 지정되지 않으면 기본값은 number이다.</h4>
	<p>number : ${coin }</p>
	
	<fmt:parseNumber value="${coin }" var="coinNum"/>
	<p>coinNum : ${coinNum }</p>
	
	<fmt:formatNumber value="${coin }" type="currency" var="coinNum"/>
	<p>coinNum : ${coinNum }</p>
	
	<fmt:formatNumber value="${coin }" type="percent" var="coinNum"/>
	<p>coinNum : ${coinNum }</p>
</body>
</html>