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
	<a href="admin?opcion=verPedidos">Ver Todos los Pedidos</a>
	
	<h1>Ver Pedidos</h1>
	
	<form action="admin?opcion=verPedidosFecha" method="post">
		<label>Buscar por fecha</label>
		<input type="text" name="fecha" placeholder="yyyy/MM/dd">
		<input type="submit" value="Añadir">
	</form>
	
	<table border="1">
		<tr>
			<th>Id Pedido</th>
			<th>Dirección</th>
			<th>Estado</th>
			<th>Fecha</th>
			<th>Nombre Usuario</th>
		</tr>
		<c:forEach var="ele" items="${sessionScope.listaPedidos}">
			<tr>
				<td>${ ele.idPedido }</td>
				<td>${ ele.direccionEntrega }</td>
				<td>${ ele.estado }</td>
				<td>${ ele.fechaAlta }</td>
				<td>${ ele.usuario.username }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>