package unir.exa.ventas.modelo.dao;

import java.util.List;

import unir.exa.ventas.modelo.entity.Cliente;
import unir.exa.ventas.modelo.entity.Pedido;

public interface PedidoDao {
	
	// Create
	Pedido crear(Pedido pedido);
	
	// Read
	Pedido buscarUno(int idPedido);
	List<Pedido> buscarTodos();
	List<Pedido> buscarPedidoPorCliente(int idCliente);
	List<Pedido> buscarPedidoPorComercial(int idComercial);
	List<Cliente> buscarPorComercial(int idComercial);
	
	// Update
	int modificar(Pedido pedido);
	
	// Delete
	int borrarUno(int idPedido);

}
