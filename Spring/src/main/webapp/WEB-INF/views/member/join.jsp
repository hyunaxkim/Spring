<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Join</title>
</head>

<body>
	<h2>Join</h2>
	
	<form id="join_form" name="join_form" method="post">
		<table>
			<tr>
				<td>id</td>
				<td><input type="text" id="id" name="id"></td>
				<td>
					<input type="hidden" id="checked_id" name="checked_id" value="">
					<button type="button" id="button_idcheck" onclick="idcheck()">중복확인 </button>
					<span id="message"></span>
				</td>
			</tr>
			<tr>
				<td>pw</td>
				<td colspan="2"><input type="password" id="pw" name="pw"></td>
			</tr>
			<tr>
				<td>name</td>
				<td colspan="2"><input type="text" id="name" name="name"></td>
			</tr>
		</table><br/>
		<button type="button" id="button_join" onclick="join()">회원가입 </button>
	</form>
	<br/>
	<a href="/spring/main">메인</a>
	<a href="/spring/member/login">로그인</a>
	
	<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script>
	//아이디 중복체크
	function idcheck(){
		if($("#id").val() == ""){
			alert("아이디를 입력하세요");
			$("#id").focus();
			return false;
		}
		
		$("#message").text("");
		
		$.ajax({
			url : "/spring/member/join/idcheck",
			type : "POST",
			data : {id : $("#id").val()},
			success : function(data){
				if(data.status=='fail'){
					$("#message").text("중복된 아이디입니다.");
					$("#message").css("color", "red");
					$("#checked_id").attr("value", "");
				}else{
					$("#message").text("사용 가능한 아이디입니다.");
					$("#message").css("color", "blue");
					$("#checked_id").attr("value", data.id);
				}
			},
			error : function(){
				alert("system error");
			}
		})
	}
	
	//회원가입
	function join(){
		if($("#id").val() == ""){
			alert("아이디를 입력하세요");
			$("#id").focus();
			return false;
		}
		
		if($("#checked_id").val() == "" || $("#checked_id").val() != $("#id").val()){
			alert("중복확인을 해주세요");
			return false;
		}
	
		if($("#pw").val() == ""){
			alert("비밀번호를 입력하세요");
			$("#pw").focus();
			return false;
		}
		
		if($("#name").val() == ""){
			alert("이름을 입력하세요");
			$("#name").focus();
			return false;
		}
		
		$.ajax({
			url : "/spring/member/join",
			type : "POST",
			data : $("#join_form").serialize(),
			success : function(data){
				if(data.status=='fail'){
					alert("회원가입에 실패했습니다.");
				}else{
					alert("회원가입이 완료되었습니다.");
					window.location.replace("/spring/member/login");
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