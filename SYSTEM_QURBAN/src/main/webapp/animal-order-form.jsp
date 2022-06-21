<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Tambah Tempahan Haiwan</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	<link rel="stylesheet" href="animal-order-form-style.css">
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: rgb(0,87,46)">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> Tempahan Haiwan </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Senarai Tempahan Haiwan</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${animalOrder != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${animalOrder == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${animalOrder != null}">
            			Ubah Tempahan Haiwan
            		</c:if>
						<c:if test="${animalOrder == null}">
            			Tambah Tempahan Haiwan Baru
            		</c:if>
					</h2>
				</caption>

				<c:if test="${animalOrder != null}">
					<input type="hidden" name="animalOrderID" value="<c:out value='${animalOrder.animalOrderID}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Jenis Haiwan</label> <input type="text"
						value="<c:out value='${animalOrder.animalOrderType}' />" class="form-control"
						name="animalOrderType">
				</fieldset>

				<fieldset class="form-group">
					<label>Harga Bahagian</label> <input type="number"
						value="<c:out value='${animalOrder.animalOrderPrice}' />" class="form-control"
						name="animalOrderPrice">
				</fieldset>

				<fieldset class="form-group">
					<label>Nama Tanggungan</label> <input type="text"
						value="<c:out value='${animalOrder.dependentName}' />" class="form-control"
						name="dependentName">
				</fieldset>
				<fieldset class="form-group">
					<label>Nama Pembekal</label> <input type="text"
						value="<c:out value='${animalOrder.supplierName}' />" class="form-control"
						name="supplierName">
				</fieldset>

				<button type="submit" class="btn btn-success">Tambah</button>
				</form>
				
			</div>
		</div>
	</div>
	
</body>
</html>