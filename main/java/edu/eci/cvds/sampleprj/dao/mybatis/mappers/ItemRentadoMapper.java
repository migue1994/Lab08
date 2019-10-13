package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;

public interface ItemRentadoMapper {

	/**
     * Registrar un nuevo item rentado asociado al cliente identificado
     * con 'idc' y relacionado con el item identificado con 'idi'
     * @param id
     * @param idit
     * @param fechainicio
     * @param fechafin 
     */
    public void agregarItemRentadoACliente(@Param("id_cliente")long idC, @Param("id_item") int idit,
    		@Param("fechainiciorenta")Date fechainicio,@Param("fechafinrenta")Date fechafin);

	
	 /**
     * Consultar los items rentados por un cliente
     * @param idcliente
     * @return
     */
    public List<ItemRentado> consultarItemsCliente(@Param("id_cliente") long idcliente);
    
    public int consultarDiasAlquiler(@Param("id_item") int itemId);
    
    public List<ItemRentado> consultarItemsRentados(@Param("id_item")int itemId);

	public ItemRentado consultarItemRentado(@Param("id_item")int iditem);
    
}



