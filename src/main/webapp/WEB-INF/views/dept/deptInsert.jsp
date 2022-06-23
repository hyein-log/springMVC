<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<h1>부서등록</h1>
<form action="${path}/dept/deptInsert.do" method="post">
부서번호 : <input type="number" name="department_id" ><br>
부서이름 : <input type="text" name="department_name" ><br>
매니저번호 : <input type="number" name="manager_id" value ="100"><br>
지역번호 : <input type="number" name="location_id" value = "1700"><br>
<input type="submit" value ="입력">
<input type="reset" value ="취소">
</form>
</body>
</html>