<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login</title>
</head>

<body>
	<h2>Login</h2>
	
	<form name="login_form" method="post">
		id <input type="text" id="id" name="id">
		pw <input type="password" id="pw" name="pw">
		<button type="button" id="button_login" onclick="login()">로그인 </button>
		<c:if test="${status == 'error'}">
			<div style="color:red;"> 아이디 또는 비밀번호가 일치하지 않습니다.</div>
		</c:if>
	</form>
	<br/>
	<a href="/spring/main">메인</a>
	<a href="/spring/member/join">회원가입</a>
	
	<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script>
	function login(){
		if($("#id").val() == ""){
			alert("아이디를 입력하세요");
			$("#id").focus();
			return false;
		}
		
		if($("#pw").val() == ""){
			alert("비밀번호를 입력하세요");
			$("#pw").focus();
			return false;
		}
		
		//폼 내부의 데이터를 전송할 주소
		document.login_form.action= "/spring/member/login";
		document.login_form.submit(); //제출
	}
	</script>
</body>
</html>