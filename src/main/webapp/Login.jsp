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
		<h1>Iniciar Sesión</h1>
		<h3>Mensaje : ${ mensaje }</h3>
		<form action="home?opcion=iniciarSesion" method="post">
			<input type="text" name="username" placeholder="Username: ">
			<input type="text" name="password" placeholder="Password: ">
			<input type="submit" value="Enviar">
		</form>
		<a href="Registro.jsp">Registro</a>
	</div>
</body>
</html>