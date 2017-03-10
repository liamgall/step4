<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<style>
.error {
	color: red;
	font-weight: bold;
}

tr:hover {
	background-color: lightgrey;
	color: white;
	cursor: pointer;
}
</style>
</head>
<body>
	<input type="hidden" id="allUser" value="${allUser}" />
	<div align="center">
		<table id="myTable">
			<tr>
				<th align="left">번호</th>
				<th align="left">이메일</th>
				<th align="left">이름</th>
				<th align="left">전화번호</th>
			</tr>

		</table>
	</div>


</body>

<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
	$(document).ready(
			function() {
				var a = '${allUser}';
				console.log(a);
				$.ajax({
					url : "/getList",
					dataType : "jsonp",
					jsonp : "callback",
					success : function(response) {
						response.data.forEach(function(value, index) {
							index++;
							var text = "<tr><td>" + index + "</td>" + "<td>"
									+ value.email + "</td>" + "<td>"
									+ value.name + "</td>" + "<td>"
									+ value.phonenumber + "</td></tr>";
							$('#myTable > tbody:last').append(text);
						})
					}
				});
			});
</script>
</html>