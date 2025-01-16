<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<script>
		$(function(){
			$('#btn1').click(function(){
				let bno = $("#input_bno").val();
				$.ajax({
					type : 'post',
					data : {"bno":bno},
					url :'ExAjaxServlet2',
					success : function(data) {
						console.log(data);
						let str = "게시글# : " + data.bno + "<br>" 
						+ "제목 : " + data.title + "<br>"
						+ "내용 : " + data.content + "<br>"
						+ "작성자 : " + data.writer + "<br>";
						$("#div1").html(str);
					},
					error : function(r,s,e) { alert("에러"); }
				});
			});
		});
	</script>
<body>
	BNO : <input type="text" id = "input_bno">
	<button id = "btn1">버튼1</button>
	<div id ="div1">
		
	</div>
</body>
</html>