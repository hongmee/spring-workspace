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
	<h1>게시물 상세보기</h1>
	<hr>
	<table border="1">
		<tr>
			<th>글번호</th>
			<td>${b.boardNo }</td>
			<th>제목</th>
			<td>${b.boardTitle }</td>
			<th>작성자</th>
			<td>${b.boardWriter }</td>
			<th>작성일</th>
			<td>${b.boardDate }</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td colspan="7">
				<c:forEach items="${b.fileList }" var="f">
					<a href="/boardFileDown.do?fileNo=${f.fileNo }">${f.filename }</a>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="7">${b.boardContent }</td>
		</tr>
		<c:if test="${not empty sessionScope.m && sessionScope.m.memberId eq b.boardWriter }">
		<tr>
			<th colspan="8">
				<a href="/boardUpdateFrm.do?boardNo=${b.boardNo }">수정</a>
				<a href="/boardDelete.do?boardNo=${b.boardNo }">삭제</a>
			</th>
		</tr>
		</c:if>
	</table>
</body>
</html>