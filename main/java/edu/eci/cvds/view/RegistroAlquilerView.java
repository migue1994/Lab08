package edu.eci.cvds.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
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
public class RegistroAlquilerView extends BasePageBean{
	
	private static final long serialVersionUID = 1L;
	private List<ItemRentado> listaRentados;
	private ServiciosAlquiler serviciosAlquiler;
	
	public RegistroAlquilerView() {
	}

	@PostConstruct
	public void init() {
		serviciosAlquiler=getServiciosAlquiler();
		actionSetItemRentados();
	}
	
	public void actionSetItemRentados() {
		try{
			this.listaRentados=serviciosAlquiler.consultarItemsCliente(123456);
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
	
}
