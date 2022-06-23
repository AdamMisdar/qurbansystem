<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	//user has not logged in
	if(session.getAttribute("name")==null) {
		
		//redirect user to login page
		response.sendRedirect("login.jsp");
		
	}

%>
<html>
<head>
	<title>Senarai Tempahan Haiwan</title>
	<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	<link rel="stylesheet" href="animal-order-list-style.css">
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: rgb(0,87,46)">
			<div>
				<a href="#" class="navbar-brand"> Tempahan Haiwan </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link" id="refreshList">Senarai Tempahan Haiwan</a></li>
					
			</ul>	
			<div>
				<a style="color: white; width: 200px; position: fixed; top: 0; right: 0; margin-top: 10px; margin-right: 5px;
				padding: 3px; text-align: center; font-weight: 500; border: solid 1px white; border-radius: 10px; overflow: hidden">
				<%= session.getAttribute("name") %></a> 		
			</div>
		</nav>
		
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">Senarai Tempahan Haiwan</h3>
			<hr>
			<div class="container text-left">

		<%--		<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Tambah Tempahan Baru</a>  --%>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID Tempahan Haiwan</th>
						<th>Jenis Haiwan</th>
						<th>Harga Bahagian</th>
						<th>Nama Tanggungan</th>
						<th>Nama Pembekal</th>
						<th> </th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="animalOrder" items="${listAnimalOrder}">
					

						<tr>
							<td><c:out value="${animalOrder.animalOrderID}" /></td>
							<td><c:out value="${animalOrder.animalOrderType}" /></td>
							<td><c:out value="${animalOrder.animalOrderPrice}" /></td>
							<td><c:out value="${animalOrder.dependentName}" /></td>
							<td>
							<c:if test="${animalOrder.supplierName == null}">
								<c:out value="Tiada Pembekal" />
							</c:if>	
							<c:if test="${animalOrder.supplierName != null}">
								<c:out value="${animalOrder.supplierName}" />
							</c:if></td>		

							
							<td>
								<div class="linktable">
								<a href="edit?animalOrderID=<c:out value='${animalOrder.animalOrderID}'/>">Ubah</a>
								<a style="color: red" href="delete?animalOrderID=<c:out value='${animalOrder.animalOrderID}'/>">Padam</a>
									
								</div>
							</td>

							
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>
			</table>
		</div>
	</div>
	
</body>

</html>