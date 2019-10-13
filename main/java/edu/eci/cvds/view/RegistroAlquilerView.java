package edu.eci.cvds.view;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;

@ManagedBean(name = "AlquilerBean")
@RequestScoped
public class RegistroAlquilerView {

	private static final long serialVersionUID = 1L;
	private List<ItemRentado> listaRentados;
	private ServiciosAlquiler serviciosAlquiler;

	@ManagedProperty(value = "#{AlquilerItemsBean}")
	private BasePageBean baseBean;
	private long multa;
	private int numdias;
	private int idItem;
	private long costo;
	private long docC;
	
	public RegistroAlquilerView() {
	}

	@PostConstruct
	public void init() {
		serviciosAlquiler=baseBean.getServiciosAlquiler();
		getDocumentoCliente();
		actionSetItemRentados();
	}
	
	public void actionSetItemRentados() {
		try{
			this.listaRentados=serviciosAlquiler.consultarItemsCliente(this.docC);
		}
		catch(ExcepcionServiciosAlquiler e){
			e.printStackTrace();
		}
	}
	
	public void actionSetMulta(int iditem, Date fechaDevolucion){
		try {
			setMulta(serviciosAlquiler.consultarMultaAlquiler(iditem,fechaDevolucion));
		} catch (ExcepcionServiciosAlquiler e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public long actionGetMulta(int iditem, Date fechaDevolucion) {
		actionSetMulta(iditem,fechaDevolucion);
		return getMulta();
	}
	
	public void actionSetCosto() {
		try {
			setCosto(serviciosAlquiler.consultarCostoAlquiler(idItem, numdias));
		}
		catch (ExcepcionServiciosAlquiler e) {
			e.printStackTrace();
		}
	}

	public void registrarAlquilerCliente() throws ExcepcionServiciosAlquiler {
		try{
			Item item = serviciosAlquiler.consultarItem(this.idItem);
			List<Item> disponibles=serviciosAlquiler.consultarItemsDisponibles();
			
			java.util.Date fechaInicio=new java.util.Date();
			
			java.sql.Date fechaInicioSQL=new java.sql.Date(fechaInicio.getTime());
			
			System.out.println(this.docC);
			if(!disponibles.contains(item)) {
				serviciosAlquiler.registrarAlquilerCliente(fechaInicioSQL,this.docC, item, this.numdias);
			}
			
		}
		catch(ExcepcionServiciosAlquiler e){
			e.printStackTrace();
		}
	}
	

	public List<ItemRentado> getListaRentados() {
		actionSetItemRentados();
		return this.listaRentados;
	}
	
	public void setListaRentados(List<ItemRentado> i) {
		this.listaRentados=i;
	}
	
	public void getDocumentoCliente() {
		this.docC=baseBean.getDocAlq();
	}
	
	public long getMulta() {
		return this.multa;
	}
	
	public void setMulta(long mult){
	    this.multa=mult;
	}
	
	public long getCosto() {
		return this.costo;
	}

	public void setCosto(long costo) {
		this.costo = costo;
	}
	
	public int getIdItem() {
		return this.idItem;
	}

	public void setIdItem(int i) {
		this.idItem=i;
	}
	
	public int getNumdias() {
		return this.numdias;
	}

	public void setNumdias(int n) {
		this.numdias=n;
	}
	
	public BasePageBean getUsuario() {
		return this.baseBean;
	}
	
	public void setBaseBean(BasePageBean bs){
	    this.baseBean = bs;
	}
}





