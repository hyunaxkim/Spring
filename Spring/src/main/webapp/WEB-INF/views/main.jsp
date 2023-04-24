<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
	<title>Spring Main</title> 
</head>
<body>
	<h2>Main Page</h2>
	<c:choose> 
		<c:when test="${name != null}"> <!-- 세션이 설정된 경우 = 로그인 성공 -->
			<div style="color:blue;">${ name }님 환영합니다.</div> <!-- ${name}: name 세션 변수에 저장된 회원이름 -->
			<form name="login_form" method="post" action="/spring/member/logout">
				<input type="submit" value="로그아웃">
			</form>
			<br/>
			<a href="/spring/board/list">게시판</a>
		</c:when> 
		<c:when test="${status == 'logout'}"> <!-- 로그아웃한 경우 -->
			<div style="color:blue;">로그아웃 되었습니다.</div>
			<a href="/spring/member/login">로그인</a>
			<a href="/spring/main">메인</a>
		</c:when> 
		<c:otherwise> <!-- 아무것도 하지 않은 메인상태 -->
			<a href="/spring/member/login">로그인</a>
			<a href="/spring/member/join">회원가입</a>
		</c:otherwise> 
	</c:choose> 
</body>
</html>