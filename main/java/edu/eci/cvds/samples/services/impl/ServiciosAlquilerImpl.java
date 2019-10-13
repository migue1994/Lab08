package edu.eci.cvds.samples.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.ItemRentadoDAO;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.TipoItemDAO;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;

@Singleton
public class ServiciosAlquilerImpl implements ServiciosAlquiler {

   @Inject
   private ItemDAO itemDAO;

   @Inject
   private ClienteDAO clienteDAO;
   
   @Inject
   private TipoItemDAO tipoItemDAO;
   
   @Inject
   private ItemRentadoDAO itemRentadoDAO;
   
   private final int multaGeneral=10000;
   private final int limiteDias=6;
   
   
   public ServiciosAlquilerImpl() {
   }
   
   
   @Override
   public int valorMultaRetrasoxDia(int itemId) throws ExcepcionServiciosAlquiler {
	   try {
		   Item i=itemDAO.load(itemId);   
		   return this.multaGeneral;   
	   } 
	   catch (PersistenceException e) {
		   throw new ExcepcionServiciosAlquiler("Item no encontrado",e);
	   }
   }
   

   @Override
   public Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler {
	   try {
		   return clienteDAO.load(docu);
	   }
	   catch (PersistenceException ex) {
		   throw new ExcepcionServiciosAlquiler("Error al consultar al cliente "+docu,ex);
	   }
   }

   @Override
   public List<ItemRentado> consultarItemsCliente(long idcliente) throws ExcepcionServiciosAlquiler {
       try {
    	   
    	   List<ItemRentado> r= itemRentadoDAO.loadItemsCliente(idcliente);
    	   if(r.isEmpty()) {
    		   System.out.println("servicios-------------------------");
    		   System.out.println("Es nulo");
    	   }
    	   return r;
       }
       catch (PersistenceException ex) {
    	   throw new ExcepcionServiciosAlquiler("El cliente "+idcliente+" no está registrado");
       }
   }

   @Override
   public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
	   try {
    	   return clienteDAO.loadAll();
       }
       catch (PersistenceException ex) {
    	   throw new ExcepcionServiciosAlquiler("Error al consultar items");
       }
   }


   @Override
   public Item consultarItem(int id) throws ExcepcionServiciosAlquiler {
       try {
           return itemDAO.load(id);
       } 
       catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al consultar el item "+id,ex);
       }
   }

   @Override
   public List<Item> consultarItemsDisponibles() throws ExcepcionServiciosAlquiler{
	   try {
		   return itemDAO.loadAvailableItems();
	   }
	   catch (PersistenceException e) {
		   throw new ExcepcionServiciosAlquiler("Error al consultar los items disponibles",e);
	   }
   }
   
   
   @Override
   public long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler {
	   long multa=0;long dias=0;
	   try {
		   ItemRentado r=itemRentadoDAO.loadItemRentado(iditem);
		   r.setFechafinrenta(fechaDevolucion);
		   Date inicio=r.getFechainiciorenta();
		   Date fin=r.getFechafinrenta();
		   LocalDate prestado = inicio.toLocalDate();
		   LocalDate devuelto = fin.toLocalDate();
		   long diasDeAlquiler= ChronoUnit.DAYS.between(devuelto,prestado);
		   if(diasDeAlquiler>this.limiteDias) {
			   dias=diasDeAlquiler-this.limiteDias;
			   multa=this.multaGeneral*dias;
		   }
	   } 
	   catch (PersistenceException e) {
		   throw new ExcepcionServiciosAlquiler("Error al calcular la multa",e);
	   }
	   return multa;
   }

   
   @Override
   public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler {
	   try {
		   return itemDAO.loadTipoItem(id);
	   }
	   catch (PersistenceException e) {
		   throw new ExcepcionServiciosAlquiler("Error al consultar el tipo de item",e);
	   }
   }

   @Override
   public List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler {
	   try {
		   return tipoItemDAO.loadAll();
	   }
	   catch (PersistenceException e) {
		   throw new ExcepcionServiciosAlquiler("Error al consultar el tipo de item",e);
	   }
   }


   @Override
   public void registrarAlquilerCliente(Date date, long docu, Item item, int numdias) throws ExcepcionServiciosAlquiler {
	   
	   SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	   
	   String fechaInicioS=formatter.format(date);
	   
	   java.util.Date fechaInicio;
	   
	   try {
		   fechaInicio = formatter.parse(fechaInicioS);
		   Calendar calendar=Calendar.getInstance();
		   calendar.setTime(fechaInicio);
		   calendar.add(Calendar.DAY_OF_YEAR, numdias);
		   	
		   java.util.Date fechaFin=calendar.getTime();
		   
		   int id_item=item.getId();
		   itemRentadoDAO.addItemRentado(docu,id_item,fechaInicio, fechaFin);
	   } 
	   
	   catch (PersistenceException ex) {
           throw new ExcepcionServiciosAlquiler("Error al registrar alquiler",ex);
	   }
	   
	   catch (ParseException e) {
			e.printStackTrace();
	   }
   }

   @Override
   public void registrarCliente(Cliente c) throws ExcepcionServiciosAlquiler {
	   try {
		   clienteDAO.addCliente(c);
	   }
	   catch (PersistenceException e) {
		   throw new ExcepcionServiciosAlquiler("Error al registar al cliente",e);
	   }
   }

   @Override
   public long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler {
	   long valor=0;
	   try {
		   int diasExtra=0;
		   if(numdias>this.limiteDias) {
			   diasExtra=numdias-this.limiteDias;
			   valor=itemDAO.consultarCostoAlquiler(iditem,this.limiteDias);
			   valor+=itemDAO.consultarCostoAlquiler(iditem,diasExtra);
		   }
		   else {
			   valor=itemDAO.consultarCostoAlquiler(iditem,numdias);
		   }

	   }
	   catch (PersistenceException e) {
		   throw new ExcepcionServiciosAlquiler("Error al calcular la tarifa",e);
	   }
	   return valor;
   }

   @Override
   public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler {
	   try {
		   itemDAO.actualizarTarifa(id, tarifa);
	   }
	   catch (PersistenceException e) {
		   throw new ExcepcionServiciosAlquiler("Error al actualizar la tarifa",e);
	   }
   }
   @Override
   public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
	   try {
		   itemDAO.addItem(i);
	   }
	   catch (PersistenceException e) {
		   throw new ExcepcionServiciosAlquiler("Error al registar el Item",e);
	   }
   }

   @Override
   public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {
	   try {
		   clienteDAO.setVetado(docu,estado);
	   }
	   catch (PersistenceException e) {
		   throw new ExcepcionServiciosAlquiler("Error al registar el Item",e);
	   }
   }
  

}


