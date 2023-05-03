<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<style>
	.chatting{
		width: 500px;
		display: none;
	}
	.messageArea{
		overflow-y: auto;
		border: 1px solid black;
		height: 500px;
		display: flex;
		flex-direction: column;
		background-color: #b2c7d9;
	}
	.messageArea>p{
		text-align: center;
		width: 100%;
	}
	#sendMsg{
		width: 75%;
	}
	#sendBtn{
		width: 20%;	
	}
	.chat{
		margin-bottom: 10px;
		padding: 8px;
		border-radius: 3px;
	}
	.left{
		position: relative;
		max-width: 300px;
		align-self: flex-start;
		background-color: #fff;
		border-radius: 10px;
		margin-left: 16px;
		padding: 15px;
	}
	.left:after{
		/* 말풍선 꼬리 */
		content:'';
		position: absolute;
		border-style: solid;
		border-width: 15px 15px 15px 0;
		border-color: transparent #fff;
		display: block;
		width: 0;
		z-index: 1;
		left: -15px;
		top: 12px;
	}
	.right{
		position: relative;
		max-width: 300px;
		align-self: flex-end;
		background-color: #ffeb33;
		border-radius: 10px;
		margin-right: 16px;
		padding: 15px;
	}
	.right:after{
		/* 말풍선 꼬리 */
		content:'';
		position: absolute;
		border-style: solid;
		border-width: 15px 0 15px 15px;
		border-color: transparent #ffeb33;
		display: block;
		width: 0;
		z-index: 1;
		right: -15px;
		top: 12px;
	}
</style>
</head>
<body>
	<h1>전체채팅</h1>
	<hr>
	<c:if test="${not empty sessionScope.m }">
	
		<button onclick="initChat('${sessionScope.m.memberId}');">채팅 시작하기</button>
		
		<div class="chatting">
			<div class="messageArea"></div>
			<div class="sendBox">
				<input type="text" id="sendMsg">
				<button id="sendBtn" onclick="sendMsg();">전송</button>
			</div>
		</div>
		
		<script>
			// 웹소켓 객체를 저장하는 변수
			let ws;
			// 회원 아이디를 저장하는 변수
			let memberId;
			function initChat(param){
				memberId = param;
				// 웹소켓 연결 시도 (꼭 id주소로 작성)
				ws = new WebSocket("ws://192.168.10.23/chat.do");
				// 웹소켓 연결 성공시 실행할 함수 지정
				ws.onopen = startChat;
				// 서버에서 데이터를 받으면 처리할 함수 지정
				ws.onmessage = receiveMsg;
				// 웹소켓 연결이 종료되면 실행될 함수 지정
				ws.onclose = endChat;
				
				$(".chatting").slideDown();
			}
			
			function startChat(){
				console.log("웹소켓 연결완료");
				
				const data = {type : "enter", msg : memberId};
				
				// console.log(data);
				// console.log(JSON.stringify(data));
				// 연결 완료시 아이디를 전송
				// 객체 타입을 문자열로 변환
				ws.send(JSON.stringify(data));
				
				appendChat("<p>채팅방에 입장하셨습니다</p>");
			}
			function receiveMsg(param){
				appendChat(param.data)
				console.log(param);
			}
			function endChat(){
				console.log("웹소켓 연결종료");
			}
			
			function sendMsg(){
				const msg = $("#sendMsg").val();
				
				if(msg != ''){
					const data = {type : "chat", msg : msg};
					
					ws.send(JSON.stringify(data));
					
					appendChat("<div class='chat right'>"+msg+"</div>");
					$("#sendMsg").val('');
				}
			}
			$("#sendMsg").on("keyup", function(e){
				// 엔터 치면 채팅 보내짐 
				if(e.keyCode == 13){
					sendMsg();
				}
			});
			function appendChat(chatMsg){
				$(".messageArea").append(chatMsg);
				$(".messageArea").scrollTop($(".messageArea")[0].scrollHeight);
			}
		</script>
	</c:if>
	<hr>
	<a href="/">메인으로 돌아가기</a>
</body>
</html>