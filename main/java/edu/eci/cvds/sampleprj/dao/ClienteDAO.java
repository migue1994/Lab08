package edu.eci.cvds.sampleprj.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.guice.transactional.Transactional;

import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;

public interface ClienteDAO {
	
	
	public Cliente load(long id) throws PersistenceException;
	
	@Transactional
	public void addItemRentado(int id, int idC,int idit, Date fechainicio,Date fechafin) throws PersistenceException;

	public List<Cliente> loadAll() throws PersistenceException;

	public List<ItemRentado> loadItemsCliente(long idcliente) throws PersistenceException;
	
	@Transactional
	public void setVetado(long docu, boolean estado) throws PersistenceException;
}
