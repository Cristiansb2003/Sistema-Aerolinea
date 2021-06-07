package Controller;

import Entity.Ciudades;
import Entity.Vuelos;
import Facade.VuelosFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

@Named(value = "vuelosController")
@SessionScoped

public class VuelosController implements Serializable {

    @EJB
    private VuelosFacade vuelosFacade;
    private Vuelos vuelos = new Vuelos();
    private boolean confirm = false;

    /**
     * @return the vuelos
     */
    public Vuelos getVuelos() {
        return vuelos;
    }

    /**
     * @param vuelos the vuelos to set
     */
    public void setVuelos(Vuelos vuelos) {
        this.vuelos = vuelos;
    }

    public List<Vuelos> findAll() {
        return vuelosFacade.findAll();
    }

    public List<Vuelos> findAll2() {
        return vuelosFacade.findAll2();
    }

    //Con TypedQuery
    public Vuelos findByNumVuelo() {
        return vuelosFacade.findByNumVuelo("3");
    }

    public List<Vuelos> findByNumAvion() {
        return vuelosFacade.findByNumAvion("Volaris");
    }

    //Con NamedQuery
    public List<Vuelos> findByOrigen() {
        return vuelosFacade.finByOrigenNQ("Monterrey");
    }

    public Vuelos findByIdNQ2() {
        return vuelosFacade.findByIdNQ2(8L);
    }

    public String insert() {
        FacesMessage msj;
        try {
            if (vuelosFacade.buscarNumeroVuelo(vuelos.getNumeroVuelos()).isEmpty()) {
                Ciudades origen = vuelos.getOrigen();
                Ciudades destino = vuelos.getDestino();
                if (!origen.getNombre().equalsIgnoreCase(destino.getNombre())) {
                    if (vuelos.getFechaInicio().before(vuelos.getFechaFin())) {
                        vuelosFacade.insert(vuelos);
                        msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro " + vuelos.getNumeroVuelos() + " fue agregado exitosamentes", "");
                        FacesContext.getCurrentInstance().addMessage("vuelosAlta", msj);
                        clean();
                    } else if (vuelos.getFechaInicio().after(vuelos.getFechaFin())) {
                        msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo agregar, porque la Fecha Fin es anterior a la Fecha Inicio", "");
                        FacesContext.getCurrentInstance().addMessage("vuelosAlta", msj);
                    } else {
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                        String HI = sdf.format(vuelos.getHoraInicio());
                        String HF = sdf.format(vuelos.getHoraFin());

                        LocalTime HoraInicio = LocalTime.parse(HI);
                        LocalTime HoraFin = LocalTime.parse(HF);
                        int diferencia = (int) ChronoUnit.MINUTES.between(HoraInicio, HoraFin);

                        if (diferencia >= 60) {
                            vuelosFacade.insert(vuelos);
                            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro " + vuelos.getNumeroVuelos() + " fue agregado exitosamente", "");
                            FacesContext.getCurrentInstance().addMessage("vuelosAlta", msj);
                            minClean("vuelosAlta");
                            clean();
                        } else {
                            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo agregar, porque Hora de Inicio y Hora fin deben tener una diferencia de 1 hora minimo", "");
                            FacesContext.getCurrentInstance().addMessage("vuelosAlta", msj);
                        }

                    }

                } else {
                    msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo agregar, porque origen y destino son el mismo", "");
                    FacesContext.getCurrentInstance().addMessage("vuelosAlta", msj);
                }

            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo agregar, porque ya existe el numero de vuelo " + vuelos.getNumeroVuelos(), "");
                FacesContext.getCurrentInstance().addMessage("vuelosAlta", msj);
            }

        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo agregar, contacte con el servicio tecnico", "");
            FacesContext.getCurrentInstance().addMessage("vuelosAlta", msj);
        }
        return "vuelosAlta";
    }

    public String preparedEdit(Vuelos v) {
        vuelos = v;
        return "vuelosEdit";
    }

    public String update() {
        FacesMessage msj;
        try {
            if (vuelosFacade.buscarNumeroVuelo(vuelos.getNumeroVuelos()).isEmpty()) {
                Ciudades origen = vuelos.getOrigen();
                Ciudades destino = vuelos.getDestino();
                if (!origen.getNombre().equalsIgnoreCase(destino.getNombre())) {
                    if (vuelos.getFechaInicio().before(vuelos.getFechaFin())) {
                        vuelosFacade.update(vuelos);
                        msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro " + vuelos.getNumeroVuelos() + " fue actualizado exitosamente", "");
                        FacesContext.getCurrentInstance().addMessage("vuelosEdit", msj);
                        clean();
                    } else if (vuelos.getFechaInicio().after(vuelos.getFechaFin())) {
                        msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo actualizar, porque la Fecha Fin es anterior a la Fecha Inicio", "");
                        FacesContext.getCurrentInstance().addMessage("vuelosEdit", msj);
                    } else {
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                        String HI = sdf.format(vuelos.getHoraInicio());
                        String HF = sdf.format(vuelos.getHoraFin());

                        LocalTime HoraInicio = LocalTime.parse(HI);
                        LocalTime HoraFin = LocalTime.parse(HF);
                        int diferencia = (int) ChronoUnit.MINUTES.between(HoraInicio, HoraFin);

                        if (diferencia >= 60) {
                            vuelosFacade.update(vuelos);
                            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro " + vuelos.getNumeroVuelos() + " fue actualizado exitosamente ", "");
                            FacesContext.getCurrentInstance().addMessage("vuelosEdit", msj);
                            clean();
                        } else {
                            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo actualizar, porque Hora de Inicio y Hora fin deben tener una diferencia de 1 hora minimo", "");
                            FacesContext.getCurrentInstance().addMessage("vuelosEdit", msj);
                        }

                    }

                } else {
                    msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo actualizar, porque origen y destino son el mismo", "");
                    FacesContext.getCurrentInstance().addMessage("vuelosEdit", msj);
                }

            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo actualizar, porque ya existe el numero de vuelo " + vuelos.getNumeroVuelos(), "");
                FacesContext.getCurrentInstance().addMessage("vuelosEdit", msj);
            }

        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo actualizar, contacte a servicio tecnico", "");
            FacesContext.getCurrentInstance().addMessage("vuelosEdit", msj);
        }
        return "";
    }

    public String prepareDelete() {

        setConfirm(true);
        return "vuelosList";
    }

    public void delete(Vuelos v) {
        FacesMessage msj;
        try {
            vuelos = v;
            vuelosFacade.delete(vuelos);
            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro " + v.getNumeroVuelos() + " se elimino correctamente", "");
            FacesContext.getCurrentInstance().addMessage("vuelosList", msj);
        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo eliminar el registro " + v.getNumeroVuelos() + ", contacte con servicio tecnico", "");
            FacesContext.getCurrentInstance().addMessage("vuelosList", msj);
        }
        minClean("vuelosList");
    }

    public void clean() {
        vuelos = new Vuelos();
    }

    public String minClean(String uri) {
        vuelos = new Vuelos();
        setConfirm(false);
        return uri;
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
