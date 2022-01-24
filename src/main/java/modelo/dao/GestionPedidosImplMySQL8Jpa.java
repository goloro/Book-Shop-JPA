package modelo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import modelo.beans.Pedido;

public class GestionPedidosImplMySQL8Jpa extends AbstractFactory implements IntGestionPedidos {

	@Override
	public int insertPedido(Pedido pedido) {
		tx.begin();
		int filas = 0;
		try {
			em.persist(pedido);
			tx.commit();
			filas = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

	@Override
	public List<Pedido> findByFecha(String fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		sql = "select p from Pedido p where p.fechaAlta = :fecha";
		query = em.createQuery(sql);
		
		try {
			query.setParameter("fecha", sdf.parse(fecha));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return query.getResultList();
	}

	@Override
	public List<Pedido> findAll() {
		return em.createQuery("select p from Pedido p").getResultList();
	}

	@Override
	public List<Pedido> finByUsername(String username) {
		sql = "select p from Pedido p where p.usuario.username = :usu";
		query = em.createQuery(sql);
		
		try {
			query.setParameter("usu", username);
			
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
