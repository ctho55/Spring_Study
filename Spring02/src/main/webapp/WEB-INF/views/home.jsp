<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Home</title>
<link rel="stylesheet" type="text/css" href="resources/myStyle.css">
</head>
<body>
	<h1>Hello Spring MVC</h1>
	<P>The time on the server is ${serverTime}.</P>

	<hr>
	<img src="resources/myimg/city.jpg" width="300" height="200">
	<hr>

	<c:if test="${loginID != null}">
	=> ${loginName}님 안녕하세요 
</c:if>

	<c:if test="${message != null}">
	=> ${message} <br>
	</c:if>

	<c:if test="${loginID==null}">
		<a href="loginf">로그인</a>&nbsp;&nbsp;
	<a href="joinf">회원가입</a>
	</c:if>

	<c:if test="${loginID!=null}">
		<a href="mdetail">MyInfo</a>&nbsp;&nbsp;
	<a href="logout">로그아웃</a>
	</c:if>

	<a href="mlist">MList</a>&nbsp;&nbsp;
	<a href="blist">BList</a>&nbsp;&nbsp;


</body>

</html>
