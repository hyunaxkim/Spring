<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Board List</title>
</head>

<body>
	<%@ include file="../include.jsp" %>
	<h2>Board List</h2>
	<div style="color:blue;">${ name }님 환영합니다.</div> <!-- ${name}: name 세션 변수에 저장된 회원이름 -->
	<form name="login_form" method="post" action="/spring/member/logout">
		<input type="submit" value="로그아웃">
	</form>
	<br/>
	<a href="/spring/main">메인</a>
	<a href="/spring/board/list">게시판</a>
	<a href="/spring/board/create">글쓰기</a>
	<br/><br/>
	
	<table style="text-align:center; border-collapse:collapse;" border="1">
		<tr>
			<th style="width:60px;">번호</th>
			<th style="width:200px;">제목</th>
			<th style="width:100px;">작성자</th>
			<th style="width:100px;">조회수</th>
			<th style="width:200px;">등록일</th>
		</tr>
		<c:forEach items="${list}" var="list">
			<tr>
				<td>${list.code}</td>
				<td><a href="/spring/board/detail?no=${list.code}">${list.title}</a></td>
				<td>${list.writer_name}</td>
				<td>${list.view}</td>
				<td><fmt:formatDate value="${list.reg_date}" pattern="yyyy-MM-dd" type="date"/></td>
				
			</tr>
		</c:forEach>
	</table>
	
	<!-- 페이징 -->
	<div>
		<c:if test="${paging.prev}">
			<a href="/spring/board/list?page=${paging.startPageNum - 1}&searchType=${searchType}&keyword=${keyword}">&lt;</a>
		</c:if>
		
		<c:forEach begin="${paging.startPageNum}" end="${paging.limitPageNum}" var="page">
			<c:choose>
				<c:when test="${currentPageNum == page}">
					<b>${page}</b>
				</c:when>
				<c:otherwise>
					<a href="/spring/board/list?page=${page}&searchType=${searchType}&keyword=${keyword}">${page}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		
		<c:if test="${paging.next}">
			<a href="/spring/board/list?page=${paging.limitPageNum + 1}&searchType=${searchType}&keyword=${keyword}">&gt;</a>
		</c:if>
	</div>
	
	<!-- 검색 -->
	<div>
		<select name="searchType" id="searchType">
			<option value="title" <c:if test="${searchType eq 'title'}">selected</c:if>>제목</option>
			<option value="content" <c:if test="${searchType eq 'content'}">selected</c:if>>내용</option>
			<option value="all" <c:if test="${searchType eq 'all'}">selected</c:if>>제목+내용</option>
			<option value="writer_name" <c:if test="${searchType eq 'writer_name'}">selected</c:if>>작성자</option>
		</select>
		<input type="text" name="keyword" id="keyword" value="${keyword}">
		<button type="button" onclick="search()">검색</button>
	</div>
	
	
	<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script>
	//검색
	function search(){
		if($("#keyword").val() == ""){
			alert("키워드를 입력해주세요.");
			$("#keyword").focus();
			return false;
		}
		
		window.location.replace("/spring/board/list?searchType="+$("#searchType option:selected").val()+"&keyword="+$("#keyword").val());
		
	}
	</script>
	
</body>
</html>