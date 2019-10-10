/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.cvds.samples.services.client;



import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.TipoItemMapper;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.TipoItem;

/**
 *
 * @author hcadavid
 */
public class MyBatisExample {

    /**
     * Método que construye una fábrica de sesiones de MyBatis a partir del
     * archivo de configuración ubicado en src/main/resources
     *
     * @return instancia de SQLSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream=null;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);                
            } 
          
            
           catch (IOException e) {
                throw new RuntimeException(e.getCause());
           }
        }
        return sqlSessionFactory;
    }

    /**
     * Programa principal de ejempo de uso de MyBATIS
     * @param args
     * @throws SQLException 
     */
    public static void main(String args[]) throws SQLException {
    	SqlSessionFactory sessionfact = getSqlSessionFactory();
    	SqlSession sqlss = sessionfact.openSession();
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInicio = "1/10/2019";
        String dateFin = "2/10/2019";
        
    	
    	ClienteMapper cm=sqlss.getMapper(ClienteMapper.class);
    	//Cliente c=new Cliente("natalia",12345,"54321","cll", "n@gmail.com");
    	//cm.insertarCliente(c);
    	//cm.actualizarClienteVetado(2148825, true);
    	//System.out.println(cm.consultarItemsCliente(2148825));
    
    	/*System.out.println(cm.consultarClientes());
    	System.out.println(cm.consultarCliente(2107356));*/
    	/*try {
    		Date dateI = formatter.parse(dateInicio);
    		Date dateF = formatter.parse(dateFin);
    		cm.agregarItemRentadoACliente(1024, 2148825,5,dateI,dateF);
    	}
    	
    	catch (ParseException e) {
            e.printStackTrace();
        }*/
    	
    	ItemMapper itm=sqlss.getMapper(ItemMapper.class);
    	//System.out.println(itm.consultarItemsDisponibles());
    	//System.out.println(itm.consultarTipoItem(19));
    	//itm.actualizarTarifa(19, 14785217);
    	
    	/*System.out.println(itm.consultarItems());
    	System.out.println(itm.consultarItem(7));*/
    	
    	/*try {
    		Date dateL = formatter.parse(dateFin);
    		long t=5000;
    		TipoItem tmp=new TipoItem(2,"Accion");
        	Item i=new Item(tmp,214,"Coleccion", "Coleccion de libros",dateL,5000,"Alquilado", "Todos" );
        	itm.insertarItem(i);
    	}
    	
    	catch (ParseException e) {
            e.printStackTrace();
        }*/
    	

    	/*TipoItemMapper titm=sqlss.getMapper(TipoItemMapper.class);
    	System.out.println(titm.getTiposItems());
    	System.out.println(titm.getTipoItem(1));*/
    	/*titm.addTipoItem("Peliculas");*/

        
     
        sqlss.commit();
        
        
        sqlss.close();

        
        
    }


}
