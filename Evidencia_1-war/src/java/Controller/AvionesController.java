package Controller;

import Entity.Aviones;
import Facade.AvionesFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

@Named(value = "avionesController")
@SessionScoped

public class AvionesController implements Serializable {

    @EJB
    private AvionesFacade avionesFacade;
    private Aviones aviones = new Aviones();
    private boolean confirm = false;

    public List<Aviones> findAll() {
        return avionesFacade.findAll();
    }

    public List<Aviones> findAll2() {
        return avionesFacade.findAll2();
    }

    //Con TypedQuery
    public List<Aviones> findNumAvion() {
        return avionesFacade.findByNumAvion("2");
    }

    public List<Aviones> findByAerolinea() {
        return avionesFacade.findByAerolinea("Aerobus");
    }

    //Por medio de NamedQuery
    public List<Aviones> findByIdNQ() {
        return avionesFacade.findByIdNQ(1L);
    }

    public List<Aviones> findByNumAndCapNQ() {
        return avionesFacade.findByNumAndCapNQ("2", 12);
    }

    public List<Aviones> findByModelo() {
        return avionesFacade.findByModelo("Z-34");
    }

    /**
     * @return the aviones
     */
    public Aviones getAviones() {
        return aviones;
    }

    /**
     * @param aviones the aviones to set
     */
    public void setAviones(Aviones aviones) {
        this.aviones = aviones;
    }

    public String insert() {
        FacesMessage msj;
        try {
            if (avionesFacade.buscarNumeroAvion(aviones.getNumeroAvion()).isEmpty()) {
                avionesFacade.insert(aviones);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro " + aviones.getNumeroAvion() + " fue agregado exitosamente", "");
                FacesContext.getCurrentInstance().addMessage("avionesAlta", msj);
                clean();
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de " + aviones.getNumeroAvion() + " no se pudo agregar, porque ya existe el Numero de Avion " + aviones.getNumeroAvion(), "");
                FacesContext.getCurrentInstance().addMessage("avionesList", msj);
            }

        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de " + aviones.getNumeroAvion() + " no se pudo agregar, contacte con servicio a cliente", "");
            FacesContext.getCurrentInstance().addMessage("avionesList", msj);
        }
        return "avionesAlta";
    }

    public String preparedEdit(Aviones a) {
        aviones = a;
        return "avionesEdit";
    }

    public void clean() {
        aviones = new Aviones();
    }

    public String prepareDelete() {

        setConfirm(true);
        return "avionesList";
    }

    public void delete(Aviones a) {
        FacesMessage msj;
        try {
            if (a.getVuelosList().isEmpty()) {
                aviones = a;
                avionesFacade.delete(aviones);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro " + a.getNumeroAvion() + " se elimino correctamente", "");
                FacesContext.getCurrentInstance().addMessage("avionesList", msj);
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de " + a.getNumeroAvion() + " no se pudo eliminar, porque, tiene hijos dependientes", "");
                FacesContext.getCurrentInstance().addMessage("avionesList", msj);
            }
        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de " + a.getNumeroAvion() + " no se pudo eliminar, contacte con servicio a cliente", "");
            FacesContext.getCurrentInstance().addMessage("avionesList", msj);
        }
        minClean("avionesList");
    }

    public String update() {
        FacesMessage msj;
        try {

            if (avionesFacade.buscarNumeroAvion(aviones.getNumeroAvion()).isEmpty()) {
                avionesFacade.update(aviones);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro " + aviones.getNumeroAvion() + " fue actualizado exitosamente", "");
                FacesContext.getCurrentInstance().addMessage("avionesAlta", msj);
                clean();
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de " + aviones.getNumeroAvion() + " no se pudo actualizar, porque ya existe el Numero de Avion " + aviones.getNumeroAvion(), "");
                FacesContext.getCurrentInstance().addMessage("avionesList", msj);
            }

        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de " + aviones.getNumeroAvion() + " no se pudo actualizar, contacte con servicio a cliente", "");
            FacesContext.getCurrentInstance().addMessage("avionesList", msj);
        }
        return "";
    }

    public String minClean(String uri) {
        aviones = new Aviones();
        setConfirm(false);
        return uri;
    }

    public Aviones find(Long id) {
        return avionesFacade.find(id);
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
