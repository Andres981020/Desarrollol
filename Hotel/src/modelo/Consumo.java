/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "consumo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consumo.findAll", query = "SELECT c FROM Consumo c")
    , @NamedQuery(name = "Consumo.findByCodProducto", query = "SELECT c FROM Consumo c WHERE c.consumoPK.codProducto = :codProducto")
    , @NamedQuery(name = "Consumo.findByCodReserva", query = "SELECT c FROM Consumo c WHERE c.consumoPK.codReserva = :codReserva")
    , @NamedQuery(name = "Consumo.findByCantidad", query = "SELECT c FROM Consumo c WHERE c.cantidad = :cantidad")})
public class Consumo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ConsumoPK consumoPK;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    @JoinColumn(name = "codProducto", referencedColumnName = "codigoProducto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Producto producto;
    @JoinColumn(name = "codReserva", referencedColumnName = "codigoR", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Reserva reserva;

    public Consumo() {
    }

    public Consumo(ConsumoPK consumoPK) {
        this.consumoPK = consumoPK;
    }

    public Consumo(ConsumoPK consumoPK, int cantidad) {
        this.consumoPK = consumoPK;
        this.cantidad = cantidad;
    }

    public Consumo(String codProducto, int codReserva) {
        this.consumoPK = new ConsumoPK(codProducto, codReserva);
    }

    public ConsumoPK getConsumoPK() {
        return consumoPK;
    }

    public void setConsumoPK(ConsumoPK consumoPK) {
        this.consumoPK = consumoPK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
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
        hash += (consumoPK != null ? consumoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consumo)) {
            return false;
        }
        Consumo other = (Consumo) object;
        if ((this.consumoPK == null && other.consumoPK != null) || (this.consumoPK != null && !this.consumoPK.equals(other.consumoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Consumo[ consumoPK=" + consumoPK + " ]";
    }
    
}
