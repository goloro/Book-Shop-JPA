package controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.beans.Tema;
import modelo.beans.Usuario;
import modelo.dao.GestionPerfilesImplMySQL8Jpa;
import modelo.dao.GestionTemasImplMySQL8Jpa;
import modelo.dao.GestionUsuariosImplMySQL8Jpa;
import modelo.dao.IntGestionPerfiles;
import modelo.dao.IntGestionTemas;
import modelo.dao.IntGestionUsuarios;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IntGestionUsuarios udao;
	private IntGestionPerfiles pdao;
	private IntGestionTemas tdao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        udao = new GestionUsuariosImplMySQL8Jpa();
        pdao = new GestionPerfilesImplMySQL8Jpa();
        tdao = new GestionTemasImplMySQL8Jpa();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcion = request.getParameter("opcion");
		
			switch (opcion) {
			case "home":
				procHome(request, response);
				break;
			case "iniciarSesion":
				procIniciar(request, response);
				break;
			case "cerrarSesion":
				procCerrar(request, response);
				break;
			case "registrar":
				procRegistrar(request, response);
				break;
			default:
				System.out.println("Opción errónea : " + opcion);
			}
	}
	
	// REDIRIGE AL JSP DE INICIO SESIÓN
	protected void procHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("Login.jsp").forward(request, response);
	}
	
	// INICIAR SESIÓN
	protected void procIniciar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usu = udao.findByUsernameAndPassword(request.getParameter("username"), request.getParameter("password"));
		
		if(usu != null) {
			Usuario usu2 = usu;
			usu2.setPassword("");
			request.getSession().setAttribute("usuario", usu);
			request.getSession().setAttribute("mensaje", "Hola " + usu.getUsername());
			request.getSession().setAttribute("username", usu.getUsername());
			// Comprueba admin
			if (usu.getPerfile().getIdPerfil() == 1) {
				request.getRequestDispatcher("admin?opcion=home").forward(request, response);
			} else {
				request.getRequestDispatcher("book?opcion=temas").forward(request, response);
			}
		} else {
			request.getSession().setAttribute("mensaje", "usuario o password incorrecto");
			procHome(request, response);
		}
	}
	
	// CERRAR SESIÓN
	protected void procCerrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("usuario");
		request.getSession().removeAttribute("mensaje");
		request.getSession().removeAttribute("username");
		request.getSession().removeAttribute("tema");
		request.getSession().removeAttribute("listCarrito");
		request.getSession().removeAttribute("listaTemas");
		request.getSession().removeAttribute("listaPedidos");
		request.getSession().removeAttribute("listaUsuarios");
		request.getSession().invalidate();
		
		request.setAttribute("mensaje", "Sesión cerrada correctamente");
		request.getRequestDispatcher("home?opcion=home").forward(request, response);
	}
	
	//REGISTRAR USUARIO
	protected void procRegistrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	request.getRequestDispatcher("Registro.jsp").forward(request, response);
		Usuario usu = new Usuario();
		usu.setUsername(request.getParameter("username"));
		usu.setPassword(request.getParameter("password"));
		usu.setEmail(request.getParameter("email"));
		usu.setNombre(request.getParameter("nombre"));
		usu.setApellido(request.getParameter("apellido"));
		usu.setDireccion(request.getParameter("direccion"));
		Date fecha = new Date();
		usu.setFechaAlta(fecha);
		usu.setPerfile(pdao.findById(2));
		
		int filas = udao.insertUsuarios(usu);
		if (filas == 1) {
			request.getSession().setAttribute("mensaje", "Usuario " + usu.getUsername() + " introducido correctamente");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		} else {
			request.getSession().setAttribute("mensaje", "Username o email ya existe");
			request.getRequestDispatcher("Registro.jsp").forward(request, response);
		}
	}
}
