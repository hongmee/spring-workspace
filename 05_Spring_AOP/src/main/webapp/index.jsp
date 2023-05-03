<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
</head>
<body>
	<h1>Spring_AOP</h1>
	<hr>
	<a href="/test1.do">테스트 요청</a>
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
			<h3><a href="#">비밀번호 찾기</a></h3>
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
<!-- 			<h3>아이디 or 이름으로 검색</h3> -->
<!-- 			<p> -->
<!-- 				아이디만 입력하고 검색하면 아이디로 조회, -->
<!-- 				이름만 입력하고 검색하면 이름으로 조회, -->
<!-- 				둘 다 입력하고 검색하면 두개 and조건으로 조회 -->
<!-- 			</p> -->
<!-- 			<form action="/searchMember2.do"> -->
<!-- 				아이디 : <input type="text" name="memberId"><br> -->
<!-- 				이름 : <input type="text" name="memberName"><br> -->
<!-- 				<input type="submit" value="검색"> -->
<!-- 			</form> -->
			
<!-- 			<h3><a href="/searchMember3.do">전체 아이디 목록 조회</a></h3>			 -->
<!-- 			<h3><a href="/searchMember5.do">회원 목록 조회(부등호)</a></h3> -->
			
<!-- 			<h3><a href="/noticeList.do">공지사항</a></h3> -->
<!-- 			<h3><a href="/boardList.do?reqPage=1">게시판가기</a></h3> -->

			<h3><a href="/allMember.do">전체회원조회</a></h3>
			<h3><a href="/mypage.do">마이페이지</a></h3>
			
			<hr>
<%-- 			<h3><a href="/deleteMember.do?memberNo=${sessionScope.m.memberNo }">회원탈퇴</a></h3> --%>
			
			
			
			
			<!-- ajax -->
			<button id="allMemberAjax">전체회원조회</button>
			<div id="ajaxResult"></div>
			
			<script>
				$("#allMemberAjax").on("click", function(){
					$.ajax({
						url : "/ajaxAllMember.do",
						// ajax 요청에 의한 데이터를 되돌려줄때 문제점
						// 1. 문자열 형식 -> 객체 (스크립트에서 데이터타입 json 지정)
						// 2. 한글깨짐 --> 어려움.. controller로 이동해서 둘다 해결가능!!!여기선못햄
						// 문제점 1. 한글 깨짐 2. 객체 배열이 아닌 문자열 배열
						// 데이터타입을 json으로 해야 String에서 객체 배열 형태로 나옴(Object)
						// dataType : "json";
						success : function(data){
// 							console.log(typeof data);
// 							console.log(data);
							$("#ajaxResult").empty();
							const table = $("<table>");
							const titleTr = $("<tr>");
							titleTr.html("<th>번호</th><th>아이디</th><th>이름</th><th>전화번호</th>");
							table.append(titleTr);
							
							for(let i=0 ; i<data.length; i++){
								const tr = $("<tr>");
								tr.append("<td>"+data[i].memberNo+"</td>");
								tr.append("<td>"+data[i].memberId+"</td>");
								tr.append("<td>"+data[i].memberName+"</td>");
								tr.append("<td>"+data[i].memberPhone+"</td>");
								table.append(tr);
							}
							$("#ajaxResult").append(table);
						}
					})
				});
				
			</script>
			
		</c:otherwise>
	</c:choose>
</body>
</html>