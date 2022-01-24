<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Libros Tomasin</title>
	<!-- <link rel = "stylesheet" href = "./CSS/general.css" type="text/css"> -->
</head>
<body>
	
	<div>
		<h3>Hola ${ username }</h3>
		<a href="home?opcion=cerrarSesion">Cerrar Sesión</a>
	</div>
	<div class="content">
		<h1>Lista Temas</h1>
		
		<ul><c:forEach var="ele" items="${sessionScope.listaTemas}">
			<li><a href="book?opcion=buscarTema&idTema=${ ele.idTema }">${ele.descTema}</a></li>
		</c:forEach></ul>
		
		<a href="Carrito.jsp">Carrito</a>
	</div>
	
</body>
</html>