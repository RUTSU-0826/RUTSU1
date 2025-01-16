<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%
    	String desc = (String)request.getAttribute("desc");
      	String filename1 = (String)request.getAttribute("filename1");
      	String filename2 = (String)request.getAttribute("filename2");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> 이미지 설명 : <%=desc %></h1>
	<img src="upload/<%=filename1 %>"/>
	<img src="upload/<%=filename2 %>"/>
</body>
</html>