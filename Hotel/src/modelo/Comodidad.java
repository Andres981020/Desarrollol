/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "comodidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comodidad.findAll", query = "SELECT c FROM Comodidad c")
    , @NamedQuery(name = "Comodidad.findByCodigoComodidad", query = "SELECT c FROM Comodidad c WHERE c.codigoComodidad = :codigoComodidad")
    , @NamedQuery(name = "Comodidad.findByNombre", query = "SELECT c FROM Comodidad c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Comodidad.findByPorcentajeIncremento", query = "SELECT c FROM Comodidad c WHERE c.porcentajeIncremento = :porcentajeIncremento")})
public class Comodidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigoComodidad")
    private String codigoComodidad;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "porcentajeIncremento")
    private int porcentajeIncremento;
    @JoinTable(name = "saloncomodidad", joinColumns = {
        @JoinColumn(name = "codComodidad", referencedColumnName = "codigoComodidad")}, inverseJoinColumns = {
        @JoinColumn(name = "numSalon", referencedColumnName = "numeroSalon")})
    @ManyToMany
    private Collection<Salon> salonCollection;
    @ManyToMany(mappedBy = "comodidadCollection")
    private Collection<Habitacion> habitacionCollection;

    public Comodidad() {
    }

    public Comodidad(String codigoComodidad) {
        this.codigoComodidad = codigoComodidad;
    }

    public Comodidad(String codigoComodidad, String nombre, int porcentajeIncremento) {
        this.codigoComodidad = codigoComodidad;
        this.nombre = nombre;
        this.porcentajeIncremento = porcentajeIncremento;
    }

    public String getCodigoComodidad() {
        return codigoComodidad;
    }

    public void setCodigoComodidad(String codigoComodidad) {
        this.codigoComodidad = codigoComodidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPorcentajeIncremento() {
        return porcentajeIncremento;
    }

    public void setPorcentajeIncremento(int porcentajeIncremento) {
        this.porcentajeIncremento = porcentajeIncremento;
    }

    @XmlTransient
    public Collection<Salon> getSalonCollection() {
        return salonCollection;
    }

    public void setSalonCollection(Collection<Salon> salonCollection) {
        this.salonCollection = salonCollection;
    }

    @XmlTransient
    public Collection<Habitacion> getHabitacionCollection() {
        return habitacionCollection;
    }

    public void setHabitacionCollection(Collection<Habitacion> habitacionCollection) {
        this.habitacionCollection = habitacionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoComodidad != null ? codigoComodidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comodidad)) {
            return false;
        }
        Comodidad other = (Comodidad) object;
        if ((this.codigoComodidad == null && other.codigoComodidad != null) || (this.codigoComodidad != null && !this.codigoComodidad.equals(other.codigoComodidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Comodidad[ codigoComodidad=" + codigoComodidad + " ]";
    }
    
}
