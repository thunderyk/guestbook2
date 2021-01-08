<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="com.javaex.vo.GuestBookVo" %>

<%
	GuestBookVo guestBookVo = (GuestBookVo)request.getAttribute("guestBookVo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="./GuestController" method="post">
		<div>
			<label>비밀번호</label>
			<input type="password" name="password">
			<input type="hidden" name="no" value=<%=guestBookVo.getNo()%>>
			<input type="hidden" name="action" value="delete">
			<button type="submit">확인</button>
		</div>
	</form>
	<br>
	<a href="./GuestController?action=addList">메인으로 돌아가기</a>
</body>
</html>