package unir.exa.ventas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import unir.exa.ventas.modelo.dao.ClienteDao;
import unir.exa.ventas.modelo.dao.ComercialDao;
import unir.exa.ventas.modelo.dao.PedidoDao;
import unir.exa.ventas.modelo.entity.Cliente;
import unir.exa.ventas.modelo.entity.Comercial;
import unir.exa.ventas.modelo.entity.Pedido;


@Controller // Para indicar que la clase es un controlador se anota con @Controller
public class PedidoController {

	@Autowired
	private PedidoDao pedidoDao;
	
	@Autowired
	private ClienteDao clienteDao;
	
	@Autowired
	private ComercialDao comercialDao;
	
	// ----------------
	@GetMapping({"/pedidos"})
	public String verPedidos(Model model) {
		// Creamos una lista para almacenar los pedidos
		List<Pedido> pedidos = pedidoDao.buscarTodos();
		
		// Agregamos la lista de pedidos al model con un nombre específico
		model.addAttribute("pedidos", pedidos);
			
		return "pedidos";
	}
	
	// ----------------
	@GetMapping({"/pedidos/pedidoDetalle/{idPedido}"})
	public String pedidoVerDetalle(@PathVariable("idPedido") int idPedido, Model model) {
		// Creamos el objeto pedido
		Pedido pedido = pedidoDao.buscarUno(idPedido);
		
		// Comprueba si el pedido existe
		if(pedido != null) {
			// Agregamos el cliente al model con un nombre específico
			model.addAttribute("pedido", pedido);
			
			return "pedidoDetalle";
		}
		
		return "forward:/pedidos";
	}
	
	// ----------------
	@GetMapping("/pedidos/pedidoAlta")
	public String pedidoDarAlta() {
		
		return "pedidoAlta";
	}
	
	@PostMapping("/pedidos/pedidoAlta")
	public String pedidoDarAlta(Pedido pedido) {
		pedidoDao.crear(pedido);
		
		return "redirect:/";
	}
	
	// ----------------
	@GetMapping("/pedidos/pedidoBorrar/{idPedido}")
	public String pedidosBorrar(@PathVariable("idPedido") int idPedido) {
		pedidoDao.borrarUno(idPedido);
		
		return "forward:/pedidos";
	}
	
}
