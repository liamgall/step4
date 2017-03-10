<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
</head>
<body>
	    
	<div align="center">
		        
		<h2>가입을 환영합니다.</h2>
		<table style="border: 1px solid black">
			<tr>
				<td align="left" width="20%">이메일주소 :</td>
				<td align="left" width="20%">${userVO.email}</td>
			</tr>
			<tr>
				<td align="left" width="20%">우편번호 :</td>
				<td align="left" width="20%">${userVO.postcode5}</td>
			</tr>
			<tr>
				<td align="left" width="20%">도로명주소 :</td>
				<td align="left" width="20%">${userVO.address}</td>
			</tr>
			<tr>
				<td align="left" width="20%">상세주소 :</td>
				<td align="left" width="20%">${userVO.details}</td>
			</tr>
			<tr>
				<td align="left" width="20%">참고항목 :</td>
				<td align="left" width="20%">${userVO.extra_info}</td>
			</tr>
			<tr>
				<td align="left" width="20%">전화번호 :</td>
				<td align="left" width="20%">${userVO.phoneNumber}</td>
			</tr>
			<tr>
				<td align="left" width="20%">첨부이미지 :</td>
				<td align="left" width="20%"><img src="/resources/attatchments/${fileName}" width="200px" height="200px"/></td>
			</tr>

		</table>
	</div>
</body>
</html>