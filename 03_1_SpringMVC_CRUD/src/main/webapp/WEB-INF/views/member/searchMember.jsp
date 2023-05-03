<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원정보 출력</h1>
	<hr>
	<ul>
		<li>${member.memberNo }</li>
		<li>${member.memberId }</li>
		<li>${member.memberPw }</li>
		<li>${member.memberName }</li>
		<li>${member.memberPhone }</li>
		<li>${member.memberEmail }</li>
	</ul>
	<a href="/">메인으로</a>
</body>
</html>