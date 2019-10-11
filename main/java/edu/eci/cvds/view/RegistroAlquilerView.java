package edu.eci.cvds.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;

import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

public class RegistroAlquilerView extends BasePageBean {

	private static final long serialVersionUID = 1L;
	private ServiciosAlquiler servicioAlquiler;
	private Long cDoc;
	private Date fechainicio, fechafin;
	private Item item;
	private int numdias, id_item;
	private List<ItemRentado> itemsRentados;
	private ServiciosAlquiler serviciosAlquiler;
	
	
	
	public RegistroAlquilerView() {
	}
	
	
	@PostConstruct
	public void init() {
		this.cDoc=(long)123456;
		itemsRentados=new ArrayList<ItemRentado>();
		this.serviciosAlquiler=super.getServiciosAlquiler();
		System.out.println("-------------nulo-----------------");
		System.out.println(this.serviciosAlquiler==null);
		try {
			
			System.out.println("no llega 1");
			System.out.println("-------------------------------------");
			actionCargarListaRentados();
			System.out.println("no llega 2");
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("here error");
			System.out.println(this.getCDoc());
		}
	}

	public void actionCargarListaRentados() {
		try {
			System.out.println("here error 3---------------------------------------");
			System.out.println(this.getCDoc());
			System.out.println(serviciosAlquiler.consultarClientes());
			System.out.println("noooooooooooooooo");
			List<ItemRentado> lr=this.servicioAlquiler.consultarItemsCliente(this.cDoc);
			System.out.println("noooooooooooooooo nulllllll");

			System.out.println(lr==null);
			setItemsRentados(lr);
		} 
		catch (ExcepcionServiciosAlquiler e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void actionListar() {
		try{
			this.itemsRentados=this.servicioAlquiler.consultarItemsCliente(this.cDoc);
		}
		catch(ExcepcionServiciosAlquiler e){
			e.printStackTrace();
		}
	}

	public void actionConsultarCosto(){
		try{
			this.servicioAlquiler.consultarCostoAlquiler(this.id_item, this.numdias);
		}
		catch(ExcepcionServiciosAlquiler e){
			e.printStackTrace();
		}
	}

	public void actionGuardar() {
		
	}

	public void borrarGuardar() {
		
	}
	
	
	public void setCdoc(long cDoc){
		this.cDoc = cDoc;
	}

	public void setFechaInicio(Date fechainicio){
		this.fechainicio=fechainicio;
	}

	public void setFechaFin(Date fechafin){
		this.fechafin=fechafin;
	}

	public void setNumDias(int numdias){
		this.numdias=numdias;
	}

	public void setId_Item(int id_item){
		this.id_item=id_item;
	}

	public long getCDoc(){
		return this.cDoc;
	}

	
	
	public Date getFechaInicio(){
		return this.fechainicio;
	}

	public Date getFechaFin(){
		return this.fechafin;
	}

	public int getNumDias(){
		return this.numdias;
	}

	public int getId_Item(){
		return this.id_item;
	}

	public List<ItemRentado> getItemsRentados(){
		return this.itemsRentados;
	}
	
	public void setItemsRentados(List<ItemRentado> ir){
		this.itemsRentados= ir;
	}
}
