package modelo.dao;

import java.util.List;

import modelo.beans.Libro;

public class GestionLibrosImplMySQL8Jpa extends AbstractFactory implements IntGestionLibros {

	@Override
	public List<Libro> findByIdTema(int idTema) {
		sql = "select l from Libro l where l.tema.idTema = :idTema";
		query = em.createQuery(sql);
		
		try {
			query.setParameter("idTema", idTema);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return query.getResultList();
	}

	@Override
	public Libro findByIsbn(long isbn) {
		sql = "select l from Libro l where l.isbn = :isbn";
		query = em.createQuery(sql);
		
		try {
			query.setParameter("isbn", isbn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (Libro) query.getSingleResult();
	}

	@Override
	public int insertLibro(Libro libro) {
		tx.begin();
		int filas = 0;
		try {
			em.persist(libro);
			tx.commit();
			filas = 1;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return filas;
	}

}
