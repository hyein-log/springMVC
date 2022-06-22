<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="../css/common.css">
<style type="text/css">
table, td {
border: 1px solid gray;
	border-collapse: collapse;
	padding: 10px;
	margin: auto;
}
tr:nth-child(even){
background-color: AliceBlue;
}
tr:nth-child(odd){
background-color: Snow;
}
tr:first-child{
background-color: LightSkyBlue;
}
</style>
</head>
<body>
<h1>부서목록</h1>
<a href="deptInsert.do">신규 부서 등록</a>
<table>
<tr>
<td>부서번호</td>
<td>부서이름</td>
<td>매니저</td>
<td>지역번호</td>
<td></td>

</tr>
<c:forEach items="${deptLists }" var="dept">
<tr>
<td><a href="../html/dept.do?dept_id=${dept.department_id }">${dept.department_id }</a></td>
<td>${dept.department_name}</td>
<td>${dept.manager_id }</td>
<td>${dept.location_id }</td>
<td><button class ="btn btn-danger" data-deptid ="${dept.department_id }">삭제하기</button></td>
</tr>
</c:forEach>
</table>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
$(function(){
	$(".btn-danger").on("click", f); //on은 click하면 f라는 함수를 실행하라는 명령어를 연결해주는 역할임
});
function f(){
	var deptid = $(this).attr("data-deptid");
	location.href = "deptDelete.do?deptid="+deptid;
}
</script>
</body>
</html>