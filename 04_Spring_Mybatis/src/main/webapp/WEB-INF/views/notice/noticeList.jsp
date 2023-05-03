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
	<h1>공지사항 목록</h1>
	<hr>
	<c:if test="${not empty sessionScope.m}">
		<h3><a href="/noticeWriteFrm.do">글쓰기</a></h3>
	</c:if>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
		<c:forEach items="${list }" var="n">
		<tr>
			<td>${n.noticeNo }</td>
			<td><a href="/noticeView.do?noticeNo=${n.noticeNo }">${n.noticeTitle }</a></td>
			<td>${n.noticeWriter }</td>
			<td>${n.noticeDate }</td>
		</tr>
		</c:forEach>
	</table>
	<a href="/">메인으로</a>
</body>
</html>