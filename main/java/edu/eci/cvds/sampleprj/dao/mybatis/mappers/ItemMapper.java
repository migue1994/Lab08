package edu.eci.cvds.sampleprj.dao.mybatis.mappers;


import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.TipoItem;

/**
 *
 * @author 2106913
 */
public interface ItemMapper {
    
    
    public List<Item> consultarItems();        
    
    public Item consultarItem(@Param("id_item")int id);
    
    public void insertarItem(@Param("item")Item it);

    public List<Item> consultarItemsDisponibles();
    
    public TipoItem consultarTipoItem(@Param("id_item")int id);
    
    public Item consultarCostoAlquiler(@Param("id_item")int iditem);
    
    public void actualizarTarifa(@Param("id_item")int id, @Param("tarifa") long tarifa);
        
}
