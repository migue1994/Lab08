package edu.eci.cvds.sampleprj.dao.mybatis;

import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.ItemRentadoDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemRentadoMapper;
import edu.eci.cvds.samples.entities.ItemRentado;

public class MyBatisItemRentadoDAO implements ItemRentadoDAO{

	@Inject
	  private ItemRentadoMapper itemRentadoMapper;
	
	@Override
	public int consultarDiasAlquiler(int itemId) {
		return itemRentadoMapper.consultarDiasAlquiler(itemId);
		
	}
}
