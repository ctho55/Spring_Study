<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello Spring !!!  <br>
	어서오세요
</h1>


<hr>
<img src="resources/myimg/lighthouse.jpg" width="400" height="300">
<hr>

<P>  The time on the server is ${serverTime}. </P>

<a href="mlist">MemberList</a>&nbsp;&nbsp;
<a href="mlista">MList_Controller</a>&nbsp;&nbsp;
<a href="joinf">회원가입</a>&nbsp;&nbsp;



</body>
</html>
