<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forget Password</title>
</head>
<body>
	<form:form method="post" action="resetPwdProcess" modelAttribute="student"
		id="restForm">

		<table style="text-align: center;">
			<tr>
				<td><form:label path="sacc">Account Name:</form:label></td>
				<td><form:input path="sacc" /></td>
			</tr>

			<tr>
				<td><form:label path="smail">Student Email:</form:label></td>
				<td><form:input path="smail" /></td>
			</tr>
			<tr>
				<td><a href="/SpringmvcTest/home">返回</a></td>
				<td><form:button type="submit">重設密碼</form:button></td>
			</tr>
			<tr>
				<td colspan="2" id="msg">${msg}</td>
			</tr>
		</table>

	</form:form>
</body>
</html>