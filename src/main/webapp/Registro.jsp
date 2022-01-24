<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Libros Tomasin</title>
	<!-- <link rel = "stylesheet" href = "./CSS/general.css" type="text/css"> -->
</head>
<body>
	<div class="content">
		<h1>Registro</h1>
		<h3>Mensaje : ${ mensaje }</h3>
		<form action="home?opcion=registrar" method="post">
			<input type="text" name="nombre" placeholder="Name: ">
			<input type="text" name="apellido" placeholder="Surname: ">
			<input type="text" name="username" placeholder="Username: ">
			<input type="text" name="password" placeholder="Password: ">
			<input type="text" name="email" placeholder="Email: ">
			<input type="text" name="direccion" placeholder="Direction: ">
			<input type="submit" value="Enviar">
		</form>
		<a href="Login.jsp">Login</a>
	</div>
</body>
</html>