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
@Table(name = "reservahabitacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservahabitacion.findAll", query = "SELECT r FROM Reservahabitacion r")
    , @NamedQuery(name = "Reservahabitacion.findByNumHabitacion", query = "SELECT r FROM Reservahabitacion r WHERE r.reservahabitacionPK.numHabitacion = :numHabitacion")
    , @NamedQuery(name = "Reservahabitacion.findByCodReserva", query = "SELECT r FROM Reservahabitacion r WHERE r.reservahabitacionPK.codReserva = :codReserva")
    , @NamedQuery(name = "Reservahabitacion.findByFecharInicio", query = "SELECT r FROM Reservahabitacion r WHERE r.fecharInicio = :fecharInicio")
    , @NamedQuery(name = "Reservahabitacion.findByFechaFin", query = "SELECT r FROM Reservahabitacion r WHERE r.fechaFin = :fechaFin")
    , @NamedQuery(name = "Reservahabitacion.findByNumeroPersonas", query = "SELECT r FROM Reservahabitacion r WHERE r.numeroPersonas = :numeroPersonas")})
public class Reservahabitacion implements Serializable {

    @Column(name = "estadoHabitacion")
    private String estadoHabitacion;

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReservahabitacionPK reservahabitacionPK;
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
    @JoinColumn(name = "numHabitacion", referencedColumnName = "numeroHabitacion", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Habitacion habitacion;
    @JoinColumn(name = "codReserva", referencedColumnName = "codigoR", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Reserva reserva;

    public Reservahabitacion() {
    }

    public Reservahabitacion(ReservahabitacionPK reservahabitacionPK) {
        this.reservahabitacionPK = reservahabitacionPK;
    }

    public Reservahabitacion(ReservahabitacionPK reservahabitacionPK, Date fecharInicio, Date fechaFin) {
        this.reservahabitacionPK = reservahabitacionPK;
        this.fecharInicio = fecharInicio;
        this.fechaFin = fechaFin;
    }

    public Reservahabitacion(String numHabitacion, int codReserva) {
        this.reservahabitacionPK = new ReservahabitacionPK(numHabitacion, codReserva);
    }

    public ReservahabitacionPK getReservahabitacionPK() {
        return reservahabitacionPK;
    }

    public void setReservahabitacionPK(ReservahabitacionPK reservahabitacionPK) {
        this.reservahabitacionPK = reservahabitacionPK;
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

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
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
        hash += (reservahabitacionPK != null ? reservahabitacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservahabitacion)) {
            return false;
        }
        Reservahabitacion other = (Reservahabitacion) object;
        if ((this.reservahabitacionPK == null && other.reservahabitacionPK != null) || (this.reservahabitacionPK != null && !this.reservahabitacionPK.equals(other.reservahabitacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Reservahabitacion[ reservahabitacionPK=" + reservahabitacionPK + " ]";
    }

    public String getEstadoHabitacion() {
        return estadoHabitacion;
    }

    public void setEstadoHabitacion(String estadoHabitacion) {
        this.estadoHabitacion = estadoHabitacion;
    }
    
}
