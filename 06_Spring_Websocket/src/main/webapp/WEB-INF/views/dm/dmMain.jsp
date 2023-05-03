<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/dm.css">
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<h1>쪽지함</h1>
	<hr>
	<h3>쪽지보내기</h3>
	<button onclick="sendDmModal();">쪽지보내기</button>
	<hr>
	
	<h3>받은 쪽지함</h3>
	<table border="1" class="receiveDmTbl">
		<thead>
			<tr>
				<th>보낸사람</th>
				<th>내용</th>
				<th>시간</th>
				<th>읽음여부</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	<hr>
	<h3>보낸 쪽지함</h3>
	<table border="1" class="sendDmTbl">
		<thead>
			<tr>
				<th>받은사람</th>
				<th>내용</th>
				<th>시간</th>
				<th>읽음여부</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	
	
	
	<!-- 쪽지보내기 모달 -->
	<div id="sendDm-modal" class="modal-wrapper">
		<div class="modal">
			<div class="modal-header">
				<h2>쪽지보내기</h2>
			</div>
			<hr>
			<div class="modal-content">
				<div class="sendDmFrm">
					<label>수신자 : </label>
					<select name="receiver"></select>
					<textarea name="dmContent"></textarea>
					<input type="hidden" id="sender" value="${sessionScope.m.memberId }">
					<button onclick="dmSend();">쪽지보내기</button>
					<button onclick="closeModal();">닫기</button>
				</div>
			</div>
		</div>
	</div>
	
	
	<!-- 쪽지 상세보기 모달 -->
	<div id="dmDetail" class="modal-wrapper">
		<div class="modal">
			<div class="modal-header">
				<h2>쪽지내용</h2>
			</div>
			<hr>
			<div class="modal-content">
				<div class="dmFrm">
					<div>
						<span>발신자 : </span>
						<span id="detailSender"></span>
					</div>
					<div>
						<span>날짜 : </span>
						<span id="detailDate"></span>
					</div>
					<div id="detailContent"></div>
					<button onclick="replyDm();">답장</button>
					<button onclick="closeDetail();">닫기</button>
				</div>
			</div>
		</div>
		
	
	</div>
	<script src="/resources/js/dm.js"></script>
</body>
</html>