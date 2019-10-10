package edu.eci.cvds.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;

import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;

import javax.annotation.PostConstruct;

@ManagedBean(name = "AlquilerItemsBean")
@RequestScoped

public class RegistroAlquilerView extends BasePageBean {

	private ServiciosAlquiler servicioAlquiler;
	private Long cDoc;
	private Date fechainicio, fechafin;
	private Item item;
	private int numdias, id_item;
	private List<ItemRentado> itemsRentados;
	
	/**
	 * 
	 */
	
	public RegistroAlquilerView() {
		//super.getInjector()
	}

	public void init() {
		serviciosAlquiler=getServiciosAlquiler();
		setListaRentados(servicioAlquiler.consultarItemsCliente(this.cDoc));
	}

	public void actionListar() {
		try{
			servicioAlquiler.consultarItemsCliente(this.cDoc);
		}
		catch(ExcepcionServiciosAlquiler e){
			e.printStackTrace();
		}
	}

	public void actionConsultarCosto(){
		try{
			servicioAlquiler.consultarCostoAlquiler(this.id_item, this.numdias);
		}
		catch(ExcepcionServiciosAlquiler e){
			e.printStackTrace();
		}
	}

	public void actionGuardar() {
		
	}

	public void borrarGuardar() {
		
	}

	public void setCdoc(Long cDoc){
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

	public Long getCDoc(){
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

	public void setListaRentados(List<ItemRentado> ir){
		this.itemsRentados= ir;
	}
}
