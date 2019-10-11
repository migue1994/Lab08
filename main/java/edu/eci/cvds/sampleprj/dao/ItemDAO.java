package edu.eci.cvds.sampleprj.dao;

import java.util.List;

import org.mybatis.guice.transactional.Transactional;

import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;

public interface ItemDAO {

   @Transactional
   public void save(Item it) throws PersistenceException;

   public Item load(int id) throws PersistenceException;
   
   public List<Item> loadAll() throws PersistenceException;

   public List<Item> loadAvailableItems() throws PersistenceException;
   
   public TipoItem loadTipoItem(int id) throws PersistenceException;
   
   public long consultarCostoAlquiler(int iditem, int numdias) throws PersistenceException;
   
   @Transactional
   public void actualizarTarifa(int id, long tarifa) throws PersistenceException;

   @Transactional
   public void addItem(Item i) throws PersistenceException;


}

