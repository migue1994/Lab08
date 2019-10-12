package edu.eci.cvds.view;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import com.google.inject.Injector;

import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.TipoItemDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBatisClienteDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBatisItemDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBatisTipoItemDAO;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.impl.ServiciosAlquilerItemsStub;

@ManagedBean(name="AlquilerItemsBean")
@SessionScoped
public class BasePageBean implements Serializable {

 	private static final long serialVersionUID = 1L;
 	
	private Injector injector;
	protected long docAlq;
	
    private Injector getInjector() {
        if (injector == null) {
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
                    .getContext();
            injector = (Injector) servletContext.getAttribute(Injector.class.getName());
        }
        return injector; 
    }

    protected ClienteDAO getClientDao() {
    	return getInjector().getInstance(ClienteDAO.class);
    }
    
    protected TipoItemDAO getTipoItemDAO() {
    	return getInjector().getInstance(TipoItemDAO.class);
    }

    protected ItemDAO getItemDAO() {
    	return getInjector().getInstance(ItemDAO.class);
    }

    protected ServiciosAlquiler getServiciosAlquiler() {
    	return getInjector().getInstance(ServiciosAlquiler.class);
    }
    
    public String page1(){
		return "registrocliente";
	}
	
	public String page2(){
		return "registroalquiler";
	}
    
    public long getDocAlq() {
		return this.docAlq;
	}
    
    public void setDocAlq(long a) {
		this.docAlq=a;
	}
}