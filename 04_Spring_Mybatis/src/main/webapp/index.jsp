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
	<h1>Spring_Mybatis</h1>
	<hr>
	<c:choose>
		<c:when test="${empty sessionScope.m }">
			<form action="/login.do" method="post">
				<fieldset>
					<legend>로그인</legend>
					아이디 : <input type="text" name="memberId"><br>
					비밀번호 : <input type="password" name="memberPw"><br>
					<input type="submit" value="로그인">
				</fieldset>
			</form>
			<h3><a href="joinFrm.do">회원가입</a></h3>
		</c:when>
		<c:otherwise>
			<h2>[${sessionScope.m.memberName }]</h2>
			<h3><a href="/logout.do">로그아웃</a></h3>
			<form action="/searchMemberId.do">
				조회할 아이디 입력 :
				<input type="text" name="memberId">
				<input type="submit" value="조회">
			</form>
			<!-- 아이디 또는 이름으로 회원 정보 조회 -->
			<form action="/searchMember1.do">
				<select name="type">
					<option value="id">아이디</option>
					<option value="name">이름</option> 
				</select>
				<input type="text" name="keyword">
				<input type="submit" value="검색">
			</form>
			<h3>아이디 or 이름으로 검색</h3>
			<p>
				아이디만 입력하고 검색하면 아이디로 조회,
				이름만 입력하고 검색하면 이름으로 조회,
				둘 다 입력하고 검색하면 두개 and조건으로 조회
			</p>
			<form action="/searchMember2.do">
				아이디 : <input type="text" name="memberId"><br>
				이름 : <input type="text" name="memberName"><br>
				<input type="submit" value="검색">
			</form>
			
			<h3><a href="/searchMember3.do">전체 아이디 목록 조회</a></h3>			
			<h3><a href="/searchMember5.do">회원 목록 조회(부등호)</a></h3>
			
			<h3><a href="/noticeList.do">공지사항</a></h3>
			<h3><a href="/boardList.do?reqPage=1">게시판가기</a></h3>

			<h3><a href="/allMember.do">전체회원조회</a></h3>
			<h3><a href="/mypage.do">마이페이지</a></h3>
			
			<hr>
			<h3><a href="/deleteMember.do?memberNo=${sessionScope.m.memberNo }">회원탈퇴</a></h3>
		</c:otherwise>
	</c:choose>
</body>
</html>