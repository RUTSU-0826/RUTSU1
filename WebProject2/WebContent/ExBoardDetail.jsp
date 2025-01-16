<%@page import="java.util.ArrayList"%>
<%@page import="dto.ReplyDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dto.BoardDto"%>
<%@ page import="dao.BoardDao"%>
<% if(session.getAttribute("loginid")==null) { %>
	<script> alert("로그인하세요."); location.href="ExLogin.jsp"; </script>
<% 	} %>
<%
	int bno = Integer.parseInt(request.getParameter("bno"));
	BoardDao bDao = new BoardDao();
	BoardDto dto = bDao.getBoardDtoByBno(bno);
	ArrayList<ReplyDto> list = bDao.showReply(bno);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.7.1.slim.min.js" integrity="sha256-kmHvs0B+OpCW5GVHUNjv9rOmY0IvSIRcf7zGUDTDQM8=" crossorigin="anonymous"></script>
	<script>
		$(function() {
			$("#a_del").click(function() {
				let yn = confirm("정말로 삭제하나요?");
				if(yn==true) {
					location.href = "ExBoardDeleteAction.jsp?bno=<%=dto.getBno()%>";
				}
			});
			$("#a_mod").click(function() {
				location.href = "ExBoardModify.jsp?bno=<%=dto.getBno()%>";
			});
		});
	</script>
	<style>
		p { border: 1px solid grey; }
		
	</style>
</head>
<body>
	<h1>게시글 상세보기</h1>
	<p>게시글 번호 : <%=dto.getBno() %></p>
	<p>제목 : <%=dto.getTitle() %></p>
	<p>내용 : <%=dto.getContent() %></p>
	<a href="ExBoardList.jsp">목록 보기</a>
	<a id="a_del">삭제하기</a>
	<a id="a_mod">수정하기</a>
	<div>
		<form action="ExBoardReplyAction.jsp" method="post">
			<input type = "hidden" name = "bno" value="<%=dto.getBno()%>">
			댓글작성
			<textarea name = "content"></textarea>
			<input type="submit" value="작성완료"/>
		</form>
	</div>
	<div>
		<%for(ReplyDto Rdto : list) { %>
		<div>
			<%=Rdto.getContent()%> -
			<%=Rdto.getWriter() %> -
			<%=Rdto.getWriteDate() %> 
			<button id = "Rp-edit">수정</button>
			<button id = "Rp-del">삭제</button>
		</div>
		<% } %>
	</div>
</body>
</html>






