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
			<th>첨부파일</th>
			<td colspan="5">
				<c:forEach items="${n.fileList }" var="f">
					<a href="/noticeFileDown.do?fileNo=${f.fileNo }">${f.filename }</a>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="5">${n.noticeContent }</td>
		</tr>
		<c:choose>
			<c:when test="${not empty sessionScope.m && sessionScope.m.memberId eq n.noticeWriter }">
			<tr>
				<th colspan="6">
					<a href="/updateNoticeFrm.do?noticeNo=${n.noticeNo }">수정</a>
					<a href="/deleteNotice.do?noticeNo=${n.noticeNo }">삭제</a>
				</th>
			</tr>
			</c:when>
		</c:choose>
	</table>
	<a href="/noticeList.do">글 목록</a>
</body>
</html>