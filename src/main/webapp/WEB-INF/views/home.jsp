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
			<thead>
				<tr>
					<th align="left">번호</th>
					<th align="left">이메일</th>
					<th align="left">이름</th>
					<th align="left">전화번호</th>
				</tr>
			</thead>

			<tbody>

			</tbody>



		</table>
		<table id="pages">
			<tr id="pagesTr">

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
					<tr>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<div id="dialog">
		<input type="text" id="input1" />
		<input type="text" id="input2" />
	</div>
</body>

<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
	var start;
	var end;
	var text;
	var regexPhone = /^\d{3}-\d{4}-\d{4}$/;
	$(document).ready(function() {
				start = 0;
				end = 10;
				var userCount = '${userCount}';

				text = "";
				for (var i = 1; i <= (userCount / 10) + 1; ++i) {
					$('#pagesTr').append("<td id='page'>" + i + "</td>");
				}
				var $list = $.ajax({
					url : "/getList?start=" + start + "&end=" + end,
					dataType : "jsonp",
					jsonp : "callback",
					success : function(response) {
						response.data.forEach(function(value, index) {
							index++;
							text += "<tr><td>" + index + "</td>" + "<td>"
									+ value.email + "</td>" + "<td>"
									+ value.name + "</td>" + "<td>"
									+ value.phonenumber + "</td></tr>";
						});
					}
				});

				$list.success(function() {
					$('#myTable > tbody:last').html(text);
				});
			});
	$('body').on('click', '#page', function() {
				start = ($(this)[0].innerText - 1) * 10;
				text = "";
				var $list = $.ajax({
					url : "/getList?start=" + start,
					dataType : "jsonp",
					jsonp : "callback",
					success : function(response) {
						response.data.forEach(function(value, index) {
							index++;
							index += start;
							text += "<tr><td>" + index + "</td>" + "<td>"
									+ value.email + "</td>" + "<td>"
									+ value.name + "</td>" + "<td>"
									+ value.phonenumber + "</td></tr>";
						});
					}
				});

				$list.success(function() {
					$('#myTable > tbody:last').html(text);
				});

			})
	$('body').on('click', '#changePwd', function(e) {

		var $parent = $(this)[0].parentNode.parentNode;
		var $password = $parent.childNodes[1].childNodes;
		var $email = $parent.parentNode.childNodes[0].childNodes[1].innerText;
		
		e.stopPropagation();
		if ($(this).text() === '수정') {
			$(this).text('저장');
			$password[0].removeAttribute('disabled');
			$password[0].focus();
		}
		else{
			$password[0].setAttribute('disabled', 'disabled');
			$(this).text('수정');
			$.ajax({
				url:'/changePassword',
				type:'post',
				data: {
					password : $password[0].value,
					email : $email
				},
				success: function(response){
					console.log(response);
					$password[0].value='';
				}
			})
			
		}
	});
	

	$('body').on('click', '#changePhoneNumber', function(e) {
						var $parent = $(this)[0].parentNode.parentNode;
						var $phoneNumber = $parent.childNodes[1].childNodes;
						var $email = $parent.parentNode.childNodes[0].childNodes[1].innerText;
						text = "";
						e.stopPropagation();
						if ($(this).text() === '수정') {
							$(this).text('저장');
							$phoneNumber[0].removeAttribute('disabled');
							$phoneNumber[0].focus();
						} else {
							$phoneNumber[0].setAttribute('disabled', 'disabled');
							$(this).text('수정');
							
							$.ajax({
									url : '/changePhoneNumber',
									data : {
										phoneNumber : $phoneNumber[0].value,
										email : $email
									},
									type : 'post',
									success : function(response) {
										$list;
										setTimeout(function(){
											$('#myTable > tbody:last').html(text);
										}, 1000);
									}
								});
							var $list = $.ajax({
								url : "/getList?start=" + start,
								dataType : "jsonp",
								jsonp : "callback",
								success : function(response) {
									setTimeout(function(){
										response.data.forEach(function(value, index) {
											index++;
											index += start;
											text += "<tr><td>" + index
													+ "</td>" + "<td>"
													+ value.email + "</td>"
													+ "<td>" + value.name
													+ "</td>" + "<td>"
													+ value.phonenumber
													+ "</td></tr>";
											});
										}, 500);
									}
								});
							}
							

							
					});

	$('#myTable').click(function(e) {
						var $first = $.ajax({
									url : '/selectUser',
									type : 'post',
									contentType : 'text/plain',
									data : e.target.parentNode.childNodes[1].innerText,
									success : function(response) {
										var text = "<tr><td>이메일</td><td>"
												+ response.email
												+ "</td></tr><tr><td>이름</td><td>"
												+ response.name
												+ "</td></tr><tr><td>전화번호</td><td><input type='text' id='phoneInput' value='"
												+ response.phoneNumber
												+ "' disabled/></td><td><button id='changePhoneNumber'>수정</button></td></tr><tr><td>우편번호</td><td>"
												+ response.postcode5
												+ "</td></tr><tr><td>도로명주소</td><td>"
												+ response.address
												+ "</td></tr><tr><td>상세주소</td><td>"
												+ response.details
												+ "</td></tr><tr><td>비밀번호</td><td><input type='password' id='phoneInput' placeholder='비밀번호' disabled/></td><td><button id='changePwd'>수정</button></td></tr>";
										$('#userSpec > tbody:last').html(text);

									}
								});
						text = "<tr><td>첨부 이미지 목록 </td><td>";
						var $images = $.ajax({
									url : '/getImages',
									type : 'post',
									contentType : 'text/plain',
									data : e.target.parentNode.childNodes[1].innerText,
									success : function(response) {
										response.data.forEach(function(value, index) {
												text += "<img src='/resources/attatchments/"+response.data[index].path+"' width='50px' height='50px'/>";
										});
									}
								});

						$first.success(function() {
							$images.success(function() {
								text += "</td></tr>";
								$('#userSpec > tbody:last').append(text);
								$('#myModal').css('display', 'block');
							})
						})

					});

	$('#close').click(function() {
		$('#myModal').css('display', 'none');
	});
</script>
</html>