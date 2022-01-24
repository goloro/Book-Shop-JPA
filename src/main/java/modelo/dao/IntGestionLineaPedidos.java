package modelo.dao;

import java.util.List;

import modelo.beans.LineaPedido;

public interface IntGestionLineaPedidos {
	
	List<LineaPedido> findByIdPedido(int idPedido);
	
}
