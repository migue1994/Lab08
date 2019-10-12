package edu.eci.cvds.test;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class ServiciosAlquilerTest {

    @Inject
    private SqlSession sqlSession;

    ServiciosAlquiler serviciosAlquiler;
    private long documento=741444449;
    
    public ServiciosAlquilerTest() {
    }

    @Before
    public void setUp() {
    	try {
    		serviciosAlquiler = ServiciosAlquilerFactory.getInstance().getServiciosAlquiler();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}

    }

    @Test
    public void emptyDB() {
        /*for(int i = 0; i < 100; i += 10) {
            boolean r = false;
            try {
                Cliente cliente = serviciosAlquiler.consultarCliente(documento);
            } catch(ExcepcionServiciosAlquiler e) {
                r = true;
            } catch(IndexOutOfBoundsException e) {
                r = true;
            }
            catch(Exception e) {
            	e.printStackTrace();
            }
            // Validate no Client was found;
            Assert.assertTrue(r);
        }*/
    }
}