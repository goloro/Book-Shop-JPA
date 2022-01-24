<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Libros Tomasin</title>
</head>
<body>
	<h3>Hola Admin ${ username }</h3>	
	<h3>Mensaje ${ mensaje }</h3>
	<a href="admin?opcion=home">Home</a>
	<a href="home?opcion=cerrarSesion">Cerrar Sesión</a>
	<a href="admin?opcion=verClientes">Ver Todos los Clientes</a>
	
	<h1>Clientes</h1>
	
	<form action="admin?opcion=verClientesUsername" method="post">
		<label>Buscar por username</label>
		<input type="text" name="username" placeholder="username">
		<input type="submit" value="Añadir">
	</form>
	
	<form action="admin?opcion=verClientesFecha" method="post">
		<label>Buscar por fecha de alta</label>
		<input type="text" name="fecha" placeholder="yyyy/MM/dd">
		<input type="submit" value="Añadir">
	</form>
	
	<table border="1">
		<tr>
			<th>Username</th>
			<th>Password</th>
			<th>Email</th>
			<th>Nombre</th>
			<th>Apellido</th>
			<th>Dirección</th>
			<th>Fecha Alta</th>
			<th>Detalle</th>
		</tr>
		<c:forEach var="ele" items="${sessionScope.listaUsuarios}">
			<tr>
				<td>${ ele.username }</td>
				<td>${ ele.password }</td>
				<td>${ ele.email }</td>
				<td>${ ele.nombre }</td>
				<td>${ ele.apellido }</td>
				<td>${ ele.direccion }</td>
				<td>${ ele.fechaAlta }</td>
				<td><a href="admin?opcion=verDetalle&username=${ ele.username }">Ver detalle</a></td>
				
			</tr>
		</c:forEach>
	</table>
	
	<br><br>
	
	<h4>Cliente: ${ requestScope.username }</h4>
	<ul>
		<li>Número Pedidos: ${ requestScope.pedidos }</li>
		<li>Libros comprados: ${ requestScope.libros }</li>
		<li>Temas: ${ requestScope.temas }</li>
		<li>Dinero Total Gastado: ${ requestScope.precio }$</li>
	</ul>
	
</body>
</html>