/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "habitacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Habitacion.findAll", query = "SELECT h FROM Habitacion h")
    , @NamedQuery(name = "Habitacion.findByNumeroHabitacion", query = "SELECT h FROM Habitacion h WHERE h.numeroHabitacion = :numeroHabitacion")
    , @NamedQuery(name = "Habitacion.findByTipo", query = "SELECT h FROM Habitacion h WHERE h.tipo = :tipo")
    , @NamedQuery(name = "Habitacion.findByValor", query = "SELECT h FROM Habitacion h WHERE h.valor = :valor")
    , @NamedQuery(name = "Habitacion.findByEstado", query = "SELECT h FROM Habitacion h WHERE h.estado = :estado")})
public class Habitacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "numeroHabitacion")
    private String numeroHabitacion;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "valor")
    private int valor;
    @Column(name = "Estado")
    private String estado;
    @JoinTable(name = "habitacioncomodidad", joinColumns = {
        @JoinColumn(name = "numHabitacion", referencedColumnName = "numeroHabitacion")}, inverseJoinColumns = {
        @JoinColumn(name = "codComodidad", referencedColumnName = "codigoComodidad")})
    @ManyToMany
    private Collection<Comodidad> comodidadCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "habitacion")
    private Collection<Reservahabitacion> reservahabitacionCollection;

    public Habitacion() {
    }

    public Habitacion(String numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public Habitacion(String numeroHabitacion, String tipo, int valor) {
        this.numeroHabitacion = numeroHabitacion;
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(String numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<Comodidad> getComodidadCollection() {
        return comodidadCollection;
    }

    public void setComodidadCollection(Collection<Comodidad> comodidadCollection) {
        this.comodidadCollection = comodidadCollection;
    }

    @XmlTransient
    public Collection<Reservahabitacion> getReservahabitacionCollection() {
        return reservahabitacionCollection;
    }

    public void setReservahabitacionCollection(Collection<Reservahabitacion> reservahabitacionCollection) {
        this.reservahabitacionCollection = reservahabitacionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroHabitacion != null ? numeroHabitacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Habitacion)) {
            return false;
        }
        Habitacion other = (Habitacion) object;
        if ((this.numeroHabitacion == null && other.numeroHabitacion != null) || (this.numeroHabitacion != null && !this.numeroHabitacion.equals(other.numeroHabitacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Habitacion[ numeroHabitacion=" + numeroHabitacion + " ]";
    }
    
}
