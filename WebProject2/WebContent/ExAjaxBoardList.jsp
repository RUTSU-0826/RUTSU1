<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.BoardDao"%>
<%@ page import="dto.BoardDto"%>
<%@ page import="java.util.ArrayList"%>

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
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<script>
		let current_page = 1;
		window.onscroll = function (){
		    console.log(document.body.scrollHeight, window.scrollY, window.innerHeight)
		    const totalPageHeight = document.body.scrollHeight;
		    const scrollPoint = window.scrollY + window.innerHeight;
		    if (scrollPoint >= totalPageHeight) {
		        //alert("요청 page = " + ++current_page);
		    	current_page++;
		    	$.ajax({
		    		type : 'post',
		    		url : 'ExAjaxBoardListServlet',
		    		data : {"page_num":current_page},
		    		success : function(obj) { 
		    			console.log(obj);
		    			for(let i = 0; i<=obj.length-1; i++) {
			    			let str =	
			    			'<tr>' +
			    			'	 <td class="bno">' + obj[i].bno + '</td>' +
			    			'    <td>' + obj[i].title + '</td>' +
			    			'    <td>' + obj[i].writer + '</td>' +
			    			'</tr>';
		    				$("#table1").append(str);
		    			}
		    		},
		    		error : function(r,s,e) { 
		    			console.log("에러:", r, s, e);
		    		}
		       	});
		    }
		}
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
	<h1>게시글 목록보기 (무한스크롤)</h1>
	<table id = "table1">
		<tr>
			<th>글 번호</th>
			<th>제목</th>
			<th>작성자</th>
		</tr>
		<% for(BoardDto dto : listBoard) { %>
			<tr>
				<td class="bno"><%=dto.getBno() %></td>
				<td><%=dto.getTitle() %>
				</td>
				<td><%=dto.getWriter() %></td>
			</tr>
		<% } %>
	</table>
</body>
</html>








