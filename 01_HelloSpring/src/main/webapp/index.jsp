<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello Spring</h1>
	
	<!-- <h3><a href="/test1.do">결합도와 응집도</a></h3> -->
	
	<form action="/test1.do" method="get">
		<input type="radio" name="brand" value="samsung" id="samsung">
		<label for="samsung">삼성</label>
		<input type="radio" name="brand" value="lg" id="lg">
		<label for="lg">LG</label>
		<input type="submit" value="제출">
	</form>
	
	<h3><a href="/test2.do">DI 테스트</a></h3>
	
</body>
</html>