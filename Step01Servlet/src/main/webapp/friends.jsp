<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	//DB 에서 읽어온 친구 목록이라고 가정하자
	List<String> names=new ArrayList<>();
	names.add("김구라");
	names.add("해골");
	names.add("원숭이");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>webapp/friends.jsp</title>
</head>
<body>
	<h1>친구 목록2</h1>
	<ul>
		<li><%=names.get(0) %></li>
		<li><%=names.get(1) %></li>
		<li><%=names.get(2) %></li>
	</ul>
	<h1>친구 목록2</h1>
	<ul>
		<%for(int i=0; i<3; i++){ %>
			<li><%=names.get(i) %></li>
		<%} %>
	</ul>
	<h1>친구 목록2</h1>
	<ul>
		<%for(String tmp:names){ %>
			<li><%=tmp %></li>
		<%} %>	
	</ul>
</body>
</html>