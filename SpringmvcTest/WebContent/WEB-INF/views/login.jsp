<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Login Page</title>
	</head>
	<body>
		<form:form method="post" action="loginProcess" modelAttribute="login"
			id="loginForm">
	
			<table style="text-align: center;">
				<tr>
					<td><form:label path="sacc">Account Name:</form:label></td>
					<td><form:input path="sacc" /></td>
				</tr>
	
				<tr>
					<td><form:label path="spwd">Password:</form:label></td>
					<td><form:password path="spwd"/></td>
				</tr>
				<tr>
					<td><a href="forgetPwd">忘記密碼</a><br></td>
					<td><form:button type="submit">Sign in</form:button></td>
				</tr>
				<tr>
					<td colspan="2"><a href="/SpringmvcTest/home">Home</a></td>
				</tr>
				
		</table>
			
		</form:form>
		<table>
			<tr>
				<td style="font-style: italic; color: red;">${message}</td>
			</tr>
		</table>

</body>
</html>