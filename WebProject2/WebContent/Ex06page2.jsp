<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<%
		int a = Integer.parseInt(request.getParameter("a"));
		int b = Integer.parseInt(request.getParameter("b"));
		int sum=0;
		for(int i = a; i<=b; i++) {
			sum += i;
		}
	%>
</head>
<body>
	<%=sum %>
</body>
</html>