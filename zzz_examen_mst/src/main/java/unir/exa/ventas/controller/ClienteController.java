package unir.exa.ventas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import unir.exa.ventas.modelo.dao.ClienteDao;

import unir.exa.ventas.modelo.dao.PedidoDao;
import unir.exa.ventas.modelo.entity.Cliente;
import unir.exa.ventas.modelo.entity.Pedido;


@Controller // Para indicar que la clase es un controlador se anota con @Controller 
public class ClienteController {
	
	// Desde el controlador llamamos a los métodos del daoImpl
	
	// Indica que Spring debe automáticamente inyectar una instancia de las clases especificadas.
	@Autowired
	private ClienteDao clienteDao;
	
	// Indica que Spring debe automáticamente inyectar una instancia de las clases especificadas.
	@Autowired
	private PedidoDao pedidoDao;

	// ----------------
	// Controlador para manejar la solicitud de ver la lista de todos los clientes

	// Método para manejar peticiones GET a la URL "/clientes"
	@GetMapping("/clientes")
	public String verClientes(Model model) {
		// Llama al método buscarTodos del ClienteDao para obtener la lista de todos los clientes
		List<Cliente> clientes = clienteDao.buscarTodos();
		// Agregamos la lista de clientes al model con un nombre específico
		model.addAttribute("clientes", clientes);
		// Devuelve el nombre de la vista que se mostrará al usuario
		return "clientes";
	}
	
	// ----------------
	// Controlador para manejar la solicitud de ver los detalles de un cliente

	// Método para manejar peticiones GET a la URL "/clientes/clienteDetalle/{idCliente}"
	@GetMapping("/clientes/clienteDetalle/{idCliente}")
	public String clienteVerDetalle (@PathVariable("idCliente") int idCliente, Model model) {
		// Llama al método buscarUno del ClienteDao para obtener los detalles del cliente con el ID especificado
		Cliente cliente = clienteDao.buscarUno(idCliente);
		// Llama al método buscarPedidoPorCliente del PedidoDao para obtener la lista de pedidos asociados al cliente
		List<Pedido> pedidos = pedidoDao.buscarPedidoPorCliente(idCliente);
		
		// Comprueba si el cliente existe
		if(cliente != null) {
			// Agregamos el cliente al model con un nombre específico
			model.addAttribute("cliente", cliente);
			// Agregamos los pedidos al model con un nombre especifico
			model.addAttribute("pedidos", pedidos);
			// Devuelve el nombre de la vista que se mostrará al usuario
			return "clienteDetalle";
		}
		// Si el cliente no existe, redirige a la URL "/clientes"
		return "forward:/clientes";
	}
	
	// ----------------
	// Controlador para gestionar la creación de un nuevo cliente
	
	// Método para manejar peticiones GET a la URL "/clientes/clienteAlta"
	@GetMapping("/clientes/clienteAlta")
	public String clienteDarAlta(Cliente cliente, Model model) {
		// Agrega el cliente al model con un nombre especifico
		model.addAttribute("cliente", cliente);
		// Devuelve el nombre de la vista que se mostrará al usuario
		return "clienteAlta";
	}
	// Controlador para gestionar la creación de un nuevo cliente
	
	// Método para manejar peticiones POST a la URL "/clientes/clienteAlta"
	@PostMapping({"/clientes/clienteAlta"})
	public String clienteDarAlta(Cliente cliente) {
		// Agrega el cliente al model con un nombre especifico
		clienteDao.crear(cliente);
		// Redirige a la URL "/clientes" después de crear el cliente
		return "redirect:/clientes";
	}
	
	
	// ---------------
	// Controlador para gestionar el borrado de un cliente
	
	// Método para manejar peticiones GET a la URL "/clientes/clienteBorrar/{idCliente}"
	@GetMapping({"/clientes/clienteBorrar/{idCliente}"})
	public String clienteBorrar(@PathVariable("idCliente") int idCliente) {
		// Llama al método borrarUno del ClienteDao para eliminar el cliente con el ID especificado
		clienteDao.borrarUno(idCliente);
		// Redirige a la URL "/clientes" después de borrar el cliente
		return "forward:/clientes";
	}
	
	// ----------------
	// Controlador para manejar la solicitud de obtener los pedidos asociados a un cliente
	
	// Método para manejar peticiones GET a la URL "/clientes/clientePedidos/{idCliente}"
	@GetMapping({"/clientes/clientePedidos/{idCliente}"})
	public String clientePedidos(@PathVariable("idCliente") int idCliente, Model model) {
		// Llama al método buscarPedidoPorCliente del PedidoDao para obtener la lista de pedidos del cliente
		List<Pedido> pedidos = pedidoDao.buscarPedidoPorCliente(idCliente);
		// Agrega los pedidos al model con un nombre especifico
		model.addAttribute("pedidos", pedidos);
		// Devuelve el nombre de la vista que se mostrará al usuario
		return "clientePedidos";
	}
	
	// ----------------
	// Controlador para manejar la solicitud de obtener los detalles de un cliente para su modificación

	// Método para manejar peticiones GET a la URL "/clientes/clienteModificar/{idCliente}"
	@GetMapping({"/clientes/clienteModificar/{idCliente}"})
	public String clienteModificar(@PathVariable("idCliente") int idCliente, Model model) {
		// Llama al método buscarUno del ClienteDao para obtener los detalles del cliente con el ID especificado
		Cliente cliente = clienteDao.buscarUno(idCliente);
		// Agrega los pedidos al model con un nombre especifico
		model.addAttribute("cliente", cliente);
		// Devuelve el nombre de la vista que se mostrará al usuario
		return "clienteModificar";
	}
	
	// Controlador para manejar la solicitud de modificar un cliente

	// Método para manejar peticiones POST a la URL "/clientes/clienteModificar/{idCliente}"
	@PostMapping({"/clientes/clienteModificar/{idCliente}"})
	public String clienteModificar(@PathVariable("idCliente") int idCliente, Cliente cliente) {
		// Llama al método buscarUno del ClienteDao para obtener el cliente existente con el ID especificado
		Cliente clienteP = clienteDao.buscarUno(idCliente);
		// Actualiza los atributos del cliente existente con los nuevos valores proporcionados
		clienteP.setNombre(cliente.getNombre());
		clienteP.setApellido1(cliente.getApellido1());
		clienteP.setApellido2(cliente.getApellido2());
		clienteP.setCategoria(cliente.getCategoria());
		// Llama al método modificar del ClienteDao para guardar los cambios en la base de datos
		clienteDao.modificar(clienteP);
		// Redirige a la URL "/clientes" después de modificar el cliente
		return "redirect:/clientes";
	}
	
}
