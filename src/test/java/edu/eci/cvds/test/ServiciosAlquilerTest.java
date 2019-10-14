package edu.eci.cvds.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
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
    private long doc;
    
    public ServiciosAlquilerTest() {
    }

    @Before
    public void setUp() {
    	try {
    		serviciosAlquiler = ServiciosAlquilerFactory.getInstance().getServiciosAlquiler();
    		this.doc=1026585664;
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}

    }

    @Test
    public void fullDB() {
        for(int i = 0; i < 100; i += 10) {
            boolean r = true;
            try {
                Cliente cliente = serviciosAlquiler.consultarCliente(doc);
            } catch(ExcepcionServiciosAlquiler e) {
                r = false;
            } catch(IndexOutOfBoundsException e) {
                r = false;
            }
            
            Assert.assertTrue(r);
        }
    }
    
    @Test
    public void consultarClienteExistente() {
    	
    	boolean r = true;
        try {
        	Cliente cliente = serviciosAlquiler.consultarCliente(doc);
        } 
        catch(Exception e) {
        	r=false;
        }
        Assert.assertTrue(r);
    }
    @Test
    public void consultarClienteNoExistente() {
    	long docN=2148825;
    	boolean r=false;
        try {
        	Cliente cliente = serviciosAlquiler.consultarCliente(docN);
        } 
        catch(ExcepcionServiciosAlquiler e) {
        	r=true;
        }
        Assert.assertTrue(r);
    }
    @Test
    public void consultarTodosLosClientes() {
    	boolean r=true;
        try {
        	if(serviciosAlquiler.consultarClientes().isEmpty()) {
        		r=false;
        	}
        } 
        catch(ExcepcionServiciosAlquiler e) {
        	r=false;
        }
        Assert.assertTrue(r); 
    }
    
    @Test
    public void registrarClienteNoExistente() {
    	String nombre="Natalia";
    	long docu=1002478555;
        String telefono="47849891100";
        String direccion="calle 34455 cr 767";
        String email="natalia@gmail.com";
    	boolean r=true;
        try {
        	Cliente c=new Cliente(nombre,docu,telefono,direccion,email);
        	serviciosAlquiler.registrarCliente(c);
        } 
        catch(Exception e) {
        	e.printStackTrace();
        	r=false;
        }
        Assert.assertTrue(r); 
    }

    @Test
    public void registrarClienteExistente() {
    	String nombre="Julio";
        String telefono="4777444";
        String direccion="cale 12762";
        String email="julio@gmail.com";
    	boolean r=false;
        try {
        	Cliente c=new Cliente(nombre,this.doc,telefono,direccion,email);
        	serviciosAlquiler.registrarCliente(c);
        } 
        catch(ExcepcionServiciosAlquiler e) {
        	r=true;
        }
        Assert.assertTrue(r); 
    }
    
    @Test
    public void vetarClienteExistente() {
    	boolean estado=true;
    	boolean r=true;
        try {
        	serviciosAlquiler.vetarCliente(this.doc, estado);
        } 
        catch(ExcepcionServiciosAlquiler e) {
        	r=false;
        }
        Assert.assertTrue(r); 
    }
    
    @Test
    public void vetarClienteNoExistente() {
    	long docu=744555989;
    	boolean estado=true;
    	boolean r=false;
        try {
        	serviciosAlquiler.vetarCliente(docu, estado);
        } 
        catch(ExcepcionServiciosAlquiler e) {
        	r=true;
        }
        Assert.assertTrue(r); 
    }
    
    @Test
    public void consultarItemExistente() {
    	int id=1;
    	String nombre="It: Capítulo dos";
    	String descripcion="Película basada en un libro de Stephen King";
    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    	Date fechaLanzamiento;
    	long tarifaxDia=5500;
    	String formatoRenta="DVD";
    	String genero="	Terror y suspenso";
    	boolean r=true;
        try {
        	fechaLanzamiento=formatter.parse("05/09/2019");
        	TipoItem it=new TipoItem(4,"Película");
        	Item item=new Item(it,id,nombre,descripcion,fechaLanzamiento,tarifaxDia,formatoRenta,genero);
        	serviciosAlquiler.registrarItem(item);
        	Item i=serviciosAlquiler.consultarItem(id);
        } 
        catch(Exception e) {
        	e.printStackTrace();
        	r=false;
        }
        Assert.assertTrue(r); 
    }
    
    @Test
    public void consultarItemNoExistente() {
    	int id=14777;
    	boolean r=false;
        try {
        	Item i=serviciosAlquiler.consultarItem(id);
        } 
        catch(ExcepcionServiciosAlquiler e) {
        	r=true;
        }
        Assert.assertTrue(r); 
    }
    
    @Test
    public void consultarItemsDisponibles() {
    	List<Item> itd;
    	boolean r=true;
		try {
			itd = serviciosAlquiler.consultarItemsDisponibles();
	        Assert.assertFalse(itd.isEmpty());
		} 
		catch (Exception e) {
			r=false;
		}
		Assert.assertTrue(r); 
    }
    
    @Test
    public void registrarItemNoExistente() {
    	int id=7;
    	String nombre="La fiesta del chivo";
    	String descripcion="Libro de Mario Vargas Llosa";
    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    	Date fechaLanzamiento;
    	long tarifaxDia=2112;
    	String formatoRenta="Libro";
    	String genero="Política";
    	boolean r=true;
        try {
        	fechaLanzamiento=formatter.parse("08/10/2000");
        	TipoItem it=new TipoItem(5,"Libro");
        	Item item=new Item(it,id,nombre,descripcion,fechaLanzamiento,tarifaxDia,formatoRenta,genero);
        	serviciosAlquiler.registrarItem(item);
        } 
        catch(Exception e) {
        	e.printStackTrace();
        	r=false;
        }
        Assert.assertTrue(r); 
    }
    
    @Test
    public void registrarItemExistente() {
    	int id=7;
    	String nombre="La fiesta del chivo";
    	String descripcion="Libro de Mario Vargas Llosa";
    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    	Date fechaLanzamiento;
    	long tarifaxDia=2112;
    	String formatoRenta="Libro";
    	String genero="Política";
    	boolean r=false;
        try {
        	fechaLanzamiento=formatter.parse("08/10/2000");
        	TipoItem it=new TipoItem(5,"Libro");
        	Item item=new Item(it,id,nombre,descripcion,fechaLanzamiento,tarifaxDia,formatoRenta,genero);
        	serviciosAlquiler.registrarItem(item);
        	serviciosAlquiler.registrarItem(item);
        } 
        catch(ExcepcionServiciosAlquiler | ParseException e) {
        	r=true;
        }
        Assert.assertTrue(r); 
    }
    
    
    @Test
    public void actualizarTarifaItemExistente() {
    	int id=2;
    	String nombre="X-men: Primera generación";
    	String descripcion="Película de mutantes";
    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    	Date fechaLanzamiento;
    	long tarifaxDia=5000;
    	String formatoRenta="DVD";
    	String genero="Ficción";
    	boolean r=true;
        try {
        	fechaLanzamiento=formatter.parse("25/05/2011");
        	TipoItem it=new TipoItem(4,"Película");
        	Item item=new Item(it,id,nombre,descripcion,fechaLanzamiento,tarifaxDia,formatoRenta,genero);
        	serviciosAlquiler.registrarItem(item);
        	serviciosAlquiler.actualizarTarifaItem(id, 4000);
        	Assert.assertEquals(4000, item.getTarifaxDia());
        } 
        catch(Exception e) {
        	e.printStackTrace();
        	r=false;
        }
        Assert.assertTrue(r); 
    }
    
    @Test
    public void actualizarTarifaItemNoExistente() {
        int id=90;
        boolean r=false;
    	try {
        	serviciosAlquiler.actualizarTarifaItem(id, 4000);
        } 
        catch(ExcepcionServiciosAlquiler e) {
        	r=true;
        }
        Assert.assertTrue(r); 
    }
    
    @Test
    public void consultarTipoItemExistente() {
    	boolean r=true;
        try {
        	TipoItem it=serviciosAlquiler.consultarTipoItem(1);
        	Assert.assertFalse(it==null);
        } 
        catch(Exception e) {
        	r=false;
        }
        Assert.assertTrue(r); 
    }
    
    @Test
    public void consultarTipoItemNoExistente() {
    	boolean r=false;
        try {
        	TipoItem it=serviciosAlquiler.consultarTipoItem(50);
        } 
        catch(ExcepcionServiciosAlquiler e) {
        	r=true;
        }
        Assert.assertTrue(r); 
    }
    
    @Test
    public void consultarTiposItem() {
    	List<TipoItem> tis;
    	boolean r=true;
		try {
			tis = serviciosAlquiler.consultarTiposItem();
	        Assert.assertTrue(tis.size()>=3);
		} 
		catch (Exception e) {
			r=false;
		}
		Assert.assertTrue(r); 
    }
    
    @Test
    public void registrarAlquilerClienteExistente() {
    	Date fecha;
    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    	boolean r=true;
        try {
        	Item i=serviciosAlquiler.consultarItem(6);
        	fecha=formatter.parse("01/10/2019");
        	java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
        	serviciosAlquiler.registrarAlquilerCliente(sqlDate, this.doc, i, 5);
        } 
        catch(Exception e) {
        	r=false;
        }
        Assert.assertTrue(r); 
    }
    
    @Test
    public void registrarAlquilerClienteNoExistente() {
    	long doc=123455643;
    	Date fecha;
    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    	boolean r=false;
        try {
        	Item i=serviciosAlquiler.consultarItem(6);
        	fecha=formatter.parse("01/10/2019");
        	java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
        	serviciosAlquiler.registrarAlquilerCliente(sqlDate, doc, i, 5);
        } 
        catch(ExcepcionServiciosAlquiler | ParseException e) {
        	r=true;
        }
        Assert.assertTrue(r); 
    }
    
    @Test
    public void consultarCostoAlquiler() {
    	int dias=5;
    	boolean r=true;
        try {
        	Item i=serviciosAlquiler.consultarItem(6);
        	long total=i.getTarifaxDia()*dias;
        	Assert.assertEquals(total,serviciosAlquiler.consultarCostoAlquiler(6,dias));        	
        } 
        catch(Exception e) {
        	r=false;
        }
        Assert.assertTrue(r);  
    }
}

















