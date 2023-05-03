<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>


<!-- 최초 접속시 현재 내가 읽지않은 쪽지 수를 가져옴
다른사람이 접속한 사람한테 쪽지를 보낸 경우  쪽지를 받은사람 쪽지 수 변경 -->
<h3>읽지않은 쪽지 수 : <span id="dmCount"></span></h3>
<input type="hidden" id="memberId" value="${sessionScope.m.memberId}">

<script>
	let memberId;
	let ws;
	
	////////////////////////////////////////////////////////
	// 소켓 연결 하려면
	// 핸들러 만들고 servlet-context.xml에서 웹소켓 객체 생성하고, 매핑 해줘야함 !!!!!!
	
	$(function(){
		memberId = $("#memberId").val();
		ws = new WebSocket("ws://192.168.10.23/dm.do");
		ws.onopen = startDm;
		ws.onmessage = receiveMsg;
		ws.onclose = endDm;
	});
	
	function startDm(){
		console.log("쪽지DM 연결 완료");
		const data = {type : "enter", memberId : memberId};
		// 긴 문자열로 바꿔서 보냄
		ws.send(JSON.stringify(data));
	}
	
	function receiveMsg(param){
		console.log(param.data);
		// 전송받은 문자열을 객체 형식으로 인식시켜야함 
		const result = JSON.parse(param.data);
		console.log(result);
		
		if(result.type == "myDmCount"){
			
			// 객체 타입이면
			$("#dmCount").text(result.dmCount);
			// 받은 쪽지함 갱신
			getReceiverDm();
			
		}else if(result.type == "readDm"){
			
			// 쪽지 읽으면 보낸쪽지함 갱신
			getSendDm();
			
		}
	}
	
	function endDm(){
		console.log("쪽지DM 연결 종료")
	}
</script>

