<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table, td {
border:1px solid gray;
border-collapse: collapse;
padding: 10px;
}
.color1 {
	background-color: MistyRose;
}
.color3{
background-color: LavenderBlush;
}
.color4{
background-color: SeaShell;
}
table { width: 80%; margin: 0 auto;}
</style>
</head>
<body>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
	<h1>게시판목록</h1>
	
	<a href="boardInsert.do">게시글 작성하기</a>
	<hr>
	<table>
	<tr class = color1>
			<td>순서</td>
			<td>역순서</td>
			<td>번호</td>
			<td>제목</td>
			<td>내용</td>
			<td>등록일</td>
			<td>업데이트일</td>
			<td>작성자</td>
			<td>${resultMessage }</td>
		</tr>
		<c:forEach items="${boardLists }" var = "board" varStatus="status">
		
		<tr class ="${status.count%2==0?'color3':'color4' }">
			<td>${status.count }</td>
			<td>${boardLists.size()-status.index }</td>
			<td><a href = "${path}/board/boardDetail.do?boardid=${board.bno }">${board.bno }</a></td> <%--<a>태그는 get타입밖에 못받음 post안됨 --%>
			<td>${board.title }</td>
			<td>${board.content }</td>
			<td>${board.regdate }</td>
			<td>${board.update_date }</td>
			<td>${board.writer }</td>
			<td><button class="btnDel" data-bno="${board.bno }">삭제하기</button></td>
		</tr>
		</c:forEach>
	</table>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
	$(function(){
		//# : 아이디를 의미함. 아이디는 문서 내에서 유일함.
		//. : 클래스를 의미함. 클래스는 여러 개 있을 수 있음
		$(".btnDel").click(function(){
			var bno = $(this).attr("data-bno");
			if(confirm(bno+" 삭제하시겠습니까?")){
					location.href = "boardDelete.do?bno="+bno;
			}
		});
	});
	</script>
</body>
</html>