/**
 * 
 */
package modelo.dao;

import java.util.List;

import modelo.beans.LineaPedido;

/**
 * @author golor
 *
 */
public class GestionLineaPedidoImplMySQL8Jpa extends AbstractFactory implements IntGestionLineaPedidos {

	@Override
	public List<LineaPedido> findByIdPedido(int idPedido) {
		sql = "select l from LineaPedido l where l.pedido.idPedido = :idPedido";
		query = em.createQuery(sql);
		
		try {
			query.setParameter("idPedido", idPedido);
			
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
