/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "reserva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r")
    , @NamedQuery(name = "Reserva.findByCodigoR", query = "SELECT r FROM Reserva r WHERE r.codigoR = :codigoR")
    , @NamedQuery(name = "Reserva.findByFechaDeRealizacion", query = "SELECT r FROM Reserva r WHERE r.fechaDeRealizacion = :fechaDeRealizacion")
    , @NamedQuery(name = "Reserva.findByEstado", query = "SELECT r FROM Reserva r WHERE r.estado = :estado")})
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoR")
    private Integer codigoR;
    @Basic(optional = false)
    @Column(name = "fechaDeRealizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeRealizacion;
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reserva")
    private Collection<Consumo> consumoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reserva")
    private Collection<Reservasalon> reservasalonCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reserva")
    private Collection<Reservahabitacion> reservahabitacionCollection;
    @JoinColumn(name = "codigoCliente", referencedColumnName = "identificacion")
    @ManyToOne(optional = false)
    private Cliente codigoCliente;

    public Reserva() {
    }

    public Reserva(Integer codigoR) {
        this.codigoR = codigoR;
    }

    public Reserva(Integer codigoR, Date fechaDeRealizacion, String estado) {
        this.codigoR = codigoR;
        this.fechaDeRealizacion = fechaDeRealizacion;
        this.estado = estado;
    }

    public Integer getCodigoR() {
        return codigoR;
    }

    public void setCodigoR(Integer codigoR) {
        this.codigoR = codigoR;
    }

    public Date getFechaDeRealizacion() {
        return fechaDeRealizacion;
    }

    public void setFechaDeRealizacion(Date fechaDeRealizacion) {
        this.fechaDeRealizacion = fechaDeRealizacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<Consumo> getConsumoCollection() {
        return consumoCollection;
    }

    public void setConsumoCollection(Collection<Consumo> consumoCollection) {
        this.consumoCollection = consumoCollection;
    }

    @XmlTransient
    public Collection<Reservasalon> getReservasalonCollection() {
        return reservasalonCollection;
    }

    public void setReservasalonCollection(Collection<Reservasalon> reservasalonCollection) {
        this.reservasalonCollection = reservasalonCollection;
    }

    @XmlTransient
    public Collection<Reservahabitacion> getReservahabitacionCollection() {
        return reservahabitacionCollection;
    }

    public void setReservahabitacionCollection(Collection<Reservahabitacion> reservahabitacionCollection) {
        this.reservahabitacionCollection = reservahabitacionCollection;
    }

    public Cliente getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Cliente codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoR != null ? codigoR.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.codigoR == null && other.codigoR != null) || (this.codigoR != null && !this.codigoR.equals(other.codigoR))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Reserva[ codigoR=" + codigoR + " ]";
    }
    
}
