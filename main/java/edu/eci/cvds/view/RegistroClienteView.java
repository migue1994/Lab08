package edu.eci.cvds.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.google.inject.Inject;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;

@ManagedBean(name = "alquilerItemsBean")
@RequestScoped
public class RegistroClienteView{

	private static final long serialVersionUID = 1L;
	private ServiciosAlquiler serviciosAlquiler;
	private String nombre;
	private long documento;
	private String telefono;
	private String direccion;
	private String email;
	private List<Cliente> clientes;
	
	@Inject
	BasePageBean basePageBean;

	
	public RegistroClienteView() {
		serviciosAlquiler=basePageBean.getInjector().getInstance(ServiciosAlquiler.class);
	}

	public void actionBuscarCliente() {
		try {
			serviciosAlquiler.consultarCliente(this.documento);
		} 
		catch (ExcepcionServiciosAlquiler e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void actionListarClientes() {
		try {
			this.clientes=serviciosAlquiler.consultarClientes();
		} 
		catch (ExcepcionServiciosAlquiler e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void actionGuardarCliente() {
		try {
			Cliente c=new Cliente(this.nombre,this.documento,this.telefono,this.direccion,this.email);
			serviciosAlquiler.registrarCliente(c);
		} catch (ExcepcionServiciosAlquiler e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void actionBorrarCliente() {
		
	}

	

}
