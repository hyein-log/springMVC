<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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


</head>
<body>
<h1>직원의 상세내역</h1>

<form action="empDetail.do" method ="post">
<div class="form-group">
      <label>직원번호: ${emp.employee_id } </label>
<input class="form-control" type="hidden" name="employee_id" value="${emp.employee_id }">
</div>
<div class="form-group">
      <label>first name : </label>
<input class="form-control" type="text" name="first_name" value="${emp.first_name }">
</div>
<div class="form-group">
      <label> last name : </label>
<input class="form-control" type="text" name="last_name" value="${emp.last_name }">
</div>
<div class="form-group">
      <label> email : </label>
<input class="form-control" type="text" name="email" value="${emp.email }">
</div>
<div class="form-group">
      <label> phone : </label>
<input class="form-control" type="text" name="phone_number" value="${emp.phone_number }">
</div>
<div class="form-group">
      <label> commission : </label>
<input class="form-control" type="text" name="commission_pct" value="${emp.commission_pct }">
</div>
<div class="form-group">
      <label> manager_id : </label>
<input class="form-control" type="text" name="manager_id" value="${emp.manager_id }">
</div>
<div class="form-group">
      <label> department_id : </label>
<input class="form-control" type="text" name="department_id" value="${emp.department_id }">
</div>
<div class="form-group">
      <label> job_id : </label>
<input class="form-control" type="text" name="job_id" value="${emp.job_id }">
</div>
<div class="form-group">
      <label> 입사일 : </label>
<input class="form-control" type="date" name="hire_date" value="${emp.hire_date }">
</div>
<div class="form-group">
      <label> 급여 : </label>
<input class="form-control" type="text" name="salary" value="${emp.salary }">
</div>
<input class="btn btn-primary" type="submit" value="수정하기">
<input  class="btn btn-danger" type="reset" value="취소하기">
</form>




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