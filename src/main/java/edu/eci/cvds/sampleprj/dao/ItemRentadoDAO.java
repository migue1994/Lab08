package edu.eci.cvds.sampleprj.dao;

import java.util.Date;
import java.util.List;

import org.mybatis.guice.transactional.Transactional;

import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;

public interface ItemRentadoDAO {

	public int consultarDiasAlquiler(int itemId);
	
	@Transactional
	public void addItemRentado(long idC,int idit, Date fechainicio,Date fechafin) throws PersistenceException;

	public List<ItemRentado> loadItemsCliente(long idcliente) throws PersistenceException;
	
	public List<ItemRentado> loadItemsRentados(int itemId) throws PersistenceException;

	public ItemRentado loadItemRentado(int iditem) throws PersistenceException;
}
