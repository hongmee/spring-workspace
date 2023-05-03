<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/updateMember.do" method="post">
		<fieldset>
			<legend>마이페이지</legend>
			회원번호 : <input type="text" name="memberNo" value="${sessionScope.m.memberNo }" readonly><br>
			아이디 : <input type="text" name="memberId" value="${sessionScope.m.memberId }" disabled><br>
			비밀번호 : <input type="password" name="memberPw" value="${sessionScope.m.memberPw }"><br>
			이름 : <input type="text" name="memberName" value="${sessionScope.m.memberName }" disabled><br>
			전화번호 : <input type="text" name="memberPhone" value="${sessionScope.m.memberPhone }"><br>
			이메일 : <input type="text" name="memberEmail" value="${sessionScope.m.memberEmail }"><br>
			<input type="submit" value="정보수정">
		</fieldset>
	</form>
	<a href="/">메인으로</a>
</body>
</html>