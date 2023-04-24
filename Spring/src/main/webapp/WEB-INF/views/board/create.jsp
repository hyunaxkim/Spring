<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Board Create</title>
</head>

<body>
	<h2>Board Create</h2>
	<div style="color:blue;">${ name }님 환영합니다.</div> <!-- ${name}: name 세션 변수에 저장된 회원이름 -->
	<form name="login_form" method="post" action="/spring/member/logout">
		<input type="submit" value="로그아웃">
	</form>
	<br/>
	<a href="/spring/main">메인</a>
	<a href="/spring/board/list">게시판</a>
	<br/><br/>
	
	<form id="create_form" name="create_form" method="post" action="/spring/board/create">
		<input type="hidden" name="writer" value="${ code }">
		제목<br/>
		<input type="text" name="title" size="52"><br/><br/>
		내용<br/>
		<textarea name="content" cols="50" rows="10"></textarea><br/>
		<button type="button" onclick="create()">글쓰기 </button>
	</form>
	
	<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script>
	//글쓰기
	function create(){
		if($("#writer").val() == ""){
			alert("세션이 만료되었습니다. 다시 로그인해주세요.");
			window.location.replace("/spring/main");
		}
		
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
			url : "/spring/board/create",
			type : "POST",
			data : $("#create_form").serialize(),
			success : function(data){
				if(data.status=='fail'){
					alert("글쓰기에 실패했습니다.");
				}else{
					alert("글이 등록되었습니다.");
					window.location.replace("/spring/board/list");
				}
			},
			error : function(data){
				alert("system error"); 
			}
		})
	}
	</script>
</body>
</html>