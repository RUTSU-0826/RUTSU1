<%@page import="dto.MemberProfileDto"%>
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
	int point = dto.getPoint();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
	$(function(){
		$('.logout').click(function(){
			alert("로그아웃 되었습니다");
			location.href = "Login.jsp";
		});
		let Mypoint = <%=point%>;
		$('.content').click(function(){
			let point = $(this).data("code");
			let title = $(this).data("name");
			if(point > Mypoint) {
				alert("포인트가 부족합니다. 광고를 클릭하세요.");
			} else {
				let pointvalue = Mypoint - point;
				$.ajax({
					 type: 'POST',
			            url: 'PointAjax',
			            data: {
			               point : pointvalue,
			               id : "<%=loginId%>"
			            },
			            success: function(data) {
			                console.log(data);
			                alert("컨텐츠("+title+")를 구입하였습니다.")
			                $('.point').text("포인트 : "+pointvalue+"점");
			                 let newPoint = data.newPoint; 
			                 Mypoint = newPoint;  
			            },
			            error: function(r, s, e) {
			                console.log(e);
			                alert("오류");
			            }
				});
			}
		});
		$('.img4').click(function(){
			$.ajax({
				 type: 'POST',
		            url: 'PointaddAjax',
		            data: {
		               point : Mypoint,
		               id : "<%=loginId%>"
		            },
		            success: function(data) {
		                console.log(data);
		                alert(data.addPoint);
		                 let newPoint = data.newPoint; 
		                 Mypoint = newPoint;  
		                $('.point').text("포인트 : "+newPoint+"점");
		                location.href = "https://koreaisacademy.com";
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
body {
	width : 1912px;
	height : 100%;
}
 .title {
 	margin : 0px;
 	    float: left;
    	width: auto;
 }
 .profile {
 	margin-right : 30px;
 	width: auto;
 	float:right;
 	font-weight : 700;
 }
 .header {
 	
 	margin-top : 30px;
 	width : 100%;
 	height : 100px;
 }
 .name {
 	float:left;
 }
 ul, li {
 	list-style-type: none; 
 	margin : 0px;
 	padding : 0px;
 }
 ul {
 	width: 1544px;
    height: 500px;
 	margin-left : 350px;
 }
 li {
 	cursor:pointer;
 	width : auto;
 	float:left;
 	margin-right : 3px;
 }
 li div {
 	width : auto;
 	height : auot;
 }
 .img1 {
 	display : block;
 	width : 350px;
 	height : 408px;
 	 background: url(images/Cpp_350_408.png);
 }
 .img2 {
 	display : block;
 	width : 350px;
 	height : 408px;
 	background: url(images/Intro_350_408.png);
 }
 .img3 {
 	display : block;
 	width : 350px;
 	height : 408px;
 	background: url(images/Java_350_408.png);
 }
 .cotent1-value span {
 	margin-left : 91px;
 }
 .cotent2-value span {
 	margin-left : 91px;
 }
 .cotent3-value span {
 	margin-left : 91px;
 }
 .main {
 	width : auto;
 	height: 663px;
 }
 .bottom {
 }
 .bottom-value {
 	float:right;
 	margin-right: 30px;
 }
 .img4 {
 	cursor:pointer;
 	float:right;
 	display : block;
 	width : 238px;
 	height : 141px;
 	background: url(images/korea_it.png) no-repeat;
 }
 h3 {
 	width : 300px;
 	margin-left : 350px;
 }
 .point {
 	float:left;
 	margin-right: 38px;
  }

button {
	cursor: pointer;
}
 
 
</style>
<body>
	<div class = "header">
		<h1 class = "title">메인페이지</h1>
		<div class = "profile">
			<div class = "name"><%=dto.getName() %>님 안녕하세요. &nbsp;</div>
			<button class = "logout">로그아웃</button><br>
			<div class = "point">포인트 : <%=dto.getPoint() %>점 &nbsp;</div>
		</div>
	</div>
	<div class = "main">
		<h3>구입할 컨텐츠를 선택하세요</h3>
		<ul class = "content-value">
		 	<li class = "content" data-name = "C++" data-code="100000">
		 		<div class = "cotent1-value">
		 			<i class = "img1"></i>
		 			<span>100.000포인트</span>
		 		</div>
		 	</li>
		 	<li class = "content" data-name = "Intro" data-code="500000">
		 		<div class = "cotent2-value">
		 			<i class = "img2"></i>
		 			<span>500.000포인트</span>
		 		</div>
		 	</li>
		 	<li class = "content" data-name = "Java" data-code="300000">
		 		<div class = "cotent3-value">
		 			<i class = "img3"></i>
		 			<span>300.000포인트</span>
		 		</div>
		 	</li>
		</ul>
	</div>
	<div class="bottom">
		<div class = "bottom-value">
			<span>&lt;광고&gt;</span>
			<div class = "img4"></div>
		</div>
	</div>
</body>
</html>