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
public class ReservasalonPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "numSalon")
    private String numSalon;
    @Basic(optional = false)
    @Column(name = "codReserva")
    private int codReserva;

    public ReservasalonPK() {
    }

    public ReservasalonPK(String numSalon, int codReserva) {
        this.numSalon = numSalon;
        this.codReserva = codReserva;
    }

    public String getNumSalon() {
        return numSalon;
    }

    public void setNumSalon(String numSalon) {
        this.numSalon = numSalon;
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
        hash += (numSalon != null ? numSalon.hashCode() : 0);
        hash += (int) codReserva;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservasalonPK)) {
            return false;
        }
        ReservasalonPK other = (ReservasalonPK) object;
        if ((this.numSalon == null && other.numSalon != null) || (this.numSalon != null && !this.numSalon.equals(other.numSalon))) {
            return false;
        }
        if (this.codReserva != other.codReserva) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ReservasalonPK[ numSalon=" + numSalon + ", codReserva=" + codReserva + " ]";
    }
    
}
