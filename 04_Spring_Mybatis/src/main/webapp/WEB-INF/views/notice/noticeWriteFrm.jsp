<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>공지사항 작성</h1>
	<hr>
	<form action="/noticeWrite.do" method="post">
		<fieldset>
			<legend>공지사항 작성</legend>
			<table border="1">
				<tr>
					<th>제목</th>
					<td><input type="text" name="noticeTitle"></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${sessionScope.m.memberId }
						<input type="hidden" name="noticeWriter" value="${sessionScope.m.memberId }">
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea name="noticeContent"></textarea></td>
				</tr>
				<tr>
					<th colspan="2">
						<input type="submit" value="글쓰기">
						<input type="reset" value="취소">
					</th>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>