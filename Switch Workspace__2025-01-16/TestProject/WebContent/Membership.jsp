<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
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
	}
	.button {
		width : 240px;
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
	.ID {
		margin-left : 31px;
		margin-bottom : 3px;
	}
	.PW {
		margin-left : 21px;
		margin-bottom : 3px;
	}
	.Name {
		margin-left : 5px;
		margin-bottom : 3px;
	}
</style>
<body>
	<div>
		<h1>회원가입</h1>
		<form action = "ActionMembership.jsp" method = "post">
			ID : <input type = "text" class = "ID" name = "ID"><br>
			PW : <input type = "password" class = "PW" name = "PW"><br>
			Name : <input type = "text" class = "Name" name = "name"><br>
			<input type = "submit" class = "button" value = "작성완료"><br>
		</form>
	</div>
</body>
</html>