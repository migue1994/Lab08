package edu.eci.cvds.sampleprj.dao.mybatis;

import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.TipoItemDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.TipoItemMapper;
import edu.eci.cvds.samples.entities.TipoItem;

public class MyBatisTipoItemDAO implements TipoItemDAO{
	
	@Inject
	private TipoItemMapper tipoItemMapper;    

	@Override
	public TipoItem load(int id) throws PersistenceException{
		try{
			  return tipoItemMapper.getTipoItem(id);
		  }
		  catch(org.apache.ibatis.exceptions.PersistenceException e){
		      throw new PersistenceException("Error al consultar el tipo de item "+ Integer.toString(id), e);
		  }  
	}

	public void save(String des) throws PersistenceException{
		try{
			  tipoItemMapper.addTipoItem(des);;
		  }
		  catch(org.apache.ibatis.exceptions.PersistenceException e){
		      throw new PersistenceException("Error al guardar la descripción ", e);
		  }  
		
	}

	@Override
	public List<TipoItem> loadAll() throws PersistenceException {
		try{
			return tipoItemMapper.getTiposItems();
		  }
		  catch(org.apache.ibatis.exceptions.PersistenceException e){
		      throw new PersistenceException("Error al guardar la descripción ", e);
		  } 
	}

}
