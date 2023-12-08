package unir.exa.ventas.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import unir.exa.ventas.modelo.entity.Cliente;
import unir.exa.ventas.repository.ClienteRepository;
import unir.exa.ventas.repository.PedidoRepository;

@Repository
public class ClienteDaoImpl implements ClienteDao {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	

	// ----------------
	@Override
	public Cliente crear(Cliente cliente) {
		
		try {
			return clienteRepository.save(cliente);
			
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
			
		}
	}

	// ----------------
	@Override
	public Cliente buscarUno(int idCliente) {
		
		return clienteRepository.findById(idCliente).orElse(null);
		
	}

	// ----------------
	@Override
	public List<Cliente> buscarTodos() {

		return clienteRepository.findAll();
		
	}
	
	// ----------------
	@Override
	public int modificar(Cliente cliente) {

		if(buscarUno(cliente.getIdCliente()) != null) {
			clienteRepository.save(cliente);
			
			return 1;
			
		}else {
			
			return 0;		
		}
	}

	/*
	// ----------------
	@Override
	public int borrarUno(int idCliente) {
		
		if(buscarUno(idCliente) != null) {
			clienteRepository.deleteById(idCliente);
			return 1;
			
		}else {
			return 0;
			
		}
	}
	*/
	
	@Override
	public int borrarUno(int idCliente) {
	 	clienteRepository.deleteById(idCliente);
	 	
	 	return 1;
	 }

	/*
	// ----------------
	@Override
	public List<Cliente> buscarPorComercial(int idComercial) {
		return pedidoRepository.clientesPorComercial(idComercial);
			
	}
	*/
	
}
