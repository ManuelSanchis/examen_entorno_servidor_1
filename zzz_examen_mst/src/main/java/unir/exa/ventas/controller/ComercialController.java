/*
 * En el HomeController trabajamos con comerciales y accedemos a las paginas que 
 * nos permiten trabajar con clientes y pedidos
 */

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
	    // Creamos una lista para almacenar los comerciales
	    List<Comercial> comerciales = comercialDao.buscarTodos();
	    // Agregamos la lista de comerciales al model con un nombre específico
	    model.addAttribute("comerciales", comerciales);

	    return "comerciales";
	}
	
	// ----------------
	@GetMapping({"/comercialDetalle/{idComercial}"})
	public String comercialVerDetalle(@PathVariable("idComercial") int idComercial, Model model) {
		// Creamos el objeto comercial
		Comercial comercial = comercialDao.buscarUno(idComercial);
		// Creamos una lista para almacenar los clientes del comercial
		List<Cliente> clientes = pedidoDao.buscarPorComercial(idComercial);
		
		// Comprueba si el comercial existe
		if(comercial != null) {
			// Agregamos el comercial al model con un nombre especifico
			model.addAttribute("comercial", comercial);
			// Agregamos los clientes al model con un nombre especifico
			model.addAttribute("clientes", clientes);
			
			return "comercialDetalle";
		}
		
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
