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
			$("#Btn1").click(function(){
				$.ajax({
					type: 'post',
					url: 'ExAjaxServlet',
					success: function(data) { 
						//alert("성공");
						console.log(data);
						alert(data.check);
						alert(data.check2);
						},
					error : function(r,s,e) { alert("에러!"); }
				});
			});
		});
	</script>
<body>
	<button id = "Btn1">버튼1</button>
</body>
</html>