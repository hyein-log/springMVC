<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Hello world! ***로그인한 사람 : ${sessionScope.user}</h1>

	<P>The time on the server is ${serverTime}.</P>
	<p>나의 이름은 ${myname }</p>
	<p>나의 이름은 ${myname2 }</p>
	<c:set var="path" value="${pageContext.request.contextPath }" />
	<ul>
		<li><a href="${path }/hello/my1">MyController의 my1로 연결하기</a></li>
		<li><a href="${path }/hello/my2">MyController의 my2로 연결하기</a>
			<form action="${path }/hello/my2" method="get">
				<input type="submit" value="get전송">
			</form></li>
		<li>
			<form action="${path }/hello/my2" method="post">
				<input type="submit" value="post전송">
			</form>
		</li>
		<li>
			<form action="${path }/hello/param.do" method="get">
				<input type="text" name="userid" value="hi" /><br> <input
					type="text" name="userpass" value="1234" /><br>
				<!-- 				<input type="text" name="email" value="email@naver.com"/><br> -->
				<input type="submit" value="param전송">
			</form>
		</li>
		<li>
			<form action="${path }/hello/param2.do" method="get">
				<input type="text" name="userid" value="ohio" /><br> <input
					type="number" name="userpass" value="1234" /><br> <input
					type="text" name="email" value="email@naver.com" /><br> <input
					type="date" name="birthday" value="2022-06-22" /><br> <input
					type="submit" value="param2전송해서 받기">
			</form>
		</li>
		<li>
			<form action="${path }/hello/param3.do" method="get">
				<input type="text" name="userid" value="ohio" /><br> <input
					type="number" name="userpass" value="1234" /><br> <input
					type="text" name="email" value="email@naver.com" /><br> <input
					type="date" name="birthday" value="2022-06-22" /><br> <input
					type="submit" value="param3전송해서 받기">
			</form>
		</li>
		<li>
			<form action="${path }/hello/param4.do" method="get">
				<input type="text" name="model" value="sm7" /><br> <input
					type="number" name="price" value="4000" /><br> <input
					type="text" name="color" value="blue" /><br> <input
					type="text" name="email" value="email@naver.com" /><br> <input
					type="date" name="birthday" value="2022-06-22" /><br>
					
					<input type="submit" value="DTO로 받기">
			</form>
		</li>
		<li>
			<form action="${path }/hello/param5.do" method="get">
				<input type="text" name="model" value="sm7" /><br> <input
					type="number" name="price" value="4000" /><br> <input
					type="text" name="color" value="blue" /><br> <input
					type="text" name="email" value="email@naver.com" /><br> <input
					type="date" name="birthday" value="2022-06-22" /><br>
					
					<input type="submit" value="DTO로 받기2">
			</form>
		</li>
		<li>
			<form action="${path }/hello/param6.do" method="get">
				<input type="text" name="model" value="sm7" /><br> <input
					type="number" name="price" value="4000" /><br> <input
					type="text" name="color" value="blue" /><br> <input
					type="text" name="email" value="email@naver.com" /><br> <input
					type="date" name="birthday" value="2022-06-22" /><br>
					
					<input type="submit" value="DTO로 받기3">
			</form>
		</li>
		
	</ul>
</body>
</html>
