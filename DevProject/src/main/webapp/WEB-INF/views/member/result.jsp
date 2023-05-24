<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result</title>
</head>
<body>
	<h4>결과</h4>
	<c:set value="${member }" var="member"/>
	
	<table border="1">
		<tr>
			<td>UserId</td>
			<td><c:out value="${member.userId }"/></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><c:out value="${member.password }"/></td>
		</tr>
		<tr>
			<td>UserName</td>
			<td><c:out value="${member.userName }"/></td>
		</tr>
		<tr>
			<td>E-mail</td>
			<td><c:out value="${member.email }"/></td>
		</tr>
		<tr>
			<td>BirthDay</td>
			<td><c:out value="${member.dateOfBirth }"/></td>
		</tr>
		<tr>
			<td>성별</td>
			<td><c:out value="${member.gender }"/></td>
		</tr>
		<tr>
			<td>국적</td>
			<td>
				<c:forEach items="${member.nationality }" var="memberNa">
					<c:out value="${memberNa }"/>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td>개발자 여부</td>
			<td><c:out value="${member.developer }"/></td>
		</tr>
		<tr>
			<td>외국인 여부</td>
			<c:if test="${member.foreigner eq true }">
				<td>외국인</td>
			</c:if>
			<c:if test="${member.foreigner ne true }">
				<td>내국인</td>
			</c:if>
		</tr>
		<tr>
			<td>소유 차량</td>
			<td>
				<c:forEach items="${member.cars }" var="memberCars">
					<c:out value="${memberCars }"/>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td>취미</td>
			<td>
				<c:forEach items="${member.hobby }" var="memberHobs">
					<c:out value="${memberHobs }"/>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td>우편번호</td>
			<td><c:out value="${member.address.postCode }"/></td>
		</tr>
		<tr>
			<td>주소</td>
			<td><c:out value="${member.address.location }"/></td>
		</tr>
		<tr>
			<td>카드1 - 번호</td>
			<td><c:out value="${member.cardList[0].no }"/></td>
		</tr>
		<tr>
			<td>카드1 - 유효년월</td>
			<td><c:out value="${member.cardList[0].validMonth }"/></td>
		</tr>
		<tr>
			<td>카드2 - 번호</td>
			<td><c:out value="${member.cardList[1].no }"/></td>
		</tr>
		<tr>
			<td>카드2 - 유효년월</td>
			<td><c:out value="${member.cardList[1].validMonth }"/></td>
		</tr>
		<tr>
			<td>소개</td>
			<td><c:out value="${member.introduction }"/></td>
		</tr>
	</table>
</body>
</html>















