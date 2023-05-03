<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>글쓰기</h1>
	<hr>
	<form action="/boardWrite.do" method="post" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<th>제목</th>
				<td><input type="text" name="boardTitle"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>
					${sessionScope.m.memberId }
					<input type="hidden" name="boardWriter" value="${sessionScope.m.memberId }">
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td><input type="file" name="boardFile" multiple></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="boardContent"></textarea></td>
			</tr>
			<tr>
				<th colspan="2"><input type="submit" value="등록하기"></th>
			</tr>
		</table>
	</form>
</body>
</html>