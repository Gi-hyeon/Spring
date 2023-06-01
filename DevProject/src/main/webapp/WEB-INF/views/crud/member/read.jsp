<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Read</h2>
	<table>
		<tr>
			<td>userId</td>
			<td>${member.userId }</td>		
		</tr>
		<tr>
			<td>userName</td>
			<td>${member.userName }</td>		
		</tr>
		<tr>
			<td>auth - 1</td>
			<td>${member.authList[0].auth }</td>		
		</tr>
		<tr>
			<td>auth - 2</td>
			<td>${member.authList[1].auth }</td>		
		</tr>
		<tr>
			<td>auth - 1</td>
			<td>${member.authList[2].auth }</td>		
		</tr>
	</table>
	<a href="/crud/member/modify?userNo=${member.userNo }">
		Modify
	</a>	
	<button type="button" id="btnRemove">Remove</button>
	<button type="button" id="btnList">List</button>
</body>
</html>