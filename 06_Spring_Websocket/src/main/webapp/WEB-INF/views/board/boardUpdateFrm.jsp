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
	<h1>게시글 수정하기</h1>
	<hr>
	<form action="/boardUpdate.do" method="post" enctype="multipart/form-data" id="updateFrm">
		<table border="1">
			<tr>
			<th>글번호</th>
			<td>
				<input type="hidden" name="boardNo" value="${b.boardNo }">
				${b.boardNo }
			</td>
			<th>작성자</th>
			<td>${b.boardWriter }</td>
			<th>작성일</th>
			<td>${b.boardDate }</td>
		</tr>
		<tr>
			<th>제목</th>	
			<td colspan="5"><input type="text" name="boardTitle" value="${b.boardTitle }"></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td colspan="5">
				<c:forEach items="${b.fileList }" var="f">
					<p>
						${f.filename }
						<button type="button" onclick="deleteFile(this, ${f.fileNo}, '${f.filepath }');">삭제</button>
					</p>
				</c:forEach>
			<input type="file" name="boardFile" multiple>
			</td>
		</tr>
		<tr>
			<th colspan="6">
				<textarea name="boardContent" style="width:90%">${b.boardContent }</textarea>
			</th>
		</tr>
		<tr>
			<th colspan="6">
				<input type="submit" value="수정하기">
			</th>
		</tr>
		</table>
	</form>
	<script>
		function deleteFile(obj, fileNo, filepath){
			// 파일 지우기 위해서 파일번호랑 파일 위치를 input 숨겨서 form 제출
			// input name을 같게 해서 받으면 배열로 전달됨
			
			// <input>
			const fileNoInput = $("<input>");
			// <input name="fileNo">
			fileNoInput.attr("name", "fileNo");
			// <input name="fileNo" value="10">
			fileNoInput.val(fileNo);
			// <input name="fileNo" value="10" style="display:none;">
			fileNoInput.hide();
			
			const filepathInput = $("<input>");
			filepathInput.attr("name", "filepath");
			filepathInput.val(filepath);
			filepathInput.hide();
			
			$("#updateFrm").append(fileNoInput).append(filepathInput);
			// 화면에 p태그 안보이게 지움
			$(obj).parent().remove();
		}
	</script>
</body>
</html>