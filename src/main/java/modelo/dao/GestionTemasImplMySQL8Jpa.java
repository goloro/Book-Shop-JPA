package modelo.dao;

import java.util.List;

import modelo.beans.Tema;

public class GestionTemasImplMySQL8Jpa extends AbstractFactory implements IntGestionTemas{

	@Override
	public Tema findById(int idTema) {
		return em.find(Tema.class, idTema);
	}

	@Override
	public List<Tema> findAll() {
		return em.createQuery("select t from Tema t").getResultList();
	}

	@Override
	public int insertTema(Tema tema) {
		tx.begin();
		int filas = 0;
		try {
			em.persist(tema);
			tx.commit();
			filas = 1;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return filas;
	}
}
