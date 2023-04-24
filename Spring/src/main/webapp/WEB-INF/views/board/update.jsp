<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Board Update</title>
</head>

<body>
	<h2>Board Update</h2>
	<div style="color:blue;">${ name }님 환영합니다.</div> <!-- ${name}: name 세션 변수에 저장된 회원이름 -->
	<form name="login_form" method="post" action="/spring/member/logout">
		<input type="submit" value="로그아웃">
	</form>
	<br/>
	<a href="/spring/main">메인</a>
	<a href="/spring/board/list">게시판</a>
	<br/><br/>
	
	<form id="update_form" name="update_form" method="post">
	<input type="hidden" id="code" name="code" value="${ detail.code }">	
	<table>
		<tr>
			<td style="width:70px;">제목</td>
			<td><input type="text" name="title" value="${ detail.title }"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="content" cols="50" rows="10">${ detail.content }</textarea></td>
		</tr>
	</table>
	</form>
	
	<button type="button" onclick="update()">수정</button>
	<button type="button" onclick="undo()">취소</button>
	
	<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script>
	//취소
	function undo(){
		window.location.href="/spring/board/detail?no="+$("#code").val();
	}
	
	//수정
	function update(){
		if($("#title").val() == ""){
			alert("제목을 입력하세요");
			$("#title").focus();
			return false;
		}
	
		if($("#content").val() == ""){
			alert("내용을 입력하세요");
			$("#content").focus();
			return false;
		}
		
		$.ajax({
			url : "/spring/board/update",
			type : "POST",
			data : $("#update_form").serialize(),
			success : function(data){
				if(data.status=='fail'){
					alert("수정에 실패했습니다.");
				}else{
					alert("수정이 완료되었습니다.");
					window.location.replace("/spring/board/detail?no="+$("#code").val());
				}
			},
			error : function(){
				alert("system error"); 
			}
		})
	}
	</script>
	
</body>
</html>