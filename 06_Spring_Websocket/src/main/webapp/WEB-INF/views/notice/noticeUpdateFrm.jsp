<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>공지사항 수정</h1>
	<hr>
	<form action="/updateNotice.do" method="post">
			<table border="1">
				<tr>
					<th>글번호</th>
					<td>${n.noticeNo }<input type="hidden" name="noticeNo" value="${n.noticeNo }"></td>
					<th>작성자</th>
					<td>${n.noticeWriter }</td>
					<th>작성일</th>
					<td>${n.noticeDate }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td colspan="5"><input type="text" name="noticeTitle" value="${n.noticeTitle }"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="5"><textarea name="noticeContent">${n.noticeContent }</textarea></td>
				</tr>
				<tr>
					<th colspan="6">
						<input type="submit" value="수정하기">
					</th>
				</tr>
			</table>
	</form>
	<a href="/noticeView.do?noticeNo=${n.noticeNo }">돌아가기</a>
</body>
</html>