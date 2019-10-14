package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;

/**
 *
 * @author 2106913
 */
public interface ClienteMapper {
    
    public Cliente consultarCliente(@Param("id_cliente")long id); 
    
    
    /**
     * Consultar todos los clientes
     * @return 
     */
    public List<Cliente> consultarClientes();
    
 
    
    /**
     * Actualiza el estado de vetado del cliente
     * @param docu
     * @param estado
     */
	public void actualizarClienteVetado(@Param("id_cliente")long docu, @Param("estado")boolean estado);

	public void insertarCliente(@Param("cliente")Cliente c);
}

