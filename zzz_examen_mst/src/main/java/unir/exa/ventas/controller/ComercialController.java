package unir.exa.ventas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import unir.exa.ventas.modelo.dao.ComercialDao;
import unir.exa.ventas.modelo.dao.PedidoDao;
import unir.exa.ventas.modelo.entity.Cliente;
import unir.exa.ventas.modelo.entity.Comercial;
import unir.exa.ventas.modelo.entity.Pedido;

@Controller // Para indicar que la clase es un controlador se anota con @Controller 
public class ComercialController {
	
	@Autowired
	private ComercialDao comercialDao;
	
	@Autowired
	private PedidoDao pedidoDao;
	
	// ----------------
	@GetMapping({"/"})
	public String verComerciales(Model model) {
	    List<Comercial> comerciales = comercialDao.buscarTodos();
	    
	    model.addAttribute("comerciales", comerciales);

	    return "comerciales";
	}
	
	// ----------------
	// Controlador para manejar la solicitud de ver los detalles de un comercial con sus clientes
	
	// Método para manejar peticiones GET a la URL "/comercialDetalle/{idComercial}"
	@GetMapping({"/comercialDetalle/{idComercial}"})
	public String comercialVerDetalle(@PathVariable("idComercial") int idComercial, Model model) {
		// Busca el comercial en la base de datos utilizando el idComercial proporcionado
		Comercial comercial = comercialDao.buscarUno(idComercial);
		// Obtiene la lista de clientes asociados a este comercial
		List<Cliente> clientes = pedidoDao.buscarPorComercial(idComercial);
		
		// Verifica si el comercial existe
		if(comercial != null) {
			// Agrega el comercial al modelo con el nombre "comercial"
			model.addAttribute("comercial", comercial);
			// Agrega la lista de clientes al modelo con el nombre "clientes"
			model.addAttribute("clientes", clientes);
			// Devuelve el nombre de la vista que se mostrará al usuario
			return "comercialDetalle";
		}
		// Si el comercial no existe, redirige a la URL "/"
		return "forward:/";
	}

	// ----------------
	@GetMapping({"/comercialAlta"})
	public String comercialDarAlta(Comercial comercial, Model model) {
		model.addAttribute("comercial", comercial);
		
		return "comercialAlta";
	}
	
	@PostMapping({"/comercialAlta"})
	public String comercialDarAlta(Comercial comercial) {
		comercialDao.crear(comercial);
		
		return "redirect:/";
	}
	
	// ----------------
	@GetMapping({"/comercialBorrar/{idComercial}"})
	public String comercialBorrar(@PathVariable("idComercial") int idComercial) {
		comercialDao.borrarUno(idComercial);
		
		return "forward:/";
	}
	
	// ----------------
	@GetMapping({"/comercialPedidos/{idComercial}"})
	public String comercialPedidos(@PathVariable("idComercial") int idComercial, Model model) {
		List<Pedido> pedidos = pedidoDao.buscarPedidoPorComercial(idComercial);
		model.addAttribute("pedidos", pedidos);
		
		return "comercialPedidos"; 
	}
	
	// ----------------
	@GetMapping({"/comercialModificar/{idComercial}"})
	public String comercialModificar(@PathVariable("idComercial") int idComercial, Model model) {
		Comercial comercial = comercialDao.buscarUno(idComercial);
		model.addAttribute("comercial", comercial);
		
		return "comercialModificar";
	}
	
	@PostMapping({"/comercialModificar/{idComercial}"})
	public String comercialModificar(@PathVariable("idComercial") int idComercial, Comercial comercial) {
		Comercial comercialP = comercialDao.buscarUno(idComercial);
		
		comercialP.setNombre(comercial.getNombre());
		comercialP.setApellido1(comercial.getApellido1());
		comercialP.setApellido2(comercial.getApellido2());
		comercialP.setComision(comercial.getComision());
		comercialDao.modificar(comercialP);
		
		return "redirect:/";
	}
	
}
