<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HOME</title>

<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

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
		<table>
			<tr>
				<td align="left">
					<form action="/register" method="post">
						<button id="register" value="회원가입">회원가입</button>
					</form>
				</td>
			</tr>
		</table>
	</div>
	<!-- The Modal -->
	<div id="myModal" class="modal">

		<!-- Modal content -->
		<div class="modal-content">
			<div class="modal-header">
				<span id="close" class="close">&times;</span>
				<h2>사용자 정보</h2>
			</div>
			<div class="modal-body">
				<table id="userSpec">
					<tr>정보
					</tr>
					<tr></tr>
					<tr></tr>
				</table>
			</div>
		</div>
	</div>


</body>

<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
	$(document).ready(
			function() {
				var userCount = '${userCount}';
				console.log(userCount);
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

	$('#myTable')
			.click(
					function(e) {
						console.log($(this));
						$.ajax({
									url : '/selectUser',
									type : 'post',
									contentType : 'text/plain',
									data : e.target.parentNode.childNodes[1].innerText,
									success : function(response) {
										console.log(response);
										console.log(response.email);
										var text = "<tr><td>이메일</td><td>"
												+ response.email
												+ "</td></tr><tr><td>이름</td><td>"
												+ response.name
												+ "</td></tr><tr><td>전화번호</td><td>"
												+ response.phoneNumber
												+ "</td><td><button id='changePhoneNumber'>수정</button></td></tr><tr><td>우편번호</td><td>"
												+ response.postcode5
												+ "</td></tr><tr><td>도로명주소</td><td>"
												+ response.address
												+ "</td></tr><tr><td>상세주소</td><td>"
												+ response.details
												+ "</td></tr><tr><td>비밀번호</td><td><button id='changePwd'>수정</button></td></tr>"
										$('#userSpec > tbody:last').html(text);
										setTimeout(function() {
											$('#myModal').css('display',
													'block');
										}, 2000);
									}
								});
					});

	$('#close').click(function() {
		$('#myModal').css('display', 'none');
	});
</script>
</html>