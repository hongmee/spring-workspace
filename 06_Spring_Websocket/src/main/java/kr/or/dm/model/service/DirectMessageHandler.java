package kr.or.dm.model.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DirectMessageHandler extends TextWebSocketHandler {
	// 쪽지 실시간 알림에 접속한 회원을 관리할 Map(key : id, value : 접속세션)
	private HashMap<String, WebSocketSession> connectionMemberList;
	
	@Autowired
	private DmService service;
	
	public DirectMessageHandler() {
		super();
		connectionMemberList = new HashMap<String, WebSocketSession>();
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception{
		// 접속시
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
		// 메세지 보내면 받음
		String receiveMsg = message.getPayload(); // 클라이언트가 보낸 실제 메세지 내용
		System.out.println("클라이언트 전송 메세지  :" + receiveMsg);
		
		// 객체 생성 : 안쓰는거 권장
//		JsonParser parser= new JsonParser();
//		JsonElement element = parser.parse(receiveMsg);
		
		// static : 사용 권고 방식
		JsonElement element = JsonParser.parseString(receiveMsg); 
		String type = element.getAsJsonObject().get("type").getAsString();
		
		if(type.equals("enter")) {
			
			String memberId = element.getAsJsonObject().get("memberId").getAsString();
			
			// 최초 접속이므로 접속한 회원의 아이디를 map에 추가
			connectionMemberList.put(memberId, session);
			
			// 현재 읽지않은 쪽지 수를 조회해서 되돌려줌
			int dmCount = service.selectDmCount(memberId);
			
			// 자바스크립트 객체 생성 {type:"myDmCount", "dmCount":10}
			JsonObject obj = new JsonObject();
			obj.addProperty("type", "myDmCount");
			obj.addProperty("dmCount", dmCount);
			
			String resultStr = new Gson().toJson(obj);
			TextMessage tm = new TextMessage(resultStr);
			// 접속한 세션에 receiveMsg함수 실행
			session.sendMessage(tm);
			
		}else if(type.equals("sendDm")) {
			// 쪽지를 보냈을때 읽지않은 쪽지 수 update
			
			String memberId = element.getAsJsonObject().get("receiver").getAsString();
			WebSocketSession receiver = connectionMemberList.get(memberId);
			
			if(receiver != null) {
				// 접속했을때만 변경 필요함 (새로고침 안하고 update됨)
				int dmCount = service.selectDmCount(memberId);
				
				JsonObject obj = new JsonObject();
				obj.addProperty("type", "myDmCount");
				obj.addProperty("dmCount", dmCount);
				
				String resultStr = new Gson().toJson(obj);
				TextMessage tm = new TextMessage(resultStr);
				receiver.sendMessage(tm);
			}
		}else if(type.equals("readCheck")) {
			// 쪽지 상세보기 후 읽지않은 쪽지 수 갱신 (읽지않음 -> 읽음)
			String sender = element.getAsJsonObject().get("sender").getAsString();
			String receiver = element.getAsJsonObject().get("receiver").getAsString();
			
			// 쪽지를 읽은 회원의 읽지않은 쪽지 수 갱신
			int dmCount = service.selectDmCount(receiver);
			
			JsonObject obj1 = new JsonObject();
			obj1.addProperty("type", "myDmCount");
			obj1.addProperty("dmCount", dmCount);
			
			String resultStr = new Gson().toJson(obj1);
			TextMessage tm = new TextMessage(resultStr);
			// receiver가 접속한 사람
			session.sendMessage(tm);

			
			// 받은 사람이 읽으면 보낸 사람의 보낸쪽지함에서 읽음으로 처리
			WebSocketSession senderSession = connectionMemberList.get(sender);
			
			if(senderSession != null) {
				// 방금 읽은 쪽지의 보낸사람이 접속해있으면 
				JsonObject obj2 = new JsonObject();
				obj2.addProperty("type", "readDm");
				String resultMsg = new Gson().toJson(obj2);
				TextMessage tm2 = new TextMessage(resultMsg);
				senderSession.sendMessage(tm2);
			}
			
		}
			
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
		// 접속 끊어진 회원을 접속회원목록에서 제거
		connectionMemberList.values().remove(session);
	}

}
