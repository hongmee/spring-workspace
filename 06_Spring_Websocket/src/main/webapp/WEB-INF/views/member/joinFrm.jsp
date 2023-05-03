<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
</head>
<body>
	<form action="/join.do" method="post">
		<fieldset>
			<legend>회원가입</legend>
			아이디 : <input type="text" name="memberId"><span id="idChk"></span><br>
			비밀번호 : <input type="password" name="memberPw"><br>
			이름 : <input type="text" name="memberName"><br>
			전화번호 : <input type="text" name="memberPhone"><br>
			이메일 : <input type="text" name="memberEmail"><br>
			<input type="submit" value="회원가입">
			<input type="reset" value="취소">
		</fieldset>
	</form>
	<a href="/">메인으로</a>
	<script>
		$('[name=memberId]').on("change", function(){
			const memberId = $(this).val();
			
			$.ajax({
				url : "/idCheck.do",
				type : "get",
				data : {memberId : memberId},
				success : function(data) {
					console.log(data);
					if(data == "ok"){
						$("#idChk").text("사용 가능한 아이디 입니다.");
						$("#idChk").css("color", "blue");
					}else{
						$("#idChk").text("이미 사용중인 아이디 입니다.")
						$("#idChk").css("color", "red");
					}
				}
				
			});
		});
	</script>
</body>
</html>