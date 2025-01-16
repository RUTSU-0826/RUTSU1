<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    RequestDispatcher rd = request.getRequestDispatcher("B1.jsp");
	request.setAttribute("result","true");
	rd.forward(request,response);
	//response.sendRedirect("B1.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- <script>location.href = "B1.jsp";</script> --%>
	<%-- <a href="B1.jsp">클릭</a> --%>
</body>
</html>