<%@page import="dto.MemberProfileDto"%>
<%@page import="dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String id = request.getParameter("ID"); 
	MemberDao dao = new MemberDao();
	MemberProfileDto dto = dao.showMemberIdx(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
    $(document).ready(function() {
        $("#editMemberForm").submit(function(event) {
            var pw = $("input[name='pw']").val();
            var name = $("input[name='name']").val();
            var point = $("input[name='point']").val();

            if (pw === "" || name === "" || point === "") {
                event.preventDefault();
                alert("모든 항목에 값을 넣어주세요.");
            }
        });
    });
</script>
<style>
	body, html {
            height: 100%;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: Arial, sans-serif;
    }
	#id {
		margin-left : 36px;
		margin-top : 3px;
	}
	#pw {
		margin-left : 27px;
		margin-top : 3px;
	}
	#name {
		margin-left : 10px;
		margin-top : 3px;
	}
	#point {
		margin-left : 17px;
		margin-top : 3px;
	}
	#submit {
		margin-top : 5px;
		width : 237px;
	}
	div { 
		width: 310px; 
        padding: 20px;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        text-align: center;
	}
</style>
<body>
	<div>
		<h1>회원관리-수정관리자</h1>
		<form action = "editMemberAction.jsp" method="post" id="editMemberForm">
			ID     <input type="text" id = "id" name = "id" value=<%=id %> readonly><br>
			PW     <input type="password" id = "pw" name = "pw" value = <%=dto.getPw() %>><br>
			Name   <input type="text" id = "name" name = "name" value=<%=dto.getName() %>><br>
			Point  <input type="text" id = "point" name = "point" value=<%=dto.getPoint() %>><br>
			<input type = "submit" id = "submit" value="제출">
		</form>
	</div>
</body>
</html>