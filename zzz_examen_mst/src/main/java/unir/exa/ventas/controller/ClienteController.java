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
	
	@Autowired
	private ClienteDao clienteDao;

	@Autowired
	private PedidoDao pedidoDao;

	// ----------------
	@GetMapping({"/clientes"})
	public String verClientes(Model model) {
		// Creamos una lista para almacenar los clientes
		List<Cliente> clientes = clienteDao.buscarTodos();
		// Agregamos la lista de clientes al model con un nombre específico
		model.addAttribute("clientes", clientes);
		
		return "clientes";
	}
	
	// ----------------
	@GetMapping("/clientes/clienteDetalle/{idCliente}")
	public String clienteVerDetalle (@PathVariable("idCliente") int idCliente, Model model) {
		// Creamos el objeto cliente
		Cliente cliente = clienteDao.buscarUno(idCliente);
		// Agregamos la lista de comerciales al model con un nombre específico
		List<Pedido> pedidos = pedidoDao.buscarPedidoPorCliente(idCliente);
		
		// Comprueba si el cliente existe
		if(cliente != null) {
			// Agregamos el cliente al model con un nombre específico
			model.addAttribute("cliente", cliente);
			// Agregamos los pedidos al model con un nombre especifico
			model.addAttribute("pedidos", pedidos);
			
			return "clienteDetalle";
		}
		
		return "forward:/clientes";
	}
	
	// ----------------
	@GetMapping({"/clientes/clienteAlta"})
	public String clienteDarAlta(Cliente cliente, Model model) {
		model.addAttribute("cliente", cliente);
		
		return "clienteAlta";
	}
	
	@PostMapping({"/clientes/clienteAlta"})
	public String clienteDarAlta(Cliente cliente) {
		clienteDao.crear(cliente);
		
		return "redirect:/clientes";
	}
	
	
	// ----------------
	@GetMapping({"/clientes/clienteBorrar/{idCliente}"})
	public String clienteBorrar(@PathVariable("idCliente") int idCliente) {
		clienteDao.borrarUno(idCliente);
		
		return "forward:/clientes";
	}
	
	// ----------------
	@GetMapping({"/clientes/clientePedidos/{idCliente}"})
	public String clientePedidos(@PathVariable("idCliente") int idCliente, Model model) {
		List<Pedido> pedidos = pedidoDao.buscarPedidoPorCliente(idCliente);
		model.addAttribute("pedidos", pedidos);
		
		return "clientePedidos";
	}
	
}
