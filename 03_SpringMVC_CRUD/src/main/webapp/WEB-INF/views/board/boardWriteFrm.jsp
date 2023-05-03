<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시글 작성</h1>
	<hr>
	<form action="/boardWrite.do" method="post" enctype="multipart/form-data">
		<fieldset>
			제목 : <input type="text" name="boardTitle"><br>
			<!-- 첨부파일 input name은 vo와 다르게 하기, string이 아니라 file타입이라서 못 들어감 -->
			<!-- multiple : 여러개 올림 -->
			첨부파일 : <input type="file" name="boardFile" multiple><br>
			내용 : <textarea name="boardContent"></textarea><br>
			<input type="submit" value="작성하기">
			<input type="hidden" name="boardWriter" value="${sessionScope.m.memberId }">
		</fieldset>
	</form>
</body>
</html>