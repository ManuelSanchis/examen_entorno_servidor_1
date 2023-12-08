package unir.exa.ventas.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import unir.exa.ventas.modelo.entity.Comercial;
import unir.exa.ventas.repository.ComercialRepository;
import unir.exa.ventas.repository.PedidoRepository;

@Repository
public class ComercialDaoImpl implements ComercialDao {

	@Autowired
	private ComercialRepository comercialRepository;
	
	
	// ----------------
	@Override
	public Comercial crear(Comercial comercial) {

		try {
			return comercialRepository.save(comercial);
			
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
			
		}
	}

	// ----------------
	@Override
	public Comercial buscarUno(int idComercial) {

		return comercialRepository.findById(idComercial).orElse(null);
		
	}

	// ----------------
	@Override
	public List<Comercial> buscarTodos() {

		return comercialRepository.findAll();
		
	}

	// ----------------
	@Override
	public int modificar(Comercial comercial) {

		if(buscarUno(comercial.getIdComercial()) != null) {
			comercialRepository.save(comercial);
			return 1;
			
		}else {
			return 0;
			
		}	
	}

	/*
	// ----------------
	@Override
	public int borrarUno(int idComercial) {
		
		if(buscarUno(idComercial) != null) {
			comercialRepository.deleteById(idComercial);
			return 1;
			
		}else {
			return 0;
			
		}
	}
	*/
	
	@Override
	public int borrarUno(int idComercial) {
	 	comercialRepository.deleteById(idComercial);
	 	
	 	return 1;
	 }

}
