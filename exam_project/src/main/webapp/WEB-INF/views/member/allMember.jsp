<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>전체회원정보</h1>
	<hr>
	<table border="1">
		<tr>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>나이</th>
		</tr>
		<c:forEach items="${list }" var="m">
		<tr>
			<td>${m.memberId }</td>
			<td>${m.memberPw }</td>
			<td>${m.memberName }</td>
			<td>${m.memberAge }</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>