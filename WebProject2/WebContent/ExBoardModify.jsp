<%@page import="dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.BoardDto"%>
<% if(session.getAttribute("loginid")==null) { %>
	<script> alert("로그인하세요."); location.href="ExLogin.jsp"; </script>
<% 	} %>
<%
	int bno = Integer.parseInt(request.getParameter("bno"));
	
	BoardDao bDao = new BoardDao();
	BoardDto dto = null;
	try {
		dto = bDao.getBoardDtoByBno(bno);
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>수정하기</h1>
	<form action="ExBoardModifyAction.jsp" method="post">
		<input type="hidden" name="bno" value="<%=dto.getBno()%>"/>
		<input type="text" name="title" value="<%=dto.getTitle()%>"/> <br/>
		<textarea name="content"><%=dto.getContent() %></textarea>
		<input type="submit" value="작성완료"/>
	</form>
</body>
</html>




