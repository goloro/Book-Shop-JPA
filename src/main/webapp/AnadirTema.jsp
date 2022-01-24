<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	
	<h1>Añadir Tema</h1>
	
	<form  action="admin?opcion=anadirTema" method="post">
		<input type="text" name="nombreTema" placeholder="Nombre Tema">
		<input type="text" name="abreviaturaTema" placeholder="Abreviatura Tema">
		<input type="submit" value="Añadir">
	</form>
	
</body>
</html>