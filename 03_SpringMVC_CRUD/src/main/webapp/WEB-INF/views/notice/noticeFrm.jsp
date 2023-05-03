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
	<h1>공지사항 글 작성</h1>
	<hr>
	<form action="/insertNotice.do" method="post" enctype="multipart/form-data">
	<fieldset>
		제목 : <input type="text" name="noticeTitle"><br>
		첨부파일 : <input type="file" name="noticeFile" multiple><br>
		내용 : <textarea name="noticeContent"></textarea><br>
		<input type="submit" value="작성">
		<input type="reset" value="취소">
		<!-- 작성자 -->
		<input type="hidden" name="noticeWriter" value="${sessionScope.m.memberId }">
	</fieldset>
	</form>
	<a href="/noticeList.do">글 목록</a>
</body>
</html>