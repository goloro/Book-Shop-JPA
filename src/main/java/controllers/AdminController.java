package controllers;

import java.io.IOException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.beans.Libro;
import modelo.beans.LineaPedido;
import modelo.beans.Pedido;
import modelo.beans.Tema;
import modelo.beans.Usuario;
import modelo.dao.GestionLibrosImplMySQL8Jpa;
import modelo.dao.GestionLineaPedidoImplMySQL8Jpa;
import modelo.dao.GestionPedidosImplMySQL8Jpa;
import modelo.dao.GestionTemasImplMySQL8Jpa;
import modelo.dao.GestionUsuariosImplMySQL8Jpa;
import modelo.dao.IntGestionLibros;
import modelo.dao.IntGestionLineaPedidos;
import modelo.dao.IntGestionPedidos;
import modelo.dao.IntGestionTemas;
import modelo.dao.IntGestionUsuarios;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/admin")
public class AdminController extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	private IntGestionTemas tdao;
	private IntGestionLibros ldao;
	private IntGestionPedidos pdao;
	private IntGestionUsuarios udao;
	private IntGestionLineaPedidos lpdao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
        tdao = new GestionTemasImplMySQL8Jpa();
        ldao = new GestionLibrosImplMySQL8Jpa();
        pdao = new GestionPedidosImplMySQL8Jpa();
        udao = new GestionUsuariosImplMySQL8Jpa();
        lpdao = new GestionLineaPedidoImplMySQL8Jpa();
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
		case "anadirTema":
			procAnadirTema(request, response);
			break;
		case "anadirLibro":
			procAnadirLibro(request, response);
			break;
		case "verPedidos":
			procVerPedidos(request, response);
			break;
		case "verPedidosFecha":
			procVerPedidosFecha(request, response);
			break;
		case "verClientes":
			procVerClientes(request, response);
			break;
		case "verClientesUsername":
			procVerClientesUsername(request, response);
			break;
		case "verClientesFecha":
			procVerClientesFecha(request, response);
			break;
		case "verDetalle":
			procVerDetalle(request, response);
			break;
		default:
			System.out.println("Opción errónea : " + opcion);
		}
	}
	
	//HOME
	protected void procHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Tema> lista = tdao.findAll();
		request.getSession().setAttribute("listaTemas", lista);
		
		List<Pedido> listaPedidos = pdao.findAll();
		request.getSession().setAttribute("listaPedidos", listaPedidos);
		
		List<Usuario> listaUsuarios = udao.findAll();
		request.getSession().setAttribute("listaUsuarios", listaUsuarios);
		
		request.getRequestDispatcher("Admin.jsp").forward(request, response);
	}
	
	// AÑADIR TEMA
	protected void procAnadirTema(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Tema tema = new Tema();
		
		tema.setDescTema(request.getParameter("nombreTema"));
		tema.setAbreviatura(request.getParameter("abreviaturaTema"));
		
		int filas = tdao.insertTema(tema);
		
		procHome(request, response);
	}
	
	// AÑADIR LIBRO
	protected void procAnadirLibro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Libro libro = new Libro();
		
		libro.setIsbn(Long.parseLong(request.getParameter("isbn")));
		libro.setTitulo(request.getParameter("titulo"));
		libro.setAutor(request.getParameter("autor"));
		libro.setPrecioUnitario(BigDecimal.valueOf(Double.parseDouble(request.getParameter("precioUnitario"))));
		libro.setStock(Integer.parseInt(request.getParameter("stock")));
		libro.setTema(tdao.findById(Integer.parseInt(request.getParameter("idTema"))));
		
		int filas = ldao.insertLibro(libro);
		
		procHome(request, response);
	}
	
	// VER PEDIDOS
	protected void procVerPedidos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		List<Pedido> listaPedidos = pdao.findAll();
		request.getSession().setAttribute("listaPedidos", listaPedidos);
		
		request.getRequestDispatcher("Verpedidos.jsp").forward(request, response);
	}
	
	// VER PEDIDOS POR FECHA
	protected void procVerPedidosFecha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fecha = request.getParameter("fecha");
		List<Pedido> listaPedidos = pdao.findByFecha(fecha);
		request.getSession().setAttribute("listaPedidos", listaPedidos);
		
		request.getRequestDispatcher("Verpedidos.jsp").forward(request, response);
	}
	
	// VER DATOS CLIENTES
	protected void procVerClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Usuario> listaUsuarios = udao.findAll();
		request.getSession().setAttribute("listaUsuarios", listaUsuarios);
		
		request.getRequestDispatcher("Vercliente.jsp").forward(request, response);
	}
	
	// VER DATOS CLIENTES USERNAME
	protected void procVerClientesUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		List<Usuario> listaUsuarios = new ArrayList <Usuario>();
		listaUsuarios.add( (Usuario) udao.findByUsername(username));

		request.getSession().setAttribute("listaUsuarios", listaUsuarios);
		
		request.getRequestDispatcher("Vercliente.jsp").forward(request, response);
	}
	
	// VER DATOS CLIENTES FECHA
	protected void procVerClientesFecha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fecha = request.getParameter("fecha");
		List<Usuario> listaUsuarios = udao.findByFecha(fecha);

		request.getSession().setAttribute("listaUsuarios", listaUsuarios);
		
		request.getRequestDispatcher("Vercliente.jsp").forward(request, response);
	}
	
	// VER DETALLE
	protected void procVerDetalle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		List<Pedido> listaPedidos = pdao.finByUsername(username);
		List<LineaPedido> listaLineaPedidos = new ArrayList<LineaPedido>();
		List<Libro> listaLibros = new ArrayList<Libro>();
		for (Pedido ele: listaPedidos) {
			// .addAll concatena dos arraylists
			listaLineaPedidos.addAll(lpdao.findByIdPedido(ele.getIdPedido()));
		}
		// Traemos los libros por cada linea de pedido
		for (LineaPedido e: listaLineaPedidos) {
			listaLibros.add(e.getLibro());
		}
		BigDecimal precio = new BigDecimal(0);
		List<Integer> listaIdTemas = new ArrayList<Integer>();
		for (Libro l: listaLibros) {
			precio = precio.add(l.getPrecioUnitario());
			boolean flag = true;
			for(Integer i: listaIdTemas) {
				if(listaIdTemas.size() != 0) {
					if (l.getTema().getIdTema() == i) {
						flag = false;
						break;
					}
				} else {
					listaIdTemas.add(l.getTema().getIdTema());
				}
			}
			if (flag)
				listaIdTemas.add(l.getTema().getIdTema());
		}

		// Enviamos parametros por request al jsp
		request.setAttribute("username", username);
		request.setAttribute("pedidos", listaPedidos.size());
		request.setAttribute("libros", listaLineaPedidos.size());
		request.setAttribute("temas", listaIdTemas.size());
		request.setAttribute("precio", precio);
		
		request.getRequestDispatcher("Vercliente.jsp").forward(request, response);
	}

}
