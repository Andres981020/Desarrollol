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
public class ConsumoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "codProducto")
    private String codProducto;
    @Basic(optional = false)
    @Column(name = "codReserva")
    private int codReserva;

    public ConsumoPK() {
    }

    public ConsumoPK(String codProducto, int codReserva) {
        this.codProducto = codProducto;
        this.codReserva = codReserva;
    }

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
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
        hash += (codProducto != null ? codProducto.hashCode() : 0);
        hash += (int) codReserva;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsumoPK)) {
            return false;
        }
        ConsumoPK other = (ConsumoPK) object;
        if ((this.codProducto == null && other.codProducto != null) || (this.codProducto != null && !this.codProducto.equals(other.codProducto))) {
            return false;
        }
        if (this.codReserva != other.codReserva) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ConsumoPK[ codProducto=" + codProducto + ", codReserva=" + codReserva + " ]";
    }
    
}
