<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<%
		int dan = Integer.parseInt(request.getParameter("dan"));
	%>
</head>
<body>
	<%
	
		for(int i=1; i<=9; i++) {
			out.println(dan + " " + "*" + i + " = " + dan*i + "<br/>");
		}
	
	%>
</body>
</html>