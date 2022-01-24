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
	<h3>Hola ${ username }</h3>

	<a href="book?opcion=temas">Lista Temas</a>
	<a href="home?opcion=cerrarSesion">Cerrar Sesión</a>
	
	<h1>Lista Libros ${ tema.descTema }</h1>
	
	<form action="book?opcion=anadirCarrito" method="post">
		<table border="1">
			<tr>
				<th>Seleccionar</th>
				<th>Titulo</th>
				<th>Autor</th>
				<th>Precio</th>
				<th>Stock</th>
			</tr>
			<c:forEach var="ele" items="${requestScope.listaLibros}">
				<tr>
					<td><input type="checkbox" name="seleccionar" value=${ ele.isbn }></td>
					<td>${ ele.titulo }</td>
					<td>${ ele.autor }</td>
					<td>${ ele.precioUnitario }</td>
					<td>${ ele.stock }</td>
				</tr>
			</c:forEach>
		</table>
		<input type="submit" value="Añadir Libros">
	</form>
	
</body>
</html>