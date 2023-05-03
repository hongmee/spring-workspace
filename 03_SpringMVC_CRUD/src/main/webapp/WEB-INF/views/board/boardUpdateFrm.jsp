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
	<h1>게시글 수정</h1>
	<hr>
	<form action="/boardUpdate.do" method="post" enctype="multipart/form-data" id="updateFrm">
		<table border="1">
			<tr>
				<th>글번호</th>
				<td>
					<!-- 수정 불가하지만 값 전달 해야함  -->
					<input type="hidden" name="boardNo" value="${b.boardNo }">
					${b.boardNo }
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="boardTitle" value="${b.boardTitle }"></td>
			</tr>
			<tr>
				<!-- 수정 불가하고 값 전달 필요없음  -->
				<th>작성자</th>
				<td>${b.boardWriter }</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${b.boardDate }</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${b.boardDate }</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<c:forEach items="${b.fileList }" var="f">
					<p>
						${f.filename }
						<button type="button" onclick="deleteFile(this, ${f.fileNo}, '${f.filepath }');">삭제</button>
					</p>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<th>첨부파일 추가</th>
				<td><input type="file" name="boardFile" multiple></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="boardContent">${b.boardContent }</textarea>
			</tr>
			<tr>
				<th colspan="2">
					<input type="submit" value="수정하기">
				</th>
			</tr>
		</table>
	</form>
	<script>
		function deleteFile(obj, fileNo, filepath){
			// 파일 지우기 위해서 파일번호랑 파일 위치를 input 숨겨서 form 제출
			
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