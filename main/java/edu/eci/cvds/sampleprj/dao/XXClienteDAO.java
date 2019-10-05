package edu.eci.cvds.sampleprj.dao;

import java.util.Date;
import java.util.List;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.ItemRentado;

public class XXClienteDAO implements ClienteDAO{

	@Override
	public Cliente load(long id) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addItemRentado(int id, int idC, int idit, Date fechainicio, Date fechafin) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Cliente> loadAll() throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItemRentado> loadItemsCliente(long idcliente) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setVetado(long docu, boolean estado) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}
	
	

}
