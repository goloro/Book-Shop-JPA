package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import modelo.dao.GestionPedidosImplMySQL8Jpa;
import modelo.dao.GestionTemasImplMySQL8Jpa;
import modelo.dao.IntGestionLibros;
import modelo.dao.IntGestionPedidos;
import modelo.dao.IntGestionTemas;

/**
 * Servlet implementation class BookController
 */
@WebServlet("/book")
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IntGestionTemas tdao;
	private IntGestionLibros ldao;
	private IntGestionPedidos pdao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookController() {
        super();
        tdao = new GestionTemasImplMySQL8Jpa();
        ldao = new GestionLibrosImplMySQL8Jpa();
        pdao = new GestionPedidosImplMySQL8Jpa();
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
		case "temas":
			procTemas(request, response);
			break;
		case "buscarTema":
			procBuscarTema(request, response);
			break;
		case "anadirCarrito":
			procAnadirCarrito(request, response);
			break;
		case "eliminarLibro":
			procEliminarLibro(request, response);
			break;
		case "vaciarCarrito":
			procVaciarCarrito(request, response);
			break;
		case "comprar":
			procComprar(request, response);
			break;
		case "verDetalle":
			procVerDetalle(request, response);
			break;
		default:
			System.out.println("opcion errónea: " + opcion);
		}
	}
	
	// MOSTRAR TEMAS
	protected void procTemas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Tema> lista = tdao.findAll();
		request.getSession().setAttribute("listaTemas", lista);
		request.getRequestDispatcher("Vertemas.jsp").forward(request, response);
	}
	
	// MOSTRAR LIBROS POR TEMAS
	protected void procBuscarTema(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idTema = Integer.parseInt(request.getParameter("idTema"));
		request.getSession().setAttribute("tema", tdao.findById(idTema));
		request.setAttribute("listaLibros", ldao.findByIdTema(idTema));
		request.getRequestDispatcher("Verlibros.jsp").forward(request, response);
	}
	
	// AÑADIR LIBRO AL CARRITO
	protected void procAnadirCarrito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] listaISBN = request.getParameterValues("seleccionar");
		List<Libro> listaSeleccionados;
		if (request.getSession().getAttribute("listaCarrito") == null) {
			listaSeleccionados = new ArrayList <Libro>();
		} else {
			listaSeleccionados = (List<Libro>) request.getSession().getAttribute("listaCarrito");
		}
		for (String ele: listaISBN) {
			long isbn = Long.parseLong(ele);
			boolean flag = true;
			for (Libro e: listaSeleccionados) {
				if (isbn == e.getIsbn()) {
					flag = false;
				}
			}
			if (flag == true)
				listaSeleccionados.add(ldao.findByIsbn(isbn));
		}
		request.getSession().setAttribute("listaCarrito", listaSeleccionados);
		procTemas(request, response);
	}
	
	// ELIMINAR LIBRO DEL CARRITO
	protected void procEliminarLibro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbnParametro = request.getParameter("isbn");
		Long isbn = Long.parseLong(isbnParametro);
		
		List<Libro> listaCarrito;
		if (request.getSession().getAttribute("listaCarrito") == null) {
			listaCarrito = new ArrayList <Libro>();
		} else {
			listaCarrito = (List<Libro>) request.getSession().getAttribute("listaCarrito");
		}
		
		for (Libro ele: listaCarrito) {
			if (ele.getIsbn() == isbn) {
				listaCarrito.remove(ele);
				break;
			}
		}
		request.getSession().setAttribute("listaCarrito", listaCarrito);
		request.getRequestDispatcher("Carrito.jsp").forward(request, response);
	}
	
	// VACIAR CARRITO
	protected void procVaciarCarrito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Libro> listaCarrito = new ArrayList <Libro>();
		request.getSession().setAttribute("listaCarrito", listaCarrito);
		request.getRequestDispatcher("Carrito.jsp").forward(request, response);
	}
	
	// COMPRAR PEDIDO
	protected void procComprar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Pedido pedido = new Pedido();
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		
		pedido.setDireccionEntrega(usuario.getDireccion());
		pedido.setEstado("comprado");
		pedido.setFechaAlta(new Date());
		pedido.setUsuario(usuario);

		List<Libro> listaCarrito = (List<Libro>) request.getSession().getAttribute("listaCarrito");
		for (Libro ele: listaCarrito) {
			LineaPedido linea = new LineaPedido();
			
			linea.setPedido(pedido);
			linea.setLibro(ele);
			linea.setFechaAlta(pedido.getFechaAlta());
			linea.setCantidad(1);
			linea.setPrecioVenta(ele.getPrecioUnitario());
			
			pedido.addLineaPedido(linea);
		}
		
		int filas = pdao.insertPedido(pedido);
		if (filas == 1) {
			List<Libro> listaCarritoVacio = new ArrayList <Libro>();
			request.getSession().setAttribute("listaCarrito", listaCarritoVacio);
			procTemas(request, response);
		}
	}
	
	// VER DETALLE DEL LIBRO
	protected void procVerDetalle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbnParametro = request.getParameter("isbn");
		Long isbn = Long.parseLong(isbnParametro);
		
		List<Libro> listaCarrito;
		if (request.getSession().getAttribute("listaCarrito") == null) {
			listaCarrito = new ArrayList <Libro>();
		} else {
			listaCarrito = (List<Libro>) request.getSession().getAttribute("listaCarrito");
		}
		for (Libro ele: listaCarrito) {
			if (ele.getIsbn() == isbn) {
				request.setAttribute("libro", ele);
				break;
			}
		}

		request.getRequestDispatcher("Verdetalle.jsp").forward(request, response);
	}

}
