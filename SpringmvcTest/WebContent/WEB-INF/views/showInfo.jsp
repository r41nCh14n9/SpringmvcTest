<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show Student</title>
</head>
<body>

<table style="text-align: center; width: 50%;" border="1">
		<tr>
			<th>學號</th>
			<th>姓名</th>
			<th>生日</th>
			<th>性別</th>
			<th>帳號</th>
			<th>email</th>
			<th>密碼</th>
			<th>已驗證</th>
		</tr>
		<c:forEach var="student" items="${students}">
			<tr>
				<td>${student.sno}</td>
				<td>${student.sname}</td>
				<td>${student.sbday}</td> 
				<td>${student.ssex == 1? '男':'女'}</td>
				<td>${student.sacc}</td>
				<td>${student.smail}</td>
				<td>${student.spwd}</td>
				<td>${student.confirm == 1? '是':'否'}</td>
			</tr>
		</c:forEach>
	</table>


</body>
</html>