package edu.eci.cvds.sampleprj.dao.mybatis;

import java.util.Date;
import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Cliente;


public class MyBatisClienteDAO implements ClienteDAO{

	@Inject
	 private ClienteMapper clienteMapper;    

	  @Override
	  public Cliente load(int id) throws PersistenceException{
		  try{
			  return clienteMapper.consultarCliente(id);
		  }
		  catch(org.apache.ibatis.exceptions.PersistenceException e){
		      throw new PersistenceException("Error al consultar cliente "+ Integer.toString(id), e);
		  }        

	  }
	  
	  @Override
	  public void addItemRentado(int id, int idC,int idit, Date fechainicio,Date fechafin) throws PersistenceException{
		  try{
			  clienteMapper.agregarItemRentadoACliente(id, idC, idit, fechainicio, fechafin);;
		  }
		  catch(org.apache.ibatis.exceptions.PersistenceException e){
		      throw new PersistenceException("Error al agregar el Item Rentado", e);
		  }        

	  }

	  @Override
	  public List<Cliente> loadAll() throws PersistenceException{
		  try{
			  return clienteMapper.consultarClientes();
		  }
		  catch(org.apache.ibatis.exceptions.PersistenceException e){
		      throw new PersistenceException("Error al consultar clientes", e);
		  }        

	  }
}
