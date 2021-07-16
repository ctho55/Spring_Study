<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring DispatcharServlet Test_MemberList **</title>
</head>
<body>
<h2>** Spring DispatcharServlet Test_MemberList **</h2>
<br>
<c:if test="${not empty message}">
=> ${message}<br>
</c:if>
<hr>
<table border="1" width=800>
<tr height="30" bgcolor="pink">
	<th>ID</th><th>Password</th><th>Name</th><th>Level</th>
	<th>BirthDay</th><th>Point</th><th>Weight</th>
</tr>
<c:forEach var="list" items="${Banana}">
<tr height="30" align="center">
	<td>${list.id}</td><td>${list.password}</td><td>${list.name}</td><td>${list.lev}</td>
	<td>${list.birthd}</td><td>${list.point}</td><td>${list.weight}</td>
</tr>
</c:forEach>
</table>
<hr>
<a href="/Web02/index.jsp">HOME</a>
</body>
</html>