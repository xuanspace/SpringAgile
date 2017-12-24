<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息</title>
	<style>
		tr:first-child{
			font-weight: bold;
			background-color: #C6C9C4;
		}
	</style>
</head>

<body>
<h2>用户列表</h2>
<table>
	<tr>
		<td>NAME</td><td>Joining Date</td><td>Salary</td><td>SSN</td><td></td>
	</tr>
	<c:forEach items="${users}" var="employee">
		<tr>
			<td>${users.id}</td>
			<td>${users.name}</td>
			<td>${users.password}</td>
			<td><a href="<c:url value='/edit-${users.name}-user' />">${users.name}</a></td>
			<td><a href="<c:url value='/delete-${users.name}-user' />">users</a></td>
		</tr>
	</c:forEach>
</table>
<br/>
<a href="<c:url value='/new' />">Add New User</a>
</body>
</html>