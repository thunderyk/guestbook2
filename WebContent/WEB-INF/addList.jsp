<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.javaex.dao.GuestBookDao" %>
<%@ page import = "com.javaex.vo.GuestBookVo" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "java.util.List" %>
<%
	List<GuestBookVo>guestBookList = (List<GuestBookVo>)request.getAttribute("guestBookList");
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="./GuestController" method="post"> 
		<table border="1">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name"></td>
				<td>비밀번호</td>
				<td><input type="password" name="password"></td>
				<td><input type="hidden" name="action" value="add"></td>
			</tr>
			<tr>
				<td colspan="4"><textarea cols="80px" rows="10px" name="content"></textarea></td>
			</tr>
			<tr>
				<td colspan="4"><button type="submit">확인</button></td>
			</tr>
		</table>
	</form>
	<br>
	<%for(int i=0;i<guestBookList.size();i++){ %>
		<table border="1">
			<tr>
				<td><%=guestBookList.get(i).getNo() %></td>
				<td><%=guestBookList.get(i).getName() %></td>
				<td><%=sdf.format(guestBookList.get(i).getDate())%></td>
				<td><a href="./GuestController?action=deleteForm&no=<%=guestBookList.get(i).getNo()%>">삭제</a></td>
			</tr>
			<tr>
				<td colspan="4"><%=guestBookList.get(i).getContent() %></td>
			</tr>
		</table>
		<br>
	<%} %>
	
	
</body>
</html>