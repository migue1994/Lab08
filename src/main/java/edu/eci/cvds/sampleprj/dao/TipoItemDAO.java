package edu.eci.cvds.sampleprj.dao;

import java.util.Date;
import java.util.List;

import org.mybatis.guice.transactional.Transactional;

import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.TipoItem;

public interface TipoItemDAO {

	   public TipoItem load(int id) throws PersistenceException;

	   @Transactional
	   public void save(String des) throws PersistenceException;
	   
	   public List<TipoItem> loadAll() throws PersistenceException;
}
