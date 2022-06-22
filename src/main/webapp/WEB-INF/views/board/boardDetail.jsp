<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>보드의 상세내역</h1>
<form action="boardUpdate.do" method="post">
bno :${board.bno } <input type="hidden" name ="bno" value="${board.bno }"><br>
title : <input type="text" name ="title" value="${board.title }"><br>
content : <input type="text" name ="content" value="${board.content }"><br>
작성일 : <input type="text" value="${board.regdate }" readonly="readonly"><br>
수정일 : <input type="text" value="${board.update_date }" disabled="disabled"><br>
작성자 : <input type="text" value="${board.writer }" readonly="readonly"><br>
<a href="${contextPath}/download.do?fileName=${board.pic}">
<img alt="" src="${pageContext.request.contextPath}/uploads/${board.pic}">
</a>
<input type="submit" value="수정">
<input type="reset" value="취소">
</form>
</body>
</html>