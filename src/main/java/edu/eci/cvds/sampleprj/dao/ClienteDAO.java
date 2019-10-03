package edu.eci.cvds.sampleprj.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;

public interface ClienteDAO {
	
	public Cliente load(int id) throws PersistenceException;
	
	public void addItemRentado(int id, int idC,int idit, Date fechainicio,Date fechafin) throws PersistenceException;

	public List<Cliente> loadAll() throws PersistenceException;

}
