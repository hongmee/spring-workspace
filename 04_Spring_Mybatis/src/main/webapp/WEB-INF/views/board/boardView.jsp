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
	<h1>게시글 상세보기</h1>
	<hr>
	<table border="1">
		<tr>
			<th>글번호</th>
			<td>${b.boardNo }</td>
			<th>작성자</th>
			<td>${b.boardWriter }</td>
			<th>작성일</th>
			<td>${b.boardDate }</td>
		</tr>
		<tr>
			<th>제목</th>	
			<td colspan="5">${b.boardTitle }</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td colspan="5">
				<c:forEach items="${b.fileList }" var="f">
					<p>
						<a href="/boardFileDown.do">${f.filename }</a>
					</p>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td colspan="6">
				${b.boardContent }
			</td>
		</tr>
		<c:if test="${not empty sessionScope.m && sessionScope.m.memberId eq b.boardWriter }">
			<tr>
				<th colspan="6">
					<a href="/boardUpdateFrm.do?boardNo=${b.boardNo }">수정</a>
					<a href="/boardDelete.do?boardNo=${b.boardNo }">삭제</a>
				</th>
			</tr>	
		</c:if>
	</table>
	<a href="/boardList.do?reqPage=1">메인으로</a>
</body>
</html>