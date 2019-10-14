package edu.eci.cvds.sampleprj.dao.mybatis;

import java.util.Date;
import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.ItemRentadoDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemRentadoMapper;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;

public class MyBatisItemRentadoDAO implements ItemRentadoDAO{

	@Inject
	  private ItemRentadoMapper itemRentadoMapper;
	
	@Override
	public int consultarDiasAlquiler(int itemId) {
		return itemRentadoMapper.consultarDiasAlquiler(itemId);
		
	}

	@Override
	public void addItemRentado(long idC,int idit, Date fechainicio,Date fechafin) throws PersistenceException{
		try{
			itemRentadoMapper.agregarItemRentadoACliente(idC, idit, fechainicio, fechafin);
		}
		catch(org.apache.ibatis.exceptions.PersistenceException e) {
			throw new PersistenceException("Error al agregar el Item Rentado", e);
		}        
	}
	
	@Override
	public List<ItemRentado> loadItemsCliente(long idcliente) throws PersistenceException{
		try{
			return itemRentadoMapper.consultarItemsCliente(idcliente);
		}
		catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Error al consultar clientes", e);
		}        
	}

	@Override
	public List<ItemRentado> loadItemsRentados(int itemId) throws PersistenceException{
		try{
			List<ItemRentado> lr=itemRentadoMapper.consultarItemsRentados(itemId);
			if(lr==null) throw new org.apache.ibatis.exceptions.PersistenceException();
			return lr;
		}
		catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Item en alquiler no encontrado "+itemId);
		}
	}

	@Override
	public ItemRentado loadItemRentado(int iditem) throws PersistenceException {
		try {
			ItemRentado ir=itemRentadoMapper.consultarItemRentado(iditem);
			if(ir==null) throw new org.apache.ibatis.exceptions.PersistenceException();
			return ir;
		}
		catch(org.apache.ibatis.exceptions.PersistenceException e){
			throw new PersistenceException("Item rentado no encontrado");
		}
		
	}
}
