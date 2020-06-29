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
			<td>${student.sno}</td>
		</tr>
		<tr>
			<th>姓名</th>
			<td>${student.sname}</td>
		</tr>
		<tr>
			<th>生日</th>
			<td>${student.sbday}</td>
		</tr>
		<tr>
			<th>性別</th>
			<td>${student.ssex == 1? '男':'女'}</td>
		</tr>
		<tr>
			<th>帳號</th>
			<td>${student.sacc}</td>
		</tr>
		<tr>
			<th>email</th>
			<td>${student.smail}</td>
		</tr>
		<tr>
			<th>密碼</th>
			<td>${student.spwd}</td>
		</tr>
	</table>
	<table>
		<tr>
			<input type ="button" onclick="javascript:location.href='/SpringmvcTest/home'" value="回到 首頁"/><br>
		</tr>
	</table>

</body>
</html>