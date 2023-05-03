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
	<h1>공지사항 상세보기</h1>
	<hr>
	<table border="1">
		<tr>
			<th>글번호</th>
			<td>${n.noticeNo }</td>
			<th>작성자</th>
			<td>${n.noticeWriter }</td>
			<th>작성일</th>
			<td>${n.noticeDate }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td colspan="5">${n.noticeTitle }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="5">${n.noticeContent }</td>
		</tr>
		<c:if test="${not empty sessionScope.m && sessionScope.m.memberId eq n.noticeWriter}">
		<tr>
			<th colspan="6">
				<a href="/noticeUpdateFrm.do?noticeNo=${n.noticeNo }">수정</a>
				<a href="/deleteNotice.do?noticeNo=${n.noticeNo }">삭제</a>
			</th>
		</tr>
		</c:if>
	</table>
	<a href="/noticeList.do">목록으로</a>
</body>
</html>