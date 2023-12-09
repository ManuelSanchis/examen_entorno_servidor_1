package unir.exa.ventas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import unir.exa.ventas.modelo.dao.PedidoDao;

import unir.exa.ventas.modelo.entity.Pedido;


@Controller // Para indicar que la clase es un controlador se anota con @Controller
public class PedidoController {

	@Autowired
	private PedidoDao pedidoDao;
	
	// ----------------
	@GetMapping({"/pedidos"})
	public String verPedidos(Model model) {
		List<Pedido> pedidos = pedidoDao.buscarTodos();
		
		model.addAttribute("pedidos", pedidos);
			
		return "pedidos";
	}
	
	// ----------------
	@GetMapping({"/pedidos/pedidoDetalle/{idPedido}"})
	public String pedidoVerDetalle(@PathVariable("idPedido") int idPedido, Model model) {
		Pedido pedido = pedidoDao.buscarUno(idPedido);
		
		if(pedido != null) {
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
