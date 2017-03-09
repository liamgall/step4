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
</style>
</head>
<body>
	<div align="center">
		<table border="0" width="80%">
			<form:form id="form" action="/InfoValidation" commandName="userVO"
				enctype="multipart/form-data" method="post">
				<tr>
					<td align="left" width="20%">Email:</td>
					<td align="left" width="20%"><form:input path="email"
							value="${eMail}" size="30" style="background-color:#EBEBE4;" /></td>
					<td align="left"><form:errors path="email" cssClass="error" /></td>
				</tr>
				<tr>
					<td>우편 번호 :</td>
					<td><form:input type="text" id="postcode5" path="postcode5"
							name="" class="postcodify_postcode5" value="" style="width:50px"
							readonly="true" />
						<button id="postcodify_search_button" type="button">검색</button></td>
					<td><form:errors path="postcode5" cssClass="error" /></td>
				</tr>
				<tr>
					<td>도로명주소 :</td>
					<td><form:input type="text" id="address" path="address"
							name="" class="postcodify_address" value="" style="width:300px"
							readonly="true" /></td>
					<td><form:errors path="address" cssClass="error" /></td>
				</tr>
				<tr>
					<td><label for="details">상세주소 :</label></td>
					<td><form:input type="text" id="details" path="details"
							name="" class="postcodify_details" value="" style="width:300px" /></td>
					<td><form:errors path="details" cssClass="error" /></td>
				</tr>
				<tr>
					<td>참고항목 :</td>
					<td><form:input type="text" id="extra_info" path="extra_info"
							name="" class="postcodify_extra_info" value=""
							style="width:300px" readonly="true" /></td>
					<td><form:errors path="extra_info" cssClass="error" /></td>
				</tr>
				<tr>
					<td><label for="phoneNumber">전화번호 :</label></td>
					<td><form:input path="phoneNumber" id="phoneNumber" /></td>
					<td><form:errors path="phoneNumber" cssClass="error" /></td>
				</tr>
				<tr>
					<td><label for="password">비밀번호 :</label></td>
					<td><form:password path="password" id="password" /></td>
					<td><form:errors path="password" cssClass="error" /></td>
				</tr>
				<tr>
					<td><label for="password2">비밀번호 확인 :</label></td>
					<td><input type="password" id="password2" /></td>
				</tr>
				<tr>
					<td>이미지 첨부 :</td>
					<td><form:input type="file" path="uploadFile" /></td>
				</tr>
				<tr>
					<td align="center"><input type="submit" id="submit" value="제출" /></td>
				</tr>
			</form:form>

			<tr>

			</tr>
		</table>
	</div>
</body>

<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>

<script>
	var regexEMail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/; // 이메일 검사식
	var regexPhoneNumber = /^\d{3}-\d{3,4}-\d{4}$/; // 전화번호 검사식
	var regexPassword = /(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()])[A-Za-z0-9!@#$%^&*()]{8,16}/;
	var $phoneNumber = $('#phoneNumber');
	var $password = $('#password');
	var $password2 = $('#password2');

	$('#password, #password2, #phoneNumber').after('<strong></strong>');

	$(function() {
		$("#postcodify_search_button").postcodifyPopUp();
	});

	/* --------------------- */

	$('#click').click(function() {
	});

	/* ---------------------- */

	$password.keyup(function() {
		var textTip = $(this).next('strong');
		if ($password.val() > 7) {
			if (!regexPassword.test($password.val()))
				textTip.text('숫자, 특수문자 1개 이상 포함. 8자 이상');
			else
				textTip.text('');
		} else
			textTip.text('');
	});

	/* [!@#$%^*+=-] 이부분 [^a-zA-Z0-9] 로 대체가능 */
	$password2.keyup(function() {
		var textTip = $(this).next('strong');
		if ($password.val().length === $password2.val().length) {
			console.log("same");
			if ($password.val() !== $password2.val())
				textTip.text('비밀번호가 틀립니다.');
			else
				textTip.text('');
		} else
			textTip.text('');
	});
	$phoneNumber.keyup(function() {
		var textTip = $(this).next('strong');
		if ($phoneNumber.val().length > 13)
			textTip.text('너무 깁니다.');
		else if (!regexPhoneNumber.test($phoneNumber.val()))
			textTip.text('하이픈(-) 포함 입력.');
		else
			textTip.text('');
	});

	function checkValidate() {
		return true;
		/* 비밀번호 같은지 검사 */
		if ($('#password').val() !== $('#password2').val()) {
			alert('비밀번호가 틀립니다.');
			return false;
		}

		/* 주소 검사 */
		else if ($('#postcode5').val() == "") {
			alert('주소를 입력하세요.');
			return false;
		} else if ($('#address').val() == "") {
			alert('주소를 입력하세요.');
			return false;
		} else if ($('#details').val() == "") {
			alert('주소를 입력하세요.');
			return false;
		} else if ($('#extra_info').val() == "") {
			alert('주소를 입력하세요.');
			return false;
		}

		/* 전화번호 검사 */
		else if (!regexPhoneNumber.test($phoneNumber.val())) { // 이메일 검사
			alert('[전화번호 입력 오류] 유효한 전화번호를 입력해 주세요. ex) 010-1234-5678');
			return false;
		}
		/* 비밀번호 정규표현식 검사 */
		else if (!regexPassword.test($password.val())) {
			alert('비밀번호 입력 오류. 숫자, 특수문자를 1개 이상 포함해야 합니다. (8자 이상)');
			return false;
		}
		/* 정상 */
		else {
			return true;
		}
	}
	$('#form').submit(function() {
		if (!checkValidate())
			return false;
	});
</script>
</html>