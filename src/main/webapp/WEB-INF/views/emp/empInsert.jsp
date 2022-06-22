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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#duplicateCheckBtn").click(function(){
		var empid = $("#employee_id").val();
		if(empid ==""||empid==null){
			alert("직원번호는 필수입니다.");
			$("#employee_id").focus();
		}
		$.ajax({
			url:"duplicateCheck.do",
			data:{"empid":empid},
			type:"get",
			success:function(responseData){
				//중복 :1, 중복아님 : 0
				$("#message").html(responseData==1?"사용불가":"사용가능");
			},
			fail:function(){}
		});
	});
});
$(function(){
	$("#duplicateCheckBtn2").click(function(){
		var email = $("#email").val();
		if(email ==""||email==null){
			alert("이메일은 필수입니다.");
			$("#email").focus();
		}
		$.ajax({
			url:"emailduplicateCheck.do",
			data:{"email":email},
			type:"get",
			success:function(responseData){
				//중복 :1, 중복아님 : 0
				$("#message2").html(responseData==1?"사용불가":"사용가능");
			},
			fail:function(){}
		});
	});
});
</script>
</head>
<body>
<h1>직원 신규등록</h1>

<form action="empInsert.do" method ="post">
<div class="form-group">
      <label>직원번호: ${emp.employee_id } </label>
<input class="form-control" type="number" name="employee_id" id="employee_id" >
<input type="button" id="duplicateCheckBtn" value="중복체크">
<span id="message"></span>
</div>
<div class="form-group">
      <label>first name : </label>
<input class="form-control" type="text" name="first_name">
</div>
<div class="form-group">
      <label> last name : </label>
<input class="form-control" type="text" name="last_name" >
</div>
<div class="form-group">
      <label> email : </label>
<input class="form-control" type="text" name="email"id="email" >
<input type="button" id="duplicateCheckBtn2" value="중복체크">
<span id="message2"></span>
</div>
<div class="form-group">
      <label> phone : </label>
<input class="form-control" type="text" name="phone_number" >
</div>
<div class="form-group">
      <label> commission : </label>
<input class="form-control" type="text" name="commission_pct" >
</div>
<div class="form-group">
      <label> 매니저 : </label>
 <select name = "manager_id">
      <c:forEach items="${mlist}" var = "manager">
      <option value="${manager.key}">${manager.value}</option>
      </c:forEach>
      </select>
</div>
<div class="form-group">
      <label> 부서 : </label>
      <select name = "department_id">
      <c:forEach items="${dlist}" var = "dept">
      <option value="${dept.department_id}">${dept.department_name}</option>
      </c:forEach>
      </select>

</div>
<div class="form-group">
      <label> 직책 : </label>
 <select name = "job_id">
      <c:forEach items="${jlist}" var = "job">
      <option value="${job.job_id}">${job.job_title}</option>
      </c:forEach>
      </select>
</div>
<div class="form-group">
      <label> 입사일 : </label>
<input class="form-control" type="date" name="hire_date" >
</div>
<div class="form-group">
      <label> 급여 : </label>
<input class="form-control" type="text" name="salary" >
</div>
<input class="btn btn-primary" type="submit" value="입력하기">
<input  class="btn btn-danger" type="reset" value="취소하기">
<input  class="btn btn-primary" type="button" value="목록보기" id ="emplist">
</form>
<script type="text/javascript">

$(function(){
	$("#emplist").click(function(){
		location.href="emplist.do";
	});
});
</script>



<br>
직원번호: ${emp.employee_id } <br>
first name : ${emp.first_name } <br> 
last name: ${emp.last_name}<br> 
email:${emp.email}<br> 
phone:${emp.phone_number}<br> 
commission:${emp.commission_pct}<br> 
manager_id:${emp.manager_id}<br> 
department_id:${emp.department_id}<br> 
job_id:${emp.job_id}<br> 
입사일:${emp.hire_date}<br> 
급여:${emp.salary}<br> 
</body>
</html>