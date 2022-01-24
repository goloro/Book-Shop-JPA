<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Libros Tomasín</title>
</head>
<body>
	<h3>Hola ${ username }</h3>
	<a href="book?opcion=temas">Lista Temas</a>
	<a href="home?opcion=cerrarSesion">Cerrar Sesión</a>
	<a href="book?opcion=vaciarCarrito">Vaciar Carrito</a>
	<a href="book?opcion=comprar">Comprar</a>
	
	<h1>Lista Libros ${ tema.descTema }</h1>
	
	<table border="1">
		<tr>
			<th>ISBN</th>
			<th>Titulo</th>
			<th>Autor</th>
			<th>Precio</th>
			<th>Detalle</th>
			<th>Eliminar</th>
		</tr>
		<c:forEach var="ele" items="${sessionScope.listaCarrito}">
			<tr>
				<td>${ ele.isbn }</td>
				<td>${ ele.titulo }</td>
				<td>${ ele.autor }</td>
				<td>${ ele.precioUnitario }</td>
				<td><a href="book?opcion=verDetalle&isbn=${ ele.isbn }">Ver detalle</a></td>
				<td><a href="book?opcion=eliminarLibro&isbn=${ ele.isbn }">Eliminar</a></td>
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>