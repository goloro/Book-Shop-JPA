package modelo.dao;

import modelo.beans.Perfile;

public class GestionPerfilesImplMySQL8Jpa extends AbstractFactory implements IntGestionPerfiles {

	@Override
	public Perfile findById(int idPerfil) {
		return em.find(Perfile.class, idPerfil);
	}

}
