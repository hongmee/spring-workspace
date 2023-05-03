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
	<h1>Spring MVC CRUD</h1>
	
	<c:choose>
		<c:when test="${not empty sessionScope.m }">
		<!-- 로그인 된 상태 -->
			<h2>[${sessionScope.m.memberName }]님 환영합니다.</h2>
			<h3><a href="/logout.do">로그아웃</a></h3>
			<form action="/searchMember.do" method="get">
				조회할 회원 아이디 입력 :
				<input type="text" name="memberId">
				<input type="submit" value="조회">
			</form>
			<h3><a href="/allMember.do">전체 회원 조회</a></h3>
			<h3><a href="/mypage.do">마이페이지</a></h3>
			<h3><a href="/deleteMember.do?memberNo=${sessionScope.m.memberNo }">회원탈퇴</a></h3>
			<h3><a href="/noticeList.do">공지사항</a></h3>
			<h3><a href="/boardList.do">게시판</a></h3>
		</c:when>
		<c:otherwise>
		<!-- 로그인 안된 상태 -->
			<form action="/login.do" method="post">
				<fieldset>
					<legend>로그인</legend>
					아이디 : <input type="text" name="memberId"><br>
					비밀번호  : <input type="password" name="memberPw"><br>
					<input type="submit" value="로그인">
				</fieldset>
			</form>
			<h3><a href="/joinFrm.do">회원가입</a></h3>
		</c:otherwise>
	</c:choose>
	
	
	

</body>
</html>