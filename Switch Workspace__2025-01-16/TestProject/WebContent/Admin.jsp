<%@page import="dto.MemberProfileDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% if(session.getAttribute("loginid")==null) { %>
	<script> alert("로그인하세요."); location.href="Login.jsp"; </script>
<% 	} %>
<%
	MemberDao dao = new MemberDao();
	MemberProfileDto dto = null; 
	String loginId = (String) session.getAttribute("loginid");
	dto = dao.showMemberIdx(loginId);
	if(dto.getAdmin()=='N') {
%> <script> alert("어드민 아이디로 로그인하세요."); location.href="Login.jsp"; </script>
<% } %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
	$(function(){
		$('.login').click(function(){
			location.href="Login.jsp";
		});
		$('.edit').click(function(){
			let id = $(this).parents('tr').data("id");
			location.href = "editMember.jsp?ID=" + id;
		});
		$('.del').click(function(){
			let id = $(this).parents('tr').data("id");
			let row = $(this).parents('tr');
			$.ajax({
				type: 'POST',
	            url: 'DelMemberAjax',
	            data: {
	               id : id
	            },
	            success: function(data) {
	                console.log(data);
	                alert("삭제되었습니다.");
	                row.remove();
	            },
	            error: function(r, s, e) {
	                console.log(e);
	                alert("오류");
	            }
			});
		});
		
		$('.scheduler-on').click(function() {
            $.ajax({
                type: 'POST',
                url: 'SchedulerServlet',
                data: { action: 'start' },
                success: function(response) {
                    alert(response); 
                },
                error: function(r, s, e) {
	                console.log(e);
	                alert("오류");
	            }
            });
        });

        $('.scheduler-off').click(function() {
            $.ajax({
                type: 'POST',
                url: 'SchedulerServlet',
                data: { action: 'stop' },
                success: function(response) {
                    alert(response);  
                },
                error: function(r, s, e) {
	                console.log(e);
	                alert("오류");
	            }
            });
        });
	});
</script>
<style>
	button {
		cursor:pointer;
	}
	h1 {
		display: inline-block;
		width : 193px;
	}
	.login {
		margin-left: 191px;
		display: inline-block;
		cursor:pointer;
	}
 	table {
		border-collapse: collapse;
		margin-bottom : 50px;
	}
	th, td {
		width: auto;
		padding: 8px;
		font-size: 19px;
		border: 1px solid grey;
	}
</style>
<body>
	<h1>회원관리</h1>
	<button class = "login">로그인</button>
	<br>
	<div>
		<table>
			<tr>
				<th>ID</th>
				<th>PW</th>
				<th>Name</th>
				<th>Point</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
			<%ArrayList<MemberProfileDto> list = dao.showMember(); %>
			<%for(MemberProfileDto dto2 : list) { %>
			<tr data-id=<%=dto2.getId()%>>
				<th><%=dto2.getId()%></th>
				<th><%=dto2.getPw()%></th>
				<th><%=dto2.getName()%></th>
				<th><%=dto2.getPoint()%></th>
				<th><button class = "edit">수정</button></th>
				<th><button class = "del">삭제</button></th>
			</tr>
			<% } %>
		</table>
	</div>
	<h1>스케줄러관리</h1>
	<br>
	<button class = "scheduler-on">스케줄러(20초마다 포인트1 증가) 실행 시작</button>
	<button class = "scheduler-off">스케줄러 실행 종료</button>
</body>
</html>