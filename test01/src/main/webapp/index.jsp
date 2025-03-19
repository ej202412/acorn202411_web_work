<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>webapp/index.jsp</title>
</head>
<body>
	<h1>여행 인덱스 페이지</h1>
	<ul>
		<li><a href="travel/list.jsp">★여행 목록★</a></li>
	</ul>
	
	<form action="send" method="get">
		<input type="text" name="msg" placeholder="★여행 기록★"/>
		<button type="submit">확인</button>
	</form>
</body>
</html>