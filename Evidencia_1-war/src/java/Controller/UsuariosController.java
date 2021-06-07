package Controller;

import Entity.Usuarios;
import Facade.UsuariosFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "UsuariosController")
@SessionScoped
public class UsuariosController implements Serializable {

    @EJB
    private UsuariosFacade usuariosFacade;
    private Usuarios usuarios = new Usuarios();
    private String correo = "";
    private String contra = "";
    private boolean confirm = false;

    public List<Usuarios> findAll() {
        return usuariosFacade.findAll();
    }

    public String iniciarSesion() {
        List<Usuarios> user = usuariosFacade.iniciarSesion(this.correo, this.contra);
        List<Usuarios> perfil = usuariosFacade.perfil(this.correo);
        FacesMessage msj;
        if (user.isEmpty()) {

            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Correo o contraseña incorrecta", "");
            FacesContext.getCurrentInstance().addMessage("index", msj);
            return "index";
        } else {
            if (perfil.isEmpty()) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nombre", user.get(0).getNombre());
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("email", user.get(0).getEmail());

                return "/menu/menuSecundario";

            } else {

                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nombre", user.get(0).getNombre());
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("email", user.get(0).getEmail());
                return "/menu/menuPrincipal";

            }
        }

    }
    //INSERT

    public String insert() {
        FacesMessage msj;
        try {
            usuariosFacade.insert(usuarios);
            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro " + usuarios.getNombre() + " a sido correctamente agregado", "");
            FacesContext.getCurrentInstance().addMessage("usuariosAlta", msj);
            clean();
        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hubo un problema al intentar agregar el registro, contacte con servicio tecnico", "");
            FacesContext.getCurrentInstance().addMessage("usuariosAlta", msj);
        }
        return "usuariosAlta";
    }

    public String update() {
        FacesMessage msj;
        try {
            usuariosFacade.update(getUsuarios());
            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro " + usuarios.getNombre() + " ha sido actualizado correctamente", "");
            FacesContext.getCurrentInstance().addMessage("usuariosEdit", msj);
            clean();
        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro no pudo ser actualizado, contacte con servicio tecnico", "");
            FacesContext.getCurrentInstance().addMessage("usuariosEdit", msj);
        }
        return "usuariosEdit";
    }

    public void delete(Usuarios u) {
        FacesMessage msj;
        try {
            usuarios = u;
            usuariosFacade.delete(usuarios);
            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro " + usuarios.getNombre() + " fue eliminado correctamente", "");
            FacesContext.getCurrentInstance().addMessage("usuariosList", msj);
        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro " + usuarios.getNombre() + " no pudo ser eliminado, contacte con servicio tecnico", "");
            FacesContext.getCurrentInstance().addMessage("usuariosList", msj);
        }
        minClean("usuariosList");

    }

    public String preparedEdit(Usuarios u) {
        setUsuarios(u);
        return "usuariosEdit";
    }

    public String prepareDelete() {
        setConfirm(true);
        return "usuariosList";
    }

    public void clean() {
        setUsuarios(new Usuarios());
    }

    public String minClean(String uri) {
        usuarios = new Usuarios();
        setConfirm(false);
        return uri;
    }

    /**
     * @return the usuarios
     */
    public Usuarios getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
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

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the contra
     */
    public String getContra() {
        return contra;
    }

    /**
     * @param contra the contra to set
     */
    public void setContra(String contra) {
        this.contra = contra;
    }

}
