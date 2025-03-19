<%@page import="test.food.dao.FoodDao"%>
<%@page import="test.food.dto.FoodDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//수정할 음식번호를 읽어온다
	int num=Integer.parseInt(request.getParameter("num"));
	//수정할 음식의 정보를 DB 에서 읽어온다
	FoodDto dto=new FoodDao().getData(num); 
	//응답하기

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/food/updateform.jsp</title>
<jsp:include page="/include/resource.jsp"></jsp:include>
</head>
<body>
	<div class="container">
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item">
					<a href="${pageContext.request.contextPath }/">Home</a>
				</li>
				<li class="breadcrumb-item">
					<a href="${pageContext.request.contextPath }/food/list.jsp">음식 목록</a>
				</li>		
				<li class="breadcrumb-item active">음식 수정</li>				
			</ol>
		</nav>
		<h1>음식 수정 양식</h1>
		
		<form action="update.jsp" method="post">
			<!-- 화면에 보이지는 않지만 폼 제출할 때 같이 전송되는 값 -->
			<input type="hidden" name="num" value="<%=dto.getNum() %>"/>
			<div class="mb-3">	
				<label class="form-label" for="type">유형</label>
				<select name="type" id="type">
					<option value="">선택</option>
					<option <%=dto.getType().equals("한식") ? "selected":"" %>>한식</option>
					<option <%=dto.getType().equals("중식") ? "selected":"" %>>중식</option>
					<option <%=dto.getType().equals("양식") ? "selected":"" %>>양식</option>
					<option <%=dto.getType().equals("일식") ? "selected":"" %>>일식</option>
					<option <%=dto.getType().equals("기타") ? "selected":"" %>>기타</option>
				</select>
			</div>
			<div class="mb-3">
				<label class="form-label" for="name">음식 이름</label>
				<input class="form-control" type="text" name="name" id="name" value="<%=dto.getName() %>"/>				
			</div>	
			<div class="mb-3">
				<label class="form-label" for="price">가격</label>
				<input class="form-control" type="text" name="price" id="price" max="1000" value="<%=dto.getPrice() %>"/>				
			</div>	
			<button class="btn btn-success" type="submit">수정 확인</button>
			<button class="btn btn-danger" type="reset">취소</button>
		</form>
	</div>
</body>
</html>