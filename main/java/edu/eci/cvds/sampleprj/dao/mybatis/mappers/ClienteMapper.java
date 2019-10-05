package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.ItemRentado;

/**
 *
 * @author 2106913
 */
public interface ClienteMapper {
    
    public Cliente consultarCliente(@Param("id_cliente")long id); 
    
    /**
     * Registrar un nuevo item rentado asociado al cliente identificado
     * con 'idc' y relacionado con el item identificado con 'idi'
     * @param id
     * @param idit
     * @param fechainicio
     * @param fechafin 
     */
    public void agregarItemRentadoACliente(@Param("id_itemRentado")int id, @Param("id_cliente")int idC,
    		@Param("id_item") int idit, @Param("fechainiciorenta")Date fechainicio,@Param("fechafinrenta")Date fechafin);

    /**
     * Consultar todos los clientes
     * @return 
     */
    public List<Cliente> consultarClientes();
    
    /**
     * Consultar los items rentados por un cliente
     * @param idcliente
     * @return
     */
    public List<ItemRentado> consultarItemsCliente(@Param("id_cliente") long idcliente);
    
    /**
     * Actualiza el estado de vetado del cliente
     * @param docu
     * @param estado
     */
	public void actualizarClienteVetado(@Param("id_cliente")long docu, @Param("estado")boolean estado);

}

