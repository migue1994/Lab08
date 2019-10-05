package edu.eci.cvds.sampleprj.dao.mybatis;

import java.util.Date;
import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.ItemRentado;


public class MyBatisClienteDAO implements ClienteDAO{
	@Inject
	private ClienteMapper clienteMapper;    
		
	@Override
	public Cliente load(long id) throws PersistenceException{
		try{
			return clienteMapper.consultarCliente(id);
		}
		catch(org.apache.ibatis.exceptions.PersistenceException e){
		    throw new PersistenceException("Error al consultar cliente "+ Integer.toString((int)id), e);
		}        

	}
	  
	@Override
	public void addItemRentado(int id, int idC,int idit, Date fechainicio,Date fechafin) throws PersistenceException{
		try{
			clienteMapper.agregarItemRentadoACliente(id, idC, idit, fechainicio, fechafin);;
		}
		catch(org.apache.ibatis.exceptions.PersistenceException e) {
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

	@Override
	public List<ItemRentado> loadItemsCliente(long idcliente) throws PersistenceException{
		try{
			return clienteMapper.consultarItemsCliente(idcliente);
		}
		catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al consultar clientes", e);
		}        
	}
	
	@Override
	public void setVetado(long docu, boolean estado) throws PersistenceException{
		try {
			clienteMapper.actualizarClienteVetado(docu,estado);
		}
		catch(org.apache.ibatis.exceptions.PersistenceException e) {
			throw new PersistenceException("No fue actualizar el estado vetado");
		}
	}
	

}
