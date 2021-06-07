package Controller;

import Entity.Estados;
import Facade.EstadosFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

@Named(value = "estadoController")
@SessionScoped
public class EstadoController implements Serializable {

    @EJB
    private EstadosFacade estadoFacade;
    private Estados estados = new Estados();
    private boolean confirm = false;

    public List<Estados> findAll() {
        return estadoFacade.findAll();
    }

    public List<Estados> findAll2() {
        return estadoFacade.findAll2();
    }

    //Con TypedQuery
    public List<Estados> findByName() {
        return estadoFacade.findByName("Nuevo Leon");
    }

    public List<Estados> findByPais() {
        return estadoFacade.findByPais("Mexico");
    }

    //Con NamedQuery
    public List<Estados> findByNamedNQ() {
        return estadoFacade.findByNameNQ("Cantabria");
    }

    public List<Estados> findByPaisNQ() {
        return estadoFacade.findByPaisNQ("Mexico");
    }

    /**
     * @return the estados
     */
    public Estados getEstados() {
        return estados;
    }

    /**
     * @param estados the estados to set
     */
    public void setEstados(Estados estados) {
        this.estados = estados;
    }

    public String insert() {
        FacesMessage msj;
        try {

            estadoFacade.insert(estados);
            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro "+estados.getNombre()+" se agrego correctamente", "");
            FacesContext.getCurrentInstance().addMessage("estadosAlta", msj);
            clean();

        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro no se pudo agregar contactar a servicio tecnico", "");
            FacesContext.getCurrentInstance().addMessage("estadosAlta", msj);
        }
        return "estadosAlta";
    }

    public String preparedEdit(Estados e) {
        estados = e;
        return "estadosEdit";
    }

    public String update() {
        FacesMessage msj;
        try {
            estadoFacade.update(estados);
            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro " + estados.getNombre() + " se actualizo correctamente", "");
            FacesContext.getCurrentInstance().addMessage("estadosEdit", msj);
            clean();

        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "no se pudo actualizar el registro " + estados.getNombre() + " contactar a servicio tecnico", "");
            FacesContext.getCurrentInstance().addMessage("estadosEdit", msj);
        }
        return "";
    }

    public void clean() {
        estados = new Estados();
    }

    public String minClean(String uri) {
        estados = new Estados();
        setConfirm(false);
        return uri;
    }

    public String prepareConfirm() {
        setConfirm(true);
        return "estadosList";
    }

    public void delete(Estados es) {
        FacesMessage msj;
        try {
            if (es.getCiudadesList().isEmpty()) {
                estados = es;
                estadoFacade.delete(estados);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro de " + es.getNombre() + " a sido eliminado correctamente", "");
                FacesContext.getCurrentInstance().addMessage("estadosList", msj);
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de " + es.getNombre() + " no pudo ser eliminado porque tiene hijos dependientes", "");
                FacesContext.getCurrentInstance().addMessage("estadosLits", msj);
            }

        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de " + es.getNombre() + " no pudo ser eliminado contacte a servicio tecnico", "");
            FacesContext.getCurrentInstance().addMessage("estadosLits", msj);
        }
        minClean("estadosList");
    }

    //
    public Estados find(Long id) {
        return estadoFacade.find(id);
    }

    /**
     * @return the confirm
     */
    public boolean isConfirm() {
        return confirm;
    }

    /**
     * @param confirm the confirm to set
     */
    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

}
