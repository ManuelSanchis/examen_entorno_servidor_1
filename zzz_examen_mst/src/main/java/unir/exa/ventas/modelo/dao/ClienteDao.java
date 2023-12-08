package unir.exa.ventas.modelo.dao;

import java.util.List;

import unir.exa.ventas.modelo.entity.Cliente;

public interface ClienteDao {
	
	//Create
	Cliente crear(Cliente cliente);
	
	// Read
	Cliente buscarUno(int idCliente);
	List<Cliente> buscarTodos();
	// List<Cliente> buscarPorComercial(int idComercial);
	
	// Update
	int modificar (Cliente cliente);
	
	// Delete
	int borrarUno (int idCliente);

}
