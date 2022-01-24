<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Libros Tomasin</title>
</head>
<body>
	<h3>Hola ${ username }</h3>
	<a href="Carrito.jsp">Carrito</a>
	<a href="home?opcion=cerrarSesion">Cerrar Sesión</a>
	
	<h1>Ver detalle</h1>

	<h5>Titulo : ${ requestScope.libro.titulo }</h5>
	<h5>Autor : ${ requestScope.libro.autor }</h5>
	<h5>ISBN : ${ requestScope.libro.isbn }</h5>
	<h5>Precio Unitario : ${ requestScope.libro.precioUnitario }</h5>

</body>
</html>