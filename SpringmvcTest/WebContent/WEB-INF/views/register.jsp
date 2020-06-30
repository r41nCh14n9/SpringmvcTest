<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Registration Page</title>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	</head>
<body>

	<form:form id="regForm" modelAttribute="student" action="registerProcess"
		method="post">
		<table style="text-align: center;">
			<tr>
				<td><form:label path="sacc">Student AccountName</form:label></td>
				<td><form:input path="sacc" name="sacc" id="sacc" onblur="checkAcc()"/>
				
				</td>
			</tr>
			<tr>
				<td><form:label path="spwd">Student Password</form:label></td>
				<td><form:password path="spwd" name="spwd" id="spwd" /></td>
			</tr>
			<tr>
				<td><form:label path="sname">Student Name</form:label></td>
				<td><form:input path="sname" name="sname" id="sname" /></td>
			</tr>
			<tr>
				<td><form:label path="sbday">Student Birthday</form:label></td>
				<td><form:input path="sbday" type="date" />
				</td>
			</tr>
			<tr>
				<td><form:label path="ssex">Student Sex</form:label></td>
				<td>
					<input  type="radio" name="ssex" value="1">男
					<input  type="radio" name="ssex" value="0">女
				</td>
			</tr>
			<tr>
				<td><form:label path="smail">Student Email</form:label></td>
				<td><form:input path="smail" name="smail" id="smail" onchange="emailCheck()"/></td>
			</tr>
			
			<tr></tr>
			<tr>
				<td><a href="/SpringmvcTest/home">Home</a></td>
				<td><form:button type="submit">Sign up</form:button></td>
			</tr>
		</table>
	</form:form>
	<table>
			<tr>
				<td><span id="msg">${message}</span></td>
			</tr>
	</table>
<script>
	function checkAcc(){
		var sacc = $("#sacc").val();
		//用ajax去資料庫匹配
		$.ajax({ 
			url:"/SpringmvcTest/student/isExist", //要處理的頁面 
			data:"sacc="+sacc, //要傳過去的數據 
			type:"post", //提交方式 
			dataType:"json", //返回的數據類型，TEXT字符串 JSON返回JSON XML返回XML；dataType中T要大寫！！ 
			success:function(data){ //回調函數，data為形參，是從login-cl.php頁面返回的值
				if (data) {
					$("#msg").html("<font color='red'>用户名已存在</font>");
				} else {
					$("#msg").html("");
				}
			} });
	}
	
	function emailCheck () {
		var emailStr=$("#smail").val();
		//alert(emailStr);
		var emailPat = /^[a-zA-Z]{1}[\w-]+@[a-z0-9]+\.[a-z]+$/;
		var reShort = /^[a-zA-Z]{1}[\w-]+@[a-z0-9]+\.[a-z]+$/;
	    var reLong = /^[a-zA-Z]{1}[\w-]+@[a-z0-9]+\.[a-z]+\.[a-z]+$/;
		var matchArray = reShort.test(emailStr) || reLong.test(emailStr);
		//alert(matchArray);
		if (!matchArray) {
			alert("電子郵件地址必須包括 ( @ 和 . )")
		return false;
		}
		return true;
		}
</script>
</body>
</html>