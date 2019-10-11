package edu.eci.cvds.sampleprj.dao;

import java.util.List;

import edu.eci.cvds.samples.entities.ItemRentado;

public interface ItemRentadoDAO {

	public int consultarDiasAlquiler(int itemId);
	
	public List<ItemRentado> loadItemsCliente(long idcliente) throws PersistenceException;
}
