package edu.eci.cvds.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import com.google.inject.Inject;

import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;

@ManagedBean(name="AlquilerBean")
@RequestScoped
public class RegistroAlquilerView{
	
	private static final long serialVersionUID = 1L;
	private List<ItemRentado> listaRentados;
	private ServiciosAlquiler serviciosAlquiler;
	private long docC;
	
	@ManagedProperty(value="#{AlquilerItemsBean}")
	private BasePageBean baseBean;
	
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
			getDocumentoCliente();
			this.listaRentados=serviciosAlquiler.consultarItemsCliente(this.docC);
		}
		catch(ExcepcionServiciosAlquiler e){
			e.printStackTrace();
		}
	}
	
	
	public List<ItemRentado> getListaRentados() {
		return this.listaRentados;
	}
	
	public void setListaRentados(List<ItemRentado> i) {
		this.listaRentados=i;
	}
	
	public void getDocumentoCliente() {
		this.docC=baseBean.getDocAlq();
	}

	public void setBaseBean(BasePageBean bs){
	    this.baseBean = bs;
	}
	   
	public BasePageBean getUsuario() {
		return this.baseBean;
	}
}





