<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<script>
		let webSocket = new WebSocket("ws://localhost:9050/WebProject2/Broadcasting");
		webSocket.onmessage = function(e) {
			$("#div_message").append("<p>" + e.data + "</p");
		}
		webSocket.onopen = function(e) {
			alert("연결되었습니다.");
		}
		webSocket.onerror = function(e) {
			alert("error!");
		}
		
		$(function(){
			$('#btn_send').click(function(){
				let my_id = $('#id_chat').val();
				let msg = $("#input_message").val();
				webSocket.send(my_id + "|" + msg);
				$("#div_message").append("<p>나:" + msg + "</p");
			});
		});
	</script>
</head>
<body>
	My ID : <input type="text" id = "id_chat" placeholder = "(이름 쓰세요)"><br/>
	Message :
	<div id = "div_message">
		<input type = "text" id = "input_message"/>
		<button id = "btn_send">전송</button>
	</div>
	
</body>
</html>