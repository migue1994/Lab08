package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.ItemRentadoDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemRentadoMapper;

public class MyBatisItemRentadoDAO implements ItemRentadoDAO {
	
	@Inject
	private ItemRentadoMapper itemRentadoMapper;  

}
