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
@Table(name = "salon")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Salon.findAll", query = "SELECT s FROM Salon s")
    , @NamedQuery(name = "Salon.findByNumeroSalon", query = "SELECT s FROM Salon s WHERE s.numeroSalon = :numeroSalon")
    , @NamedQuery(name = "Salon.findByTipo", query = "SELECT s FROM Salon s WHERE s.tipo = :tipo")
    , @NamedQuery(name = "Salon.findByValor", query = "SELECT s FROM Salon s WHERE s.valor = :valor")
    , @NamedQuery(name = "Salon.findByCapacidad", query = "SELECT s FROM Salon s WHERE s.capacidad = :capacidad")
    , @NamedQuery(name = "Salon.findByEstado", query = "SELECT s FROM Salon s WHERE s.estado = :estado")})
public class Salon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "numeroSalon")
    private String numeroSalon;
    @Basic(optional = false)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "valor")
    private int valor;
    @Basic(optional = false)
    @Column(name = "capacidad")
    private int capacidad;
    @Column(name = "Estado")
    private String estado;
    @ManyToMany(mappedBy = "salonCollection")
    private Collection<Comodidad> comodidadCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salon")
    private Collection<Reservasalon> reservasalonCollection;

    public Salon() {
    }

    public Salon(String numeroSalon) {
        this.numeroSalon = numeroSalon;
    }

    public Salon(String numeroSalon, String tipo, int valor, int capacidad) {
        this.numeroSalon = numeroSalon;
        this.tipo = tipo;
        this.valor = valor;
        this.capacidad = capacidad;
    }

    public String getNumeroSalon() {
        return numeroSalon;
    }

    public void setNumeroSalon(String numeroSalon) {
        this.numeroSalon = numeroSalon;
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

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
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
    public Collection<Reservasalon> getReservasalonCollection() {
        return reservasalonCollection;
    }

    public void setReservasalonCollection(Collection<Reservasalon> reservasalonCollection) {
        this.reservasalonCollection = reservasalonCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroSalon != null ? numeroSalon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Salon)) {
            return false;
        }
        Salon other = (Salon) object;
        if ((this.numeroSalon == null && other.numeroSalon != null) || (this.numeroSalon != null && !this.numeroSalon.equals(other.numeroSalon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Salon[ numeroSalon=" + numeroSalon + " ]";
    }
    
}
