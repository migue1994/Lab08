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


@ManagedBean(name="RegistroBean")
@RequestScoped
public class RegistroClienteView{
	
	private static final long serialVersionUID = 2L;
	private String nC;
	private long docC,docAux;
	private String telC;
	private String dirC;
	private String emC;
	private List<Cliente> listaClientes;
	private List<ItemRentado> listaRentados;
	private ServiciosAlquiler serviciosAlquiler;
	
	@ManagedProperty(value="#{AlquilerItemsBean}")
	private BasePageBean baseBean;
	
	
	public RegistroClienteView() {
	}

	@PostConstruct
	public void init() {
		serviciosAlquiler=baseBean.getServiciosAlquiler();
		actionSetClientes();
	}

	
	public void actionBuscarCliente() {
		try {
			serviciosAlquiler.consultarCliente(this.docC);
		} 
		catch (ExcepcionServiciosAlquiler e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void actionSetClientes() {
		try {
			setListaClientes(serviciosAlquiler.consultarClientes());
		} 
		catch (ExcepcionServiciosAlquiler e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void actionGuardarCliente() {
		try {
			Cliente c=new Cliente(this.nC,this.docC,this.telC,this.dirC,this.emC);
			this.docAux=this.docC;
			serviciosAlquiler.registrarCliente(c);
			getListaClientes();
		} catch (ExcepcionServiciosAlquiler e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void actionReiniciarValores() {
		this.nC=null;this.docC=0;this.telC=null;this.dirC=null;this.emC=null;
	}
	
	private void organizarClientes(long doc) {
		actionSetClientes();
		int n=listaClientes.size();
		for(int i=0;i<n;i++) {
			Cliente cl=listaClientes.get(i);
			if(cl.getDocumento()==doc) {
				Cliente aux=listaClientes.get(0);
				listaClientes.set(0, cl);
				listaClientes.set(i, aux);
			}
		}
	}
	
	public String getnC() {
		return this.nC;
	}

	public void setnC(String name) {
		this.nC=name;
	}
	
	public long getDocC() {
		return this.docC;
	}

	public void setDocC(long doc) {
		this.docC=doc;
	}
	

	public void setDocAlq(Long a) {
		baseBean.setDocAlq((long)a);
	}
	
	public String getTelC() {
		return this.telC;
	}

	public void setTelC(String tel) {
		this.telC=tel;
	}
	
	public String getDirC() {
		return this.dirC;
	}

	public void setDirC(String dir) {
		this.dirC=dir;
	}
	
	public String getEmC() {
		return this.emC;
	}

	public void setEmC(String em) {
		this.emC=em;
	}

	public List<Cliente> getListaClientes() {
		organizarClientes(this.docAux);
		actionReiniciarValores();

		return this.listaClientes;
	}
	
	public void setListaClientes(List<Cliente> c) {
		this.listaClientes=c;
	}
	
	public List<ItemRentado> getListaRentados() {
		return this.listaRentados;
	}
	
	public void setListaRentados(List<ItemRentado> i) {
		this.listaRentados=i;
	}
	
	public void setBaseBean(BasePageBean bs){
	    this.baseBean = bs;
	}
	   
	public BasePageBean getUsuario() {
		return this.baseBean;
	} 
}
