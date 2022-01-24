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
	<a href="admin?opcion=home">Home</a>
	<a href="home?opcion=cerrarSesion">Cerrar Sesión</a>
	
	<h1>Añadir Libro</h1>
	
	<table border="1">
		<tr>
			<th>Nombre</th>
			<th>ID</th>
		</tr>
		<c:forEach var="ele" items="${sessionScope.listaTemas}">
			<tr>
				<td>${ ele.descTema }</td>
				<td>${ ele.idTema }</td>
			</tr>
		</c:forEach>
	</table>
	
	<br>
	
	<form action="admin?opcion=anadirLibro" method="post">
		<input type="text" name="isbn" placeholder="ISBN">
		<input type="text" name="titulo" placeholder="Titulo">
		<input type="text" name="autor" placeholder="Autor">
		<input type="text" name="precioUnitario" placeholder="Precio">
		<input type="text" name="stock" placeholder="Stock">
		<input type="text" name="idTema" placeholder="ID Tema">
		<input type="submit" value="Añadir">
	</form>
</body>
</html>