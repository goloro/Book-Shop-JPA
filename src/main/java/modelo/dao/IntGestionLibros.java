package modelo.dao;

import java.util.List;

import modelo.beans.Libro;

public interface IntGestionLibros {

	List<Libro> findByIdTema(int idTema);
	Libro findByIsbn(long isbn);
	int insertLibro(Libro libro);
	
}
