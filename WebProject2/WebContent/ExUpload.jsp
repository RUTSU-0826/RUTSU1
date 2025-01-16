<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action = "Exselrvlet" method = "post" enctype = "multipart/form-data">
		설명 : <input type = "text" name = "desc"/> <br/>
		이미지 : <input type = "file" name = "file1"/> <br/>
		이미지2 : <input type = "file" name = "file2"/> <br/>
		<input type = "submit" value = "업로드">
	</form>
	<!-- 파일 업로드 하는곳  -->
</body>
</html>
