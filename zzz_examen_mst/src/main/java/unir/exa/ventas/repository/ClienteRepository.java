package unir.exa.ventas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import unir.exa.ventas.modelo.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	/*
	@Query("select c from Cliente c join c.pedidos p where p.comercial.idComercial = :idComercial")
    List<Cliente> clientesPorComercial(int idComercial);
	*/
}
