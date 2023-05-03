<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Spring MVC CRUD 복습</h1>
	<form action="/searchMember.do" method="get">
		조회할 회원 아이디 입력 : 
		<input type="text" name="memberId">
		<input type="submit" value="조회">
	</form>
</body>
</html>