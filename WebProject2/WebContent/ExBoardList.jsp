<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.BoardDao"%>
<%@ page import="dto.BoardDto"%>
<%@ page import="java.util.ArrayList"%>

<% if(session.getAttribute("loginid")==null) { %>
	<script> alert("로그인하세요."); location.href="ExLogin.jsp"; </script>
<% 	} %>

<%
	int pageNum = 1;
	try { 
		pageNum = Integer.parseInt(request.getParameter("page"));
	} catch(NumberFormatException e) { }
	//생각 1) 왜 catch 영역이 빈 중괄호{} 지? 왜 비어있지?
	//생각 2) 왜 pageNum의 초기 값을 1로 정했을까? 왜 0 으로 안 했지?
	
	BoardDao bDao = new BoardDao();
	ArrayList<BoardDto> listBoard = bDao.getBoardListByPageNumber(pageNum);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시글 목록보기 현제 페이지</title>
	<script src="https://code.jquery.com/jquery-3.7.1.slim.min.js" integrity="sha256-kmHvs0B+OpCW5GVHUNjv9rOmY0IvSIRcf7zGUDTDQM8=" crossorigin="anonymous"></script>
	<script>
		$(function() {
			$("td").click(function() {
				let bno = $(this).parent().find(".bno").text();
				//alert("상세보기 bno : " + bno);
				location.href = "ExBoardDetail.jsp?bno=" + bno;
			});
			$("#btn_write").click(function() {
				location.href = "ExBoardWrite.jsp";
			});
			$("#btn_logout").click(function() {
				location.href = "logout.jsp";
			});
		});
	</script>
	<style>
		table {
			border-collapse: collapse;
		}
		th, td {
			padding: 8px;
			font-size: 19px;
			border: 1px solid grey;
		}
		.underLine {
			color:blue;
			underline:blue;
		}
	</style>
</head>
<body>
	<button id="btn_logout">로그아웃</button><br>
	<h1>게시글 목록보기 <%out.print(pageNum);%> 페이지</h1>
	<table>
		<tr>
			<th>글 번호</th>
			<th>제목</th>
			<th>작성자</th>
		</tr>
		<% for(BoardDto dto : listBoard) { %>
			<tr>
				<td class="bno"><%=dto.getBno() %></td>
				<td><%=dto.getTitle() %>
					<% if(bDao.getReplyCountByBno(dto.getBno())>0) { %>
					<span class = "Replye_cnt">[<%=bDao.getReplyCountByBno(dto.getBno()) %>]</span>
					<% } %>
				</td>
				<td><%=dto.getWriter() %></td>
			</tr>
		<% } %>
	</table>
	<div id = "div_pagination">
		<%
			int cntBoard = bDao.getCountBoard();
			int lastPageNum = cntBoard / 15 + (cntBoard%15!=0 ? 1 : 0);
			int startPageNum = (pageNum/5*5 - (pageNum%5 == 0 ? 5 : 0)) +1;
			int endPageNum = startPageNum+4;
			if(endPageNum > lastPageNum) {
				endPageNum = lastPageNum;
			}
		%>
		<% if(startPageNum > 1)  { %>
			<a href="ExBoardList.jsp?page=<%=startPageNum-1%>">&lt;&lt;</a>
		<% } %>
		<% 
			for(int i = startPageNum; i<=endPageNum; i++) { %>
			<% if(pageNum != i) {%>
				<a href="ExBoardList.jsp?page=<%=i%>" class = "underLine"><%= i%></a>
			<% } else { %>
				<span><%=i%></span>
			<% } %>			
		<% } %>
		<% if(lastPageNum >= startPageNum + 5) { %>
			<a href="ExBoardList.jsp?page=<%=endPageNum+1%>" class = "no_underLine">&gt;&gt;</a>
		<% } %>
	</div>
	<button id="btn_write">글쓰기</button>
</body>
</html>








