package Controller;

import Entity.Ciudades;
import Facade.CiudadesFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "ciudadesController")
@SessionScoped
public class CiudadesController implements Serializable {

    @EJB
    private CiudadesFacade ciudadesFacade;
    private Ciudades ciudades = new Ciudades();
    private boolean confirm = false;

    public List<Ciudades> findAll() {
        return ciudadesFacade.findAll();
    }

    public List<Ciudades> findAll2() {
        return ciudadesFacade.findAll2();
    }

    //Con TypedQuery
    public List<Ciudades> findByNameEstado() {
        return ciudadesFacade.findByNameEstado("Nuevo Leon");
    }

    public List<Ciudades> findByNameT() {
        return ciudadesFacade.findByNameT("Monterrey");
    }

    //Con NamedQuery
    public List<Ciudades> findByNameNQ() {
        return ciudadesFacade.findByNameNQ("Santander");
    }

    public List<Ciudades> findByEstadoNQ() {
        return ciudadesFacade.findByEstado("Cantabria");
    }

    /**
     * @return the ciudades
     */
    public Ciudades getCiudades() {
        return ciudades;
    }

    /**
     * @param ciudades the ciudades to set
     */
    public void setCiudades(Ciudades ciudades) {
        this.ciudades = ciudades;
    }

    public String insert() {
        FacesMessage msj;
        try {
            if (ciudadesFacade.findByNameNQ(ciudades.getNombre()).isEmpty()) {
                ciudadesFacade.insert(ciudades);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro " + ciudades.getNombre() + " se agrego correctamente", "");
                FacesContext.getCurrentInstance().addMessage("ciudadesAlta", msj);
                clean();
            } else if (ciudadesFacade.findByEstado(ciudades.getEstado().getNombre()).isEmpty()) {
                ciudadesFacade.insert(ciudades);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro " + ciudades.getNombre() + " se agrego correctamente", "");
                FacesContext.getCurrentInstance().addMessage("ciudadesAlta", msj);
                clean();
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "no se pudo agregar, porque el Nombre de la Ciudad ya Existe en el Estado " + ciudades.getEstado().getNombre(), "");
                FacesContext.getCurrentInstance().addMessage("ciudadesAlta", msj);
            }

        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "no se pudo agregar, contacte a servicio tecnico", "");
            FacesContext.getCurrentInstance().addMessage("ciudadesAlta", msj);
        }
        return "ciudadesAlta";
    }

    public String preparedEdit(Ciudades c) {
        ciudades = c;
        return "ciudadesEdit";
    }

    public String update() {
        FacesMessage msj;
        try {
            if (ciudadesFacade.findByNameNQ(ciudades.getNombre()).isEmpty()) {
                ciudadesFacade.update(ciudades);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro se actualizo correctamente", "");
                FacesContext.getCurrentInstance().addMessage("ciudadesAlta", msj);
                clean();
            } else if (ciudadesFacade.findByEstado(ciudades.getEstado().getNombre()).isEmpty()) {
                ciudadesFacade.update(ciudades);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro se actualizo correctamente", "");
                FacesContext.getCurrentInstance().addMessage("ciudadesAlta", msj);
                clean();
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "no se pudo actualizar, porque el Nombre de la Ciudad ya Existe en el Estado " + ciudades.getEstado().getNombre(), "");
                FacesContext.getCurrentInstance().addMessage("ciudadesAlta", msj);
            }

        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "no se pudo actualizar, contacte a servicio tecnico", "");
            FacesContext.getCurrentInstance().addMessage("ciudadesAlta", msj);
        }
        return "";
    }

    public String prepareDelete() {
        setConfirm(true);
        return "ciudadesList";
    }

    public void delete(Ciudades c) {
        FacesMessage msj;
        try {

            if (c.getDestinosList().isEmpty()) {
                if (c.getOrigenesList().isEmpty()) {
                    ciudades = c;
                    ciudadesFacade.delete(ciudades);
                    msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro de " + c.getNombre() + " a sido eliminado correctamente", "");
                    FacesContext.getCurrentInstance().addMessage("ciudadesList", msj);
                } else {
                    msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de " + c.getNombre() + " no pudo ser eliminado porque tiene hijos dependientes", "");
                    FacesContext.getCurrentInstance().addMessage("ciudadesLits", msj);
                }

            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de " + c.getNombre() + " no pudo ser eliminado porque tiene hijos dependientes", "");
                FacesContext.getCurrentInstance().addMessage("ciudadesLits", msj);
            }

        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de " + c.getNombre() + " no pudo ser eliminado. contacte con servicio tecnico", "");
            FacesContext.getCurrentInstance().addMessage("ciudadesLits", msj);
        }
        minClean("ciudadesList");
    }

    public void clean() {
        ciudades = new Ciudades();
    }

    public String minClean(String uri) {
        ciudades = new Ciudades();
        setConfirm(false);
        return uri;
    }

    public Ciudades find(Long id) {
        return ciudadesFacade.find(id);
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
