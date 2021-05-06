/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Usuario
 */
@Embeddable
public class ReservahabitacionPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "numHabitacion")
    private String numHabitacion;
    @Basic(optional = false)
    @Column(name = "codReserva")
    private int codReserva;

    public ReservahabitacionPK() {
    }

    public ReservahabitacionPK(String numHabitacion, int codReserva) {
        this.numHabitacion = numHabitacion;
        this.codReserva = codReserva;
    }

    public String getNumHabitacion() {
        return numHabitacion;
    }

    public void setNumHabitacion(String numHabitacion) {
        this.numHabitacion = numHabitacion;
    }

    public int getCodReserva() {
        return codReserva;
    }

    public void setCodReserva(int codReserva) {
        this.codReserva = codReserva;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numHabitacion != null ? numHabitacion.hashCode() : 0);
        hash += (int) codReserva;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservahabitacionPK)) {
            return false;
        }
        ReservahabitacionPK other = (ReservahabitacionPK) object;
        if ((this.numHabitacion == null && other.numHabitacion != null) || (this.numHabitacion != null && !this.numHabitacion.equals(other.numHabitacion))) {
            return false;
        }
        if (this.codReserva != other.codReserva) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ReservahabitacionPK[ numHabitacion=" + numHabitacion + ", codReserva=" + codReserva + " ]";
    }
    
}
