package modelo.dao;

import java.util.Date;
import java.util.List;

import modelo.beans.Usuario;

public interface IntGestionUsuarios {
	
	int insertUsuarios(Usuario usuario);
	Usuario findByUsernameAndPassword(String username, String password);
	List<Usuario> findAll();
	Usuario findByUsername(String username);
	List<Usuario> findByFecha(String fecha);
	
}