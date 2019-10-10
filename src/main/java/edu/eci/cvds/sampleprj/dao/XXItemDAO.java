package edu.eci.cvds.sampleprj.dao;

import java.util.List;

import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;

public class XXItemDAO implements ItemDAO {

	@Override
	public void save(Item it) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Item load(int id) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> loadAll() throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> loadAvailableItems() throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoItem loadTipoItem(int id) throws PersistenceException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public long consultarCostoAlquiler(int iditem, int numdias) throws PersistenceException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void actualizarTarifa(int id, long tarifa) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addItem(Item i) throws PersistenceException {
		// TODO Auto-generated method stub
		
	}

}
