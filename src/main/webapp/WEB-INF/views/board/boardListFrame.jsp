<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
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
			<td id = td>${resultMessage }</td>
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