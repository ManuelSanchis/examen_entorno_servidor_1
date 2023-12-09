package unir.exa.ventas.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import unir.exa.ventas.modelo.entity.Cliente;
import unir.exa.ventas.modelo.entity.Pedido;
import unir.exa.ventas.repository.PedidoRepository;

@Repository // La anotación @Repository indica que esta clase es un componente de acceso a datos (DAO)
public class PedidoDaoImpl implements PedidoDao {
	
	// Indica que Spring debe automáticamente inyectar una instancia de las clases especificadas
	@Autowired
	private PedidoRepository pedidoRepository;

	// ----------------
	// Método personalizado que crea un nuevo pedido
	
	@Override
	public Pedido crear(Pedido pedido) {
		
		try {
			// Este método guarda el objeto en la base de datos y devuelve la entidad persistida
			return pedidoRepository.save(pedido);
		// Si ocurre una excepción durante la operación de guardado, captura la excepción
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
			
		}
	}

	// ----------------
	// Método personalizado que busca un pedido por su ID
	
	@Override
	public Pedido buscarUno(int idPedido) {
		// Utiliza el método findById proporcionado por Spring Data JPA para buscar un Pedido por su ID
	    // Si se encuentra, devuelve el objeto Pedido, de lo contrario, devuelve null
		return pedidoRepository.findById(idPedido).orElse(null);	
	}

	// ----------------
	// Método personalizado que busca todos los pedidos

	@Override
	public List<Pedido> buscarTodos() {
		// Utiliza el método findAll proporcionado por Spring Data JPA para obtener todos los pedidos en la base de datos
	    // Devuelve una lista que contiene todos los objetos Pedido presentes en la base de datos
		return pedidoRepository.findAll();	
	}

	// ----------------
	// Método que busca todos los pedidos asociados a un cliente específico por su ID
	
	@Override
	public List<Pedido> buscarPedidoPorCliente(int idCliente) {
		// Este método personalizado esta previamente definido en el repositorio
	    // Devuelve la lista de pedidos asociados al cliente especificado
		return pedidoRepository.pedidosPorCliente(idCliente);	
	}

	// ----------------
	// Método que busca todos los pedidos asociados a un comercial específico por su ID.
	
	@Override
	public List<Pedido> buscarPedidoPorComercial(int idComercial) {
		// Este método personalizado esta previamente definido en el repositorio
		// Devuelve la lista de pedidos asociados al comercial especificado
		return pedidoRepository.pedidosPorComercial(idComercial);	
	}

	// ----------------
	// Método que modifica un Pedido existente en la base de datos
	
	@Override
	public int modificar(Pedido pedido) {
		// Si el Pedido existe, utiliza el método save proporcionado por Spring Data JPA
        // para actualizar la información del Pedido en la base de datos
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
	
	// ----------------
	// Método que borra un Pedido asociado a un comercial por su ID
	
	@Override
	public int borrarUno(int idComercial) {
		// Utiliza el método deleteById proporcionado por Spring Data JPA para borrar el Pedido con el ID especificado
	 	pedidoRepository.deleteById(idComercial);
	 	// Devuelve 1 para indicar que el borrado fue exitoso
	 	return 1;
	 }
	
	// ----------------
	// Método que busca todos los clientes asociados a un comercial por su ID
	
	@Override
	public List<Cliente> buscarPorComercial(int idComercial) {
		// Utiliza un método personalizado llamado "clientesPorComercial" definido en la interfaz PedidoRepository
	    // Devuelve la lista de clientes asociados al comercial especificado
		return pedidoRepository.clientesPorComercial(idComercial);		
	}
}
