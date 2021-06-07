/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Cristian
 */
@Entity
@Table(name = "Ciudades", schema = "CSB2021")
@NamedQueries({
    @NamedQuery(name = "findCiudades", query = "SELECT C FROM Ciudades C"),
    @NamedQuery(name = "findByNameNQ", query = "SELECT C FROM Ciudades C WHERE UPPER(C.nombre)=:nombre"),
    @NamedQuery(name = "findByEstado", query = "SELECT C FROM Ciudades C WHERE UPPER(C.estado.nombre)=:nombre"),
    @NamedQuery(name = "findByEstadoYCiudad", query = "SELECT C FROM Ciudades C WHERE C.estado.nombre=:nombre AND C.nombre=:nombre")
})

public class Ciudades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nombre", length = 25, nullable = false)
    private String nombre;

    @JoinColumn(name = "Estado", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Estados estado;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "Origen")
    private List<Vuelos> origenesList;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "Destino")
    private List<Vuelos> destinosList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ciudades)) {
            return false;
        }
        Ciudades other = (Ciudades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ciudades " + " id: " + id + " Nombre: " + nombre + " Estado: " + estado.getNombre();
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the estado
     */
    public Estados getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    /**
     * @return the origenesList
     */
    public List<Vuelos> getOrigenesList() {
        return origenesList;
    }

    /**
     * @param origenesList the origenesList to set
     */
    public void setOrigenesList(List<Vuelos> origenesList) {
        this.origenesList = origenesList;
    }

    /**
     * @return the destinosList
     */
    public List<Vuelos> getDestinosList() {
        return destinosList;
    }

    /**
     * @param destinosList the destinosList to set
     */
    public void setDestinosList(List<Vuelos> destinosList) {
        this.destinosList = destinosList;
    }

}
