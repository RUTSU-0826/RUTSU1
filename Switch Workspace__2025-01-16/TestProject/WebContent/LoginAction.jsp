<%@page import="dto.MemberProfileDto"%>
<%@page import="dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("ID");
	String pw = request.getParameter("PW");
	
	MemberDao Dao = new MemberDao();
	boolean result = false;
	try {
		result = Dao.loginCheck(id, pw);
	} catch (Exception e) {
		e.printStackTrace();
	}
	MemberProfileDto dto =  Dao.showMemberIdx(id);
	if (result) {
		session.setAttribute("loginid", id);
		if(dto.getAdmin()=='N') {
%>
			<script>alert("로그인되었습니다."); location.href = "Main.jsp"</script>
		<%  } else {	%>	
			<script>alert("로그인되었습니다."); location.href = "Admin.jsp"</script>
		<%  } %>
<% 
	} else {
%>
	<script>alert("로그인 실패"); location.href="Login.jsp"</script>
<%
	}
%>
