<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table, td {
	border: 1px solid MistyRose;
	border-collapse: collapse;
}
</style>
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
 <link rel="stylesheet" href="../css/common.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<c:set var="path" value="${pageContext.request.contextPath }"></c:set>
<script type="text/javascript">
$(function(){
	$("#btnRetrieveAll").click(f1);
	$("#btnRetrieceByid").click(f2);
	$("#btnmnglist").click(f3);
	$("#btnBymanager_id").click(f4);
	$("#btnByEmail").click(f5);
	$("#btnjoblist").click(f6);
	$("#btnByJob").click(f7);
	$("#btnByCondition").click(f8);
	$("#btninsert").click(f9);
	$("#btnupdate").click(f10);
	$("#btndelete").click(f11);
	$("#btnemailDup").click(f12);
	$("#btnByDept").click(f13);
});

function f13(){
	 var deptid =  $("#inputData").val() ;
	 $.ajax({
		 url:"${path}/emp/selectByDept.do/" + deptid,
		 success: function(responseData){
			 printEmp(responseData);
		 }
	 });
}

function f12(){
	 var email =  $("#inputData").val() ;
	 $.ajax({
		 url:"${path}/emp/emailDup.do/" + email,
		 success: function(responseData){
			 $("#here").html(responseData);
		 }
	 });
}
function f11(){
    var empid = $("input[name='employee_id']").val();
    alert(empid);
	$.ajax({
		url:"${path}/emp/empdelete.do/"+empid,
		type:"delete",
		success:function(responseData){
			alert(responseData + "건 삭제")
		}
	}); 	 
}


function makeJson(){
	//var s = $("#myfrm").serialize();
	var arr = $("#myfrm").serializeArray();
	console.log(arr);
	var obj={};
	$.each(arr,function(index, item){
		obj[item.name] = item.value;
	});
	return JSON.stringify(obj);
}
function f10(){
	$.ajax({
		url:"${path}/emp/empupdate.do",
		type:"put", //put은 수정할거라는 것을 의미함
		data:makeJson(),
		contentType : "application/json;charset=utf-8",
		success:function(responseData){
			alert(responseData+"건 수정");
		}
	});
	
}
function f9(){
	makeJson();
	$.ajax({
		url:"${path}/emp/insert.do",
		type:"post",
		data:makeJson(),
		contentType : "application/json;charset=utf-8",
		success:function(responseData){
			alert(responseData+"건 입력");
		}
	});
	
}
function f1(){
	$.ajax({
		url:"${path}/emp/emplist.do",
		success: function(responseData){
			printEmp(responseData);
		}
	});
}
function f2(empid){
	 if(typeof(empid)!="number") empid = $("#inputData").val() 
	 $.ajax({
		 url:"${path}/emp/empdetail.do/"+ empid,
		 success: function(responseData){
			 printEmpOne(responseData);
		 }
	 });
}
function f3(){
	$.ajax({
		url:"${path}/emp/mnglist.do",
		success: function(responseData){
			printEmp(responseData);
		}
	});
}
function f4(){
	var mid =  $("#inputData").val();
	$.ajax({
		url:"${path}/emp/selectByManager.do/"+mid,
		success: function(responseData){
			 printEmp(responseData);
		}
	});
}
function f5(){
	var email =  $("#inputData").val();
	$.ajax({
		url:"${path}/emp/selectByEmail.do/"+email,
		success: function(responseData){
			printEmpOne(responseData);
		}
	});
}
function f6(){
	$.ajax({
		url:"${path}/emp/joblist.do",
		success: function(joblist){
			var output = "<ul>";
			$.each(joblist, function(index, item){
				output+="<li>";
				for(var prop in item){
					output+=" "+prop+"-->"+item[prop]+"</li>"
				}
				output+="</li>";
			});
			
			$("#here").html(output+"</ul>");
		}
	});
}
function f7(){
	var job_id =  $("#inputData").val();
	$.ajax({
		url:"${path}/emp/selectByJob.do/"+job_id,
		success: function(responseData){
			console.log(responseData);
			printEmp(responseData);
		}
	});
}
function f8(){
	var deptid = $("#inputData").val();
	var jobid = $("#inputData2").val();
	var sal = $("#inputData3").val();
	var hire_date = $("#inputData4").val();
	$.ajax({
		 url:`${path}/emp/selectByCondition.do/\${deptid}/\${jobid}/\${sal}/\${hire_date}`,
		 success: function(responseData){
			 printEmp(responseData);
		 }
	 });
}
function printEmpOne(item){
	var output = ``;
	$("#here").html(output);
	 //일자, select tag의 selected설정 
	 $("select[name='department_id']").val(item.department_id)
	                      .prop("selected", true);
	 $("input[name='hire_date']").val(new Date(item.hire_date).toJSON().split('T')[0]);
	 $("input[name='manager_id']").val(item["manager_id"]).prop("selected", true);
	 $("input[name='job_id']").val(item["job_id"]).prop("selected", true);
	 
	 
	 $("input[name='employee_id']").val(item["employee_id"]);
	 $("input[name='first_name']").val(item["first_name"]);
	 $("input[name='last_name']").val(item["last_name"]);
	 $("input[name='email']").val(item["email"]);
	 $("input[name='phone_number']").val(item["phone_number"]);
	 $("input[name='commission_pct']").val(item["commission_pct"]);

	 $("input[name='salary']").val(item["salary"]);

}
function printEmp(emplist){
	var output = `
		<table>
		<tr>
			<td>순서</td>
			<td>상세보기</td>
			<td>직원번호</td>
			<td>성</td>
			<td>이름</td>
			<td>입사일</td>
			<td>급여</td>
			<td>전화번호</td>
			<td>부서</td>
			<td>커미션</td>
			<td>매니저</td>
			<td>직책</td>
			<td>이메일</td>
		</tr>
	`;
	$.each(emplist, function(index, item){
		output+=`
			<tr>
			<td>\${index+1}</td>
			<td><button onclick="f2(\${item.employee_id})">상세보기</button></td>
			<td>\${item.employee_id}</td>
			<td>\${item.first_name}</td>
			<td>\${item.last_name}</td>
			<td>\${item.hire_date}</td>
			<td>\${item.salary}</td>
			<td>\${item.phone_number}</td>
			<td>\${item.department_id}</td>
			<td>\${item.commission_pct}</td>
			<td>\${item.manager_id}</td>
			<td>\${item.job_id}</td>
			<td>\${item.email}</td>
		</tr>
		`;
	});
	$("#here").html(output+"</table>");
}
</script>
</head>
<body>
	<h1>@RestController연습</h1>
	<img alt="이미지" src="${path}/images/muffin.png">
조회조건1(공통):<input type="text" id="inputData"><br>
조회조건2(jobid):<input type="text" id="inputData2" value="IT_PROG"><br>
조회조건3(sal):<input type="number" id="inputData3" value="0"><br>
조회조건4(hire_date):<input type="date" id="inputData4" value="2007-01-01"><br>
	<br>
	<button id="btnRetrieveAll">모든직원조회</button>
	<button id="btnjoblist">모든직업조회</button>
	<button id="btnmnglist">모든매니저조회</button>
	<br>
	<hr>
	<button id="btnRetrieceByid">특정직원조회</button>
	<button id="btnByDept">특정부서조회</button>
	<button id="btnByJob">특정직업조회</button>
	<button id="btnByCondition">특정조건조회</button>
	<button id="btnByEmail">특정이메일조회</button>
	<br>
	<hr>
	<button id="btnemailDup">이메일중복확인</button>
	<button id="btnBymanager_id">매니저아이디로 직원조회</button>
	<br>
	<hr>
	<button id="btninsert">insert</button>
	<button id="btnupdate">update</button>
	<button id="btndelete">delete</button>
	<br>
	<hr>
	<div id="here"></div>
	<h1>직원 신규등록</h1>

<form id="myfrm" >
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
      <option value="${manager.employee_id}">${manager.first_name}</option>
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

</form>
</body>
</html>