/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Cristian
 */
@Entity
@Table(name = "Vuelos", schema = "CSB2021")
@NamedQueries({
    @NamedQuery(name = "findVuelos", query = "SELECT V FROM Vuelos V"),
    @NamedQuery(name = "finByOrigenNQ", query = "SELECT V FROM Vuelos V WHERE V.origen.nombre=:nombre"),
    @NamedQuery(name = "findByIdNQ2", query = "SELECT V FROM Vuelos V WHERE V.id=:id")
})
public class Vuelos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NumeroVuelos", length = 20, nullable = false)
    private String numeroVuelos;

    @Column(name = "NumeroPasajeros")
    private int numeroPasajeros;

    @Column(name = "FechaInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Column(name = "FechaFin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @Column(name = "HoraInicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;

    @Column(name = "HoraFin")
    @Temporal(TemporalType.TIME)
    private Date horaFin;

    @JoinColumn(name = "NumeroAvionV", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Aviones numeroAvionV;

    @JoinColumn(name = "Origen", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Ciudades origen;

    @JoinColumn(name = "Destino", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Ciudades destino;

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
        if (!(object instanceof Vuelos)) {
            return false;
        }
        Vuelos other = (Vuelos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vuelo\n\n" + " id: " + id + " Numero de vuelo: " + numeroVuelos
                + " Numero de Avion: " + numeroAvionV.getAerolinea() + " Origen: " + origen.getNombre() + " Destino: " + destino.getNombre();
    }

    /**
     * @return the numeroVuelos
     */
    public String getNumeroVuelos() {
        return numeroVuelos;
    }

    /**
     * @param numeroVuelos the numeroVuelos to set
     */
    public void setNumeroVuelos(String numeroVuelos) {
        this.numeroVuelos = numeroVuelos;
    }

    /**
     * @return the numeroPasajeros
     */
    public int getNumeroPasajeros() {
        return numeroPasajeros;
    }

    /**
     * @param numeroPasajeros the numeroPasajeros to set
     */
    public void setNumeroPasajeros(int numeroPasajeros) {
        this.numeroPasajeros = numeroPasajeros;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the horaInicio
     */
    public Date getHoraInicio() {
        return horaInicio;
    }

    /**
     * @param horaInicio the horaInicio to set
     */
    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * @return the horaFin
     */
    public Date getHoraFin() {
        return horaFin;
    }

    /**
     * @param horaFin the horaFin to set
     */
    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public Aviones getNumeroAvionV() {
        return numeroAvionV;
    }

    public void setNumeroAvionV(Aviones numeroAvionV) {
        this.numeroAvionV = numeroAvionV;
    }

    /**
     * @return the origen
     */
    public Ciudades getOrigen() {
        return origen;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(Ciudades origen) {
        this.origen = origen;
    }

    /**
     * @return the destino
     */
    public Ciudades getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(Ciudades destino) {
        this.destino = destino;
    }

}
