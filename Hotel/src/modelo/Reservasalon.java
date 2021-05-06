/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "reservasalon")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservasalon.findAll", query = "SELECT r FROM Reservasalon r")
    , @NamedQuery(name = "Reservasalon.findByNumSalon", query = "SELECT r FROM Reservasalon r WHERE r.reservasalonPK.numSalon = :numSalon")
    , @NamedQuery(name = "Reservasalon.findByCodReserva", query = "SELECT r FROM Reservasalon r WHERE r.reservasalonPK.codReserva = :codReserva")
    , @NamedQuery(name = "Reservasalon.findByFecharInicio", query = "SELECT r FROM Reservasalon r WHERE r.fecharInicio = :fecharInicio")
    , @NamedQuery(name = "Reservasalon.findByFechaFin", query = "SELECT r FROM Reservasalon r WHERE r.fechaFin = :fechaFin")
    , @NamedQuery(name = "Reservasalon.findByNumeroPersonas", query = "SELECT r FROM Reservasalon r WHERE r.numeroPersonas = :numeroPersonas")})
public class Reservasalon implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReservasalonPK reservasalonPK;
    @Basic(optional = false)
    @Column(name = "fecharInicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecharInicio;
    @Basic(optional = false)
    @Column(name = "fechaFin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Column(name = "numeroPersonas")
    private Integer numeroPersonas;
    @JoinColumn(name = "numSalon", referencedColumnName = "numeroSalon", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Salon salon;
    @JoinColumn(name = "codReserva", referencedColumnName = "codigoR", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Reserva reserva;

    public Reservasalon() {
    }

    public Reservasalon(ReservasalonPK reservasalonPK) {
        this.reservasalonPK = reservasalonPK;
    }

    public Reservasalon(ReservasalonPK reservasalonPK, Date fecharInicio, Date fechaFin) {
        this.reservasalonPK = reservasalonPK;
        this.fecharInicio = fecharInicio;
        this.fechaFin = fechaFin;
    }

    public Reservasalon(String numSalon, int codReserva) {
        this.reservasalonPK = new ReservasalonPK(numSalon, codReserva);
    }

    public ReservasalonPK getReservasalonPK() {
        return reservasalonPK;
    }

    public void setReservasalonPK(ReservasalonPK reservasalonPK) {
        this.reservasalonPK = reservasalonPK;
    }

    public Date getFecharInicio() {
        return fecharInicio;
    }

    public void setFecharInicio(Date fecharInicio) {
        this.fecharInicio = fecharInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setNumeroPersonas(Integer numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservasalonPK != null ? reservasalonPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservasalon)) {
            return false;
        }
        Reservasalon other = (Reservasalon) object;
        if ((this.reservasalonPK == null && other.reservasalonPK != null) || (this.reservasalonPK != null && !this.reservasalonPK.equals(other.reservasalonPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Reservasalon[ reservasalonPK=" + reservasalonPK + " ]";
    }
    
}
