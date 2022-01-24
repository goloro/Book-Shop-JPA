package modelo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import modelo.beans.Usuario;

public class GestionUsuariosImplMySQL8Jpa extends AbstractFactory implements IntGestionUsuarios {

	@Override
	public int insertUsuarios(Usuario usuario) {
		tx.begin();
		int filas = 0;
		try {
			em.persist(usuario);
			tx.commit();
			filas = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

	@Override
	public Usuario findByUsernameAndPassword(String username, String password) {
		sql = "select u from Usuario u where u.username = :usu and u.password= :pass";
		query = em.createQuery(sql);
		
		try {
			query.setParameter("usu", username);
			query.setParameter("pass", password);
			
			return (Usuario) query.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Usuario> findAll() {
		return em.createQuery("select u from Usuario u").getResultList();
	}

	@Override
	public Usuario findByUsername(String username) {
		sql = "select u from Usuario u where u.username = :usu";
		query = em.createQuery(sql);
		
		try {
			query.setParameter("usu", username);
			
			return  (Usuario) query.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Usuario> findByFecha(String fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		sql = "select u from Usuario u where u.fechaAlta = :fecha";
		query = em.createQuery(sql);
		
		try {
			query.setParameter("fecha", sdf.parse(fecha));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return query.getResultList();
	}
}
