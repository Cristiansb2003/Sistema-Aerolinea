package Controller;

import Entity.Paises;
import Facade.PaisesFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

@Named(value = "paisesController")
@SessionScoped

public class PaisesController implements Serializable {

    @EJB
    private PaisesFacade paisesFacade;
    private Paises paises = new Paises();
    private boolean confirm = false;

    public List<Paises> findAll() {
        return paisesFacade.findAll();
    }

    public List<Paises> findAll2() {
        return paisesFacade.findAll2();
    }
    //Con TypedQuery

    public List<Paises> findByName() {
        return paisesFacade.findByName("España");
    }

    public Paises finByNameNQ2() {
        return paisesFacade.findByNameNQ2("Mexico");
    }

    /**
     * @return the paises
     */
    public Paises getPaises() {
        return paises;
    }

    /**
     * @param paises the paises to set
     */
    public void setPaises(Paises paises) {
        this.paises = paises;
    }
    //Insert

    public String insert() {
        FacesMessage msj;
        try {
            if (paisesFacade.buscarPaisNombre(paises.getNombre()).isEmpty()) {
                paisesFacade.insert(paises);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro " + paises.getNombre() + " fue agregado exitosamente", "");
                FacesContext.getCurrentInstance().addMessage("PaisesAlta", msj);
                clean();
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de " + paises.getNombre() + "no se puede agregar, porque ya existe", "");
                FacesContext.getCurrentInstance().addMessage("PaisesList", msj);
            }

        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de " + paises.getNombre() + " no se pudo agregar, contacte con servicio a cliente", "");
            FacesContext.getCurrentInstance().addMessage("PaisesList", msj);
        }
        return "PaisesAlta";
    }

    public String preparedEdit(Paises p) {
        paises = p;
        return "paisesEdit";
    }

    public String update() {
        FacesMessage msj;
        try {
            if (paisesFacade.buscarPaisNombre(paises.getNombre()).isEmpty()) {
                paisesFacade.update(paises);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro " + paises.getNombre() + " fue actualizado exitosamente", "");
                FacesContext.getCurrentInstance().addMessage("PaisesAlta", msj);
                clean();
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de " + paises.getNombre() + "no se puedo actualizar, porque ya existe", "");
                FacesContext.getCurrentInstance().addMessage("PaisesList", msj);
            }

        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de " + paises.getNombre() + " no se pudo actualizar, contacte con servicio a cliente", "");
            FacesContext.getCurrentInstance().addMessage("PaisesList", msj);
        }

        return "";
    }

    public void clean() {
        paises = new Paises();
    }

    public String prepareDelete() {
        setConfirm(true);
        return "PaisesList";
    }

    public void delete(Paises p) {
        FacesMessage msj;
        try {
            if (p.getEsatdosList().isEmpty()) {
                paises = p;
                paisesFacade.delete(paises);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro de " + p.getNombre() + " a sido eliminado correctamente", "");
                FacesContext.getCurrentInstance().addMessage("PaisesList", msj);
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de " + p.getNombre() + " no se pudo eliminar, porque, tiene hijos dependientes", "");
                FacesContext.getCurrentInstance().addMessage("PaisesList", msj);
            }

        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de " + p.getNombre() + " no se pudo eliminar, contacte con servicio a cliente", "");
            FacesContext.getCurrentInstance().addMessage("PaisesList", msj);
        }
        minClean("PaisesList");
    }

    public String minClean(String uri) {
        paises = new Paises();
        setConfirm(false);
        return uri;
    }

    public Paises find(Long id) {
        return paisesFacade.find(id);
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
