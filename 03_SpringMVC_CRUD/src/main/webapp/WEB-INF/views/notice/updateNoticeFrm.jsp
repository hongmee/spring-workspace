<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<h1>공지사항 수정</h1>
	<hr>
	<form action="/updateNotice.do" method="post" enctype="multipart/form-data" id="updateFrm">
		<table border="1">
			<tr>
				<th>글번호</th>
				<td>
					<input type="hidden" name="noticeNo" value="${n.noticeNo }">
					${n.noticeNo }
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="noticeTitle" value="${n.noticeTitle }"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${n.noticeWriter }</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${n.noticeDate }</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<c:forEach items="${n.fileList }" var="f">
					<p>
						${f.filename }
						<button type="button" onclick="deleteFile(this, ${f.fileNo}, '${f.filepath }');">삭제</button>
					</p>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<th>첨부파일 추가</th>
				<td><input type="file" name="noticeFile" multiple></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="noticeContent">${n.noticeContent}</textarea></td>
			</tr>
			<tr>
				<th colspan="2">
					<input type="submit" value="수정완료">
				</th>
			</tr>
		</table>
	</form>
	<script>
		function deleteFile(obj, fileNo, filepath){
			// 파일 지우기 위해서 파일번호랑 실제 저장된 파일 이름을 input에 숨겨서 form 제출
			
			const fileNoInput = $("<input>");
			fileNoInput.attr("name", "fileNo");
			fileNoInput.val(fileNo);
			fileNoInput.hide();
			
			const filepathInput = $("<input>");
			filepathInput.attr("name", "filepath");
			filepathInput.val(filepath);
			filepathInput.hide();
			
			$("#updateFrm").append(fileNoInput).append(filepathInput);
			$(obj).parent().remove();
		}
	</script>
</body>
</html>