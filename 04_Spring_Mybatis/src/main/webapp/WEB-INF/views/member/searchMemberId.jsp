<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원 정보 조회</h1>
	<hr>
	<ul>
	
		<li>${m.memberNo }</li>
		<li>${m.memberId }</li>
		<li>${m.memberPw }</li>
		<li>${m.memberName }</li>
		<li>${m.memberPhone }</li>
		<li>${m.memberEmail }</li>
	</ul>
	
	<a href="/">메인으로</a>
</body>
</html>