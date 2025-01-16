<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.BoardDao"%>
<%
	request.setCharacterEncoding("utf-8");
	int bno = Integer.parseInt(request.getParameter("bno"));
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	BoardDao bDao = new BoardDao();
	try {
		bDao.modifyBoard(bno, title, content);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
%>
<script>location.href = "ExBoardDetail.jsp?bno=<%=bno%>";</script>