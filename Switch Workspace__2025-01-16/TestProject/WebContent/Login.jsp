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
		$('#membership').click(function(){
			location.href = "Membership.jsp";
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
	h1 {
	 margin : 0px;
	}
	.button {
		width : 207px;
		cursor:pointer;
	}
	div { 
		width: 255px; 
        padding: 20px;
        border: 1px solid #ccc;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        text-align: center;
	}
	#id {
		margin-left : 13px;
		margin-bottom : 3px;
	}
	#pw {
		margin-left : 3px;
		margin-bottom : 3px;
	}
</style>
<body>
	<div>
		<h1>로그인</h1>
		<form action = "LoginAction.jsp" method = "post">
			ID : <input type = "text" id ="id" name = "ID"><br>
			PW : <input type = "password" id = "pw" name = "PW"><br>
			<input type = "submit" class = "button" value = "로그인"><br>
			<input type = "button" class = "button" id = "membership" value = "회원가입">
		</form>
	</div>
</body>
</html>