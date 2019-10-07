package edu.eci.cvds.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import com.google.inject.Inject;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;

@ManagedBean(name = "alquilerItemsBean")
@SessionScoped
public class RegistroClienteView{

	private static final long serialVersionUID = 1L;
	private ServiciosAlquiler serviciosAlquiler;
	private String nC;
	private long docC;
	private String telC;
	private String dirC;
	private String emC;
	private List<Cliente> cs;
	
	@Inject
	BasePageBean basePageBean;

	public RegistroClienteView() {
		serviciosAlquiler=ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
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
	
	public void actionListarClientes() {
		try {
			this.cs=serviciosAlquiler.consultarClientes();
		} 
		catch (ExcepcionServiciosAlquiler e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void actionGuardarCliente() {
		try {
			Cliente c=new Cliente(this.nC,this.docC,this.telC,this.dirC,this.emC);
			serviciosAlquiler.registrarCliente(c);
		} catch (ExcepcionServiciosAlquiler e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public String getTelC() {
		return this.telC;
	}

	public void setTelC(String tel) {
		this.telC=tel;
	}
	
	public String getdirC() {
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
	

}
