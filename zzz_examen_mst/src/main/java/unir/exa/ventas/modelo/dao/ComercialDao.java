package unir.exa.ventas.modelo.dao;

import java.util.List;

import unir.exa.ventas.modelo.entity.Comercial;

public interface ComercialDao {
	
	// Create
	Comercial crear(Comercial comercial);
	
	// Read
	Comercial buscarUno(int IdComercial);
	List<Comercial> buscarTodos();
	
	// Update
	int modificar (Comercial comercial);
	
	// Delete
	int borrarUno (int idComercial);

}
