<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Board Detail</title>
</head>

<body>
	<h2>Board Detail</h2>
	<div style="color:blue;">${ name }님 환영합니다.</div> <!-- ${name}: name 세션 변수에 저장된 회원이름 -->
	<form name="login_form" method="post" action="/spring/member/logout">
		<input type="submit" value="로그아웃">
	</form>
	<br/>
	<a href="/spring/main">메인</a>
	<a href="/spring/board/list">게시판</a>
	<br/><br/>
	
	<table>
		<tr>
			<td style="width:70px;">제목</td>
			<td>${ detail.title }</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${ detail.writer_name }(${ detail.writer_id })</td>
		</tr>
		<tr>
			<td>작성일</td>
			<td><fmt:formatDate value="${detail.reg_date}" pattern="yyyy-MM-dd HH:mm:ss" type="date"/></td>
		</tr>
		<tr>
			<td>조회수</td>
			<td>${ detail.view }</td>
		</tr>
		<tr>
			<td>내용</td>
			<% pageContext.setAttribute("replaceChar", "\n"); %> <!-- 줄바꿈 출력 -->
			<td>${ fn:replace(detail.content, replaceChar, "<br/>") }</td>
		</tr>
	</table>
	
	<c:if test="${code == detail.writer}">
		<a href="/spring/board/update/${ detail.code }"><button type="button">수정</button></a>
		<button type="button" onclick="remove()">삭제</button>
	</c:if>
	
	<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script>
	//삭제
	function remove(){
		if(${ code } != ${ detail.writer }){
			alert("삭제할 권한이 없습니다.");
			return false;
		}
		
		if(!confirm("삭제하시겠습니까?")){
			return false;
		}
		
			
		$.ajax({
			url : "/spring/board/delete",
			type : "POST",
			data : JSON.stringify({code : ${ detail.code }}),
			contentType : "application/json", //보내는 데이터의 타입
			success : function(data){
				if(data.status=='fail'){
					alert("삭제에 실패했습니다.");
				}else{
					alert("삭제가 완료되었습니다.");
					window.location.replace("/spring/board/list");
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