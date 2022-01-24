package modelo.dao;

import java.util.Date;
import java.util.List;

import modelo.beans.Pedido;

public interface IntGestionPedidos {

	int insertPedido(Pedido pedido);
	List<Pedido> findByFecha(String fecha);
	List<Pedido> findAll();
	List<Pedido> finByUsername(String username);
	
}
