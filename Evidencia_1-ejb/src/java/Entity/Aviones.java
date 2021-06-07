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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Cristian
 */
@Entity
@Table(name = "Aviones", schema = "CSB2021")
@NamedQueries({
    @NamedQuery(name = "findAviones", query = "SELECT A FROM Aviones A"),
    @NamedQuery(name = "findByIdNQ", query="SELECT A FROM Aviones A WHERE A.id=:id"),
    @NamedQuery(name = "findByNumAndCapNQ", query = "SELECT A FROM Aviones A WHERE A.numeroAvion=:numeroAvion AND A.capacidadPasajeros=:capacidadPasajeros"),
    @NamedQuery(name = "findByModelo", query = "SELECT A FROM Aviones A WHERE A.modelo=:modelo")   
})

public class Aviones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NumeroAvion", length = 20, nullable = false)
    private String numeroAvion;

    @Column(name = "CapacidadPasajaeros", nullable = false)
    private int capacidadPasajeros;

    @Column(name = "Modelo", length = 25, nullable = false)
    private String modelo;

    @Column(name = "Aerolinea", length = 35, nullable = false)
    private String aerolinea;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "NumeroAvionV")
    private List<Vuelos> vuelosList;

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
        if (!(object instanceof Aviones)) {
            return false;
        }
        Aviones other = (Aviones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Aviones: \n" + " Id: " + id + "\n"
                + " Numero de Avion: " + getNumeroAvion()
                + " Capacidad de Pasajaeros: " + getCapacidadPasajeros()
                + " Modelo: "+ getModelo()
                + " Aerolinea: "+ getAerolinea();
    }

    /**
     * @return the numeroAvion
     */
    public String getNumeroAvion() {
        return numeroAvion;
    }

    /**
     * @param numeroAvion the numeroAvion to set
     */
    public void setNumeroAvion(String numeroAvion) {
        this.numeroAvion = numeroAvion;
    }

    /**
     * @return the capacidadPasajeros
     */
    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    /**
     * @param capacidadPasajeros the capacidadPasajeros to set
     */
    public void setCapacidadPasajeros(int capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the aerolinea
     */
    public String getAerolinea() {
        return aerolinea;
    }

    /**
     * @param aerolinea the aerolinea to set
     */
    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }

    /**
     * @return the vuelosList
     */
    public List<Vuelos> getVuelosList() {
        return vuelosList;
    }

    /**
     * @param vuelosList the vuelosList to set
     */
    public void setVuelosList(List<Vuelos> vuelosList) {
        this.vuelosList = vuelosList;
    }

}
