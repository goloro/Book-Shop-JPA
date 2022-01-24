package modelo.dao;

import java.util.List;

import modelo.beans.Tema;

public interface IntGestionTemas {
	
	Tema findById (int idTema);
	List<Tema> findAll ();
	int insertTema (Tema tema);
	
}
