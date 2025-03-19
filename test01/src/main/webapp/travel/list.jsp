<%@page import="java.util.ArrayList"%>
<%@page import="test.dto.TravelDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<TravelDto> list=new ArrayList<>();
	list.add(new TravelDto(1, "대한민국", "서울"));
	list.add(new TravelDto(10, "일본", "도쿄"));
	list.add(new TravelDto(20, "미국", "워싱턴 D.C."));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<table border="1">
			<caption>☆여행 목록☆</caption>
			<colgroup>
				<col width="200">
				<col width="200">
				<col width="200">
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>나라</th>
					<th>도시</th>
				</tr>
			</thead>
			<tbody>
				<%for(TravelDto tmp:list) { %>
					<tr>
						<td><%=tmp.getNum() %></td>
						<td><%=tmp.getCountry() %></td>
						<td><%=tmp.getCity() %></td>
					</tr>
				<%} %>
			</tbody>
	</div>
</body>
</html>