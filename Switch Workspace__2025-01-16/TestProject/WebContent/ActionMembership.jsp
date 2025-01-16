<%@page import="dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("ID");
	String pw = request.getParameter("PW");
	String name = request.getParameter("name");
	
	MemberDao Dao = new MemberDao();
	try {
		Dao.MemberADD(id, pw, name);
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<script>alert("가입되었습니다. 로그인 해주세요."); location.href = "Login.jsp";</script>