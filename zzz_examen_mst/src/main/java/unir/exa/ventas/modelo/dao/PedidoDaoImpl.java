package unir.exa.ventas.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import unir.exa.ventas.modelo.entity.Cliente;
import unir.exa.ventas.modelo.entity.Pedido;
import unir.exa.ventas.repository.PedidoRepository;

@Repository
public class PedidoDaoImpl implements PedidoDao {
	
	@Autowired
	private PedidoRepository pedidoRepository;

	// ----------------
	@Override
	public Pedido crear(Pedido pedido) {
		
		try {
			return pedidoRepository.save(pedido);
			
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
			
		}
	}

	// ----------------
	@Override
	public Pedido buscarUno(int idPedido) {
		
		return pedidoRepository.findById(idPedido).orElse(null);
		
	}

	// ----------------
	@Override
	public List<Pedido> buscarTodos() {

		return pedidoRepository.findAll();
		
	}

	// ----------------
	@Override
	public List<Pedido> buscarPedidoPorCliente(int idCliente) {

		return pedidoRepository.pedidosPorCliente(idCliente);
		
	}

	// ----------------
	@Override
	public List<Pedido> buscarPedidoPorComercial(int idComercial) {

		return pedidoRepository.pedidosPorComercial(idComercial);
		
	}

	// ----------------
	@Override
	public int modificar(Pedido pedido) {

		if(buscarUno(pedido.getIdPedido()) != null) {
			pedidoRepository.save(pedido);
			return 1;
			
		}else {
			return 0;
		}
		
	}

	/*
	// ----------------
	@Override
	public int borrarUno(int idPedido) {

		if(buscarUno(idPedido) != null) {
			pedidoRepository.deleteById(idPedido);
			return 1;
			
		}else {
			return 0;
		}
		
	}
	*/
	
	@Override
	public int borrarUno(int idComercial) {
	 	pedidoRepository.deleteById(idComercial);
	 	
	 	return 1;
	 }
	
	// ----------------
	@Override
	public List<Cliente> buscarPorComercial(int idComercial) {
		return pedidoRepository.clientesPorComercial(idComercial);
			
	}
}
