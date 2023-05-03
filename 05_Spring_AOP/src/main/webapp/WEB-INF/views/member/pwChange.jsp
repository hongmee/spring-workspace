<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<style>
	#pwChangeFrm{
		display : none;
	}
</style>
</head>
<body>
	<h1>비밀번호 변경하기</h1>
	<hr>
	현재 비밀번호 입력 : 
	<input type="password" name="currentPw">
	<button type="button" id="checkBtn">입력</button>
	<form action="/pwChange.do" method="post" id="pwChangeFrm">
		<input type="hidden" name="memberId" value="${sessionScope.m.memberId }">
		비밀번호 : <input type="password" name="memberPw"><br>
		비밀번호 확인 : <input type="password" name="memberPwRe"><br>
		<input type="submit" value="비밀번호 변경하기" onclick="return checkPw();">
	</form>
	<script>
		$('#checkBtn').on("click", function(){
			const memberPw = $("[name=currentPw]").val();
			const memberId = $("[name=memberId]").val();
			$.ajax({
				url : "/pwCheck.do",
				type : "post",
				data : {memberId : memberId, memberPw : memberPw},
				success : function(data){
					if(data == "ok"){
						$("#pwChangeFrm").slideDown();
					}else{
						alert("비밀번호를 확인하세요.");
					}
				},
				error : function(){
					console.log("ajax 실패");
				}
			})
		});
		
		function checkPw(){
			const memberPw = $("[name=memberPw]").val();
			const memberPwRe = $("[name=memberPwRe]").val();
			
			if(memberPw != memberPwRe){
				alert("비밀번호가 일치하지 않습니다.");
				return false;
			}
		}
	</script>
</body>
</html>