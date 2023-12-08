package unir.exa.ventas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import unir.exa.ventas.modelo.entity.Cliente;
import unir.exa.ventas.modelo.entity.Comercial;
import unir.exa.ventas.modelo.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	// Pedidos por cliente
    @Query("select p from Pedido p where p.cliente.idCliente = ?1")
    public List<Pedido> pedidosPorCliente(int idCliente);

    // Pedidos por comercial
    @Query("select p from Pedido p where p.comercial.idComercial = ?1")
    public List<Pedido> pedidosPorComercial(int idComercial);
    
    // Cliente por pedido
    @Query("select p.cliente from Pedido p where p.idPedido = ?1")
    public Cliente clientePorPedido(int idPedido);
    
    // Comercial por pedido
    @Query("select p.comercial from Pedido p where p.idPedido = ?1")
    public Comercial comercialPorPedido(int idPedido);
    
    // Clientes por comercial
    @Query("select c from Pedido p join p.cliente c join p.comercial com where com.idComercial = ?1")
    public List<Cliente> clientesPorComercial(int idComercial);
    
    
}


