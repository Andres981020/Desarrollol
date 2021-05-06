/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Cliente;
import modelo.Consumo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import modelo.Reserva;
import modelo.Reservasalon;
import modelo.Reservahabitacion;
import persistencia.exceptions.IllegalOrphanException;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Usuario
 */
public class ReservaJpaController implements Serializable {

    public ReservaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("HospitalPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reserva reserva) {
        if (reserva.getConsumoCollection() == null) {
            reserva.setConsumoCollection(new ArrayList<Consumo>());
        }
        if (reserva.getReservasalonCollection() == null) {
            reserva.setReservasalonCollection(new ArrayList<Reservasalon>());
        }
        if (reserva.getReservahabitacionCollection() == null) {
            reserva.setReservahabitacionCollection(new ArrayList<Reservahabitacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente codigoCliente = reserva.getCodigoCliente();
            if (codigoCliente != null) {
                codigoCliente = em.getReference(codigoCliente.getClass(), codigoCliente.getIdentificacion());
                reserva.setCodigoCliente(codigoCliente);
            }
            Collection<Consumo> attachedConsumoCollection = new ArrayList<Consumo>();
            for (Consumo consumoCollectionConsumoToAttach : reserva.getConsumoCollection()) {
                consumoCollectionConsumoToAttach = em.getReference(consumoCollectionConsumoToAttach.getClass(), consumoCollectionConsumoToAttach.getConsumoPK());
                attachedConsumoCollection.add(consumoCollectionConsumoToAttach);
            }
            reserva.setConsumoCollection(attachedConsumoCollection);
            Collection<Reservasalon> attachedReservasalonCollection = new ArrayList<Reservasalon>();
            for (Reservasalon reservasalonCollectionReservasalonToAttach : reserva.getReservasalonCollection()) {
                reservasalonCollectionReservasalonToAttach = em.getReference(reservasalonCollectionReservasalonToAttach.getClass(), reservasalonCollectionReservasalonToAttach.getReservasalonPK());
                attachedReservasalonCollection.add(reservasalonCollectionReservasalonToAttach);
            }
            reserva.setReservasalonCollection(attachedReservasalonCollection);
            Collection<Reservahabitacion> attachedReservahabitacionCollection = new ArrayList<Reservahabitacion>();
            for (Reservahabitacion reservahabitacionCollectionReservahabitacionToAttach : reserva.getReservahabitacionCollection()) {
                reservahabitacionCollectionReservahabitacionToAttach = em.getReference(reservahabitacionCollectionReservahabitacionToAttach.getClass(), reservahabitacionCollectionReservahabitacionToAttach.getReservahabitacionPK());
                attachedReservahabitacionCollection.add(reservahabitacionCollectionReservahabitacionToAttach);
            }
            reserva.setReservahabitacionCollection(attachedReservahabitacionCollection);
            em.persist(reserva);
            if (codigoCliente != null) {
                codigoCliente.getReservaCollection().add(reserva);
                codigoCliente = em.merge(codigoCliente);
            }
            for (Consumo consumoCollectionConsumo : reserva.getConsumoCollection()) {
                Reserva oldReservaOfConsumoCollectionConsumo = consumoCollectionConsumo.getReserva();
                consumoCollectionConsumo.setReserva(reserva);
                consumoCollectionConsumo = em.merge(consumoCollectionConsumo);
                if (oldReservaOfConsumoCollectionConsumo != null) {
                    oldReservaOfConsumoCollectionConsumo.getConsumoCollection().remove(consumoCollectionConsumo);
                    oldReservaOfConsumoCollectionConsumo = em.merge(oldReservaOfConsumoCollectionConsumo);
                }
            }
            for (Reservasalon reservasalonCollectionReservasalon : reserva.getReservasalonCollection()) {
                Reserva oldReservaOfReservasalonCollectionReservasalon = reservasalonCollectionReservasalon.getReserva();
                reservasalonCollectionReservasalon.setReserva(reserva);
                reservasalonCollectionReservasalon = em.merge(reservasalonCollectionReservasalon);
                if (oldReservaOfReservasalonCollectionReservasalon != null) {
                    oldReservaOfReservasalonCollectionReservasalon.getReservasalonCollection().remove(reservasalonCollectionReservasalon);
                    oldReservaOfReservasalonCollectionReservasalon = em.merge(oldReservaOfReservasalonCollectionReservasalon);
                }
            }
            for (Reservahabitacion reservahabitacionCollectionReservahabitacion : reserva.getReservahabitacionCollection()) {
                Reserva oldReservaOfReservahabitacionCollectionReservahabitacion = reservahabitacionCollectionReservahabitacion.getReserva();
                reservahabitacionCollectionReservahabitacion.setReserva(reserva);
                reservahabitacionCollectionReservahabitacion = em.merge(reservahabitacionCollectionReservahabitacion);
                if (oldReservaOfReservahabitacionCollectionReservahabitacion != null) {
                    oldReservaOfReservahabitacionCollectionReservahabitacion.getReservahabitacionCollection().remove(reservahabitacionCollectionReservahabitacion);
                    oldReservaOfReservahabitacionCollectionReservahabitacion = em.merge(oldReservaOfReservahabitacionCollectionReservahabitacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reserva reserva) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reserva persistentReserva = em.find(Reserva.class, reserva.getCodigoR());
            Cliente codigoClienteOld = persistentReserva.getCodigoCliente();
            Cliente codigoClienteNew = reserva.getCodigoCliente();
            Collection<Consumo> consumoCollectionOld = persistentReserva.getConsumoCollection();
            Collection<Consumo> consumoCollectionNew = reserva.getConsumoCollection();
            Collection<Reservasalon> reservasalonCollectionOld = persistentReserva.getReservasalonCollection();
            Collection<Reservasalon> reservasalonCollectionNew = reserva.getReservasalonCollection();
            Collection<Reservahabitacion> reservahabitacionCollectionOld = persistentReserva.getReservahabitacionCollection();
            Collection<Reservahabitacion> reservahabitacionCollectionNew = reserva.getReservahabitacionCollection();
            List<String> illegalOrphanMessages = null;
            for (Consumo consumoCollectionOldConsumo : consumoCollectionOld) {
                if (!consumoCollectionNew.contains(consumoCollectionOldConsumo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Consumo " + consumoCollectionOldConsumo + " since its reserva field is not nullable.");
                }
            }
            for (Reservasalon reservasalonCollectionOldReservasalon : reservasalonCollectionOld) {
                if (!reservasalonCollectionNew.contains(reservasalonCollectionOldReservasalon)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Reservasalon " + reservasalonCollectionOldReservasalon + " since its reserva field is not nullable.");
                }
            }
            for (Reservahabitacion reservahabitacionCollectionOldReservahabitacion : reservahabitacionCollectionOld) {
                if (!reservahabitacionCollectionNew.contains(reservahabitacionCollectionOldReservahabitacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Reservahabitacion " + reservahabitacionCollectionOldReservahabitacion + " since its reserva field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codigoClienteNew != null) {
                codigoClienteNew = em.getReference(codigoClienteNew.getClass(), codigoClienteNew.getIdentificacion());
                reserva.setCodigoCliente(codigoClienteNew);
            }
            Collection<Consumo> attachedConsumoCollectionNew = new ArrayList<Consumo>();
            for (Consumo consumoCollectionNewConsumoToAttach : consumoCollectionNew) {
                consumoCollectionNewConsumoToAttach = em.getReference(consumoCollectionNewConsumoToAttach.getClass(), consumoCollectionNewConsumoToAttach.getConsumoPK());
                attachedConsumoCollectionNew.add(consumoCollectionNewConsumoToAttach);
            }
            consumoCollectionNew = attachedConsumoCollectionNew;
            reserva.setConsumoCollection(consumoCollectionNew);
            Collection<Reservasalon> attachedReservasalonCollectionNew = new ArrayList<Reservasalon>();
            for (Reservasalon reservasalonCollectionNewReservasalonToAttach : reservasalonCollectionNew) {
                reservasalonCollectionNewReservasalonToAttach = em.getReference(reservasalonCollectionNewReservasalonToAttach.getClass(), reservasalonCollectionNewReservasalonToAttach.getReservasalonPK());
                attachedReservasalonCollectionNew.add(reservasalonCollectionNewReservasalonToAttach);
            }
            reservasalonCollectionNew = attachedReservasalonCollectionNew;
            reserva.setReservasalonCollection(reservasalonCollectionNew);
            Collection<Reservahabitacion> attachedReservahabitacionCollectionNew = new ArrayList<Reservahabitacion>();
            for (Reservahabitacion reservahabitacionCollectionNewReservahabitacionToAttach : reservahabitacionCollectionNew) {
                reservahabitacionCollectionNewReservahabitacionToAttach = em.getReference(reservahabitacionCollectionNewReservahabitacionToAttach.getClass(), reservahabitacionCollectionNewReservahabitacionToAttach.getReservahabitacionPK());
                attachedReservahabitacionCollectionNew.add(reservahabitacionCollectionNewReservahabitacionToAttach);
            }
            reservahabitacionCollectionNew = attachedReservahabitacionCollectionNew;
            reserva.setReservahabitacionCollection(reservahabitacionCollectionNew);
            reserva = em.merge(reserva);
            if (codigoClienteOld != null && !codigoClienteOld.equals(codigoClienteNew)) {
                codigoClienteOld.getReservaCollection().remove(reserva);
                codigoClienteOld = em.merge(codigoClienteOld);
            }
            if (codigoClienteNew != null && !codigoClienteNew.equals(codigoClienteOld)) {
                codigoClienteNew.getReservaCollection().add(reserva);
                codigoClienteNew = em.merge(codigoClienteNew);
            }
            for (Consumo consumoCollectionNewConsumo : consumoCollectionNew) {
                if (!consumoCollectionOld.contains(consumoCollectionNewConsumo)) {
                    Reserva oldReservaOfConsumoCollectionNewConsumo = consumoCollectionNewConsumo.getReserva();
                    consumoCollectionNewConsumo.setReserva(reserva);
                    consumoCollectionNewConsumo = em.merge(consumoCollectionNewConsumo);
                    if (oldReservaOfConsumoCollectionNewConsumo != null && !oldReservaOfConsumoCollectionNewConsumo.equals(reserva)) {
                        oldReservaOfConsumoCollectionNewConsumo.getConsumoCollection().remove(consumoCollectionNewConsumo);
                        oldReservaOfConsumoCollectionNewConsumo = em.merge(oldReservaOfConsumoCollectionNewConsumo);
                    }
                }
            }
            for (Reservasalon reservasalonCollectionNewReservasalon : reservasalonCollectionNew) {
                if (!reservasalonCollectionOld.contains(reservasalonCollectionNewReservasalon)) {
                    Reserva oldReservaOfReservasalonCollectionNewReservasalon = reservasalonCollectionNewReservasalon.getReserva();
                    reservasalonCollectionNewReservasalon.setReserva(reserva);
                    reservasalonCollectionNewReservasalon = em.merge(reservasalonCollectionNewReservasalon);
                    if (oldReservaOfReservasalonCollectionNewReservasalon != null && !oldReservaOfReservasalonCollectionNewReservasalon.equals(reserva)) {
                        oldReservaOfReservasalonCollectionNewReservasalon.getReservasalonCollection().remove(reservasalonCollectionNewReservasalon);
                        oldReservaOfReservasalonCollectionNewReservasalon = em.merge(oldReservaOfReservasalonCollectionNewReservasalon);
                    }
                }
            }
            for (Reservahabitacion reservahabitacionCollectionNewReservahabitacion : reservahabitacionCollectionNew) {
                if (!reservahabitacionCollectionOld.contains(reservahabitacionCollectionNewReservahabitacion)) {
                    Reserva oldReservaOfReservahabitacionCollectionNewReservahabitacion = reservahabitacionCollectionNewReservahabitacion.getReserva();
                    reservahabitacionCollectionNewReservahabitacion.setReserva(reserva);
                    reservahabitacionCollectionNewReservahabitacion = em.merge(reservahabitacionCollectionNewReservahabitacion);
                    if (oldReservaOfReservahabitacionCollectionNewReservahabitacion != null && !oldReservaOfReservahabitacionCollectionNewReservahabitacion.equals(reserva)) {
                        oldReservaOfReservahabitacionCollectionNewReservahabitacion.getReservahabitacionCollection().remove(reservahabitacionCollectionNewReservahabitacion);
                        oldReservaOfReservahabitacionCollectionNewReservahabitacion = em.merge(oldReservaOfReservahabitacionCollectionNewReservahabitacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = reserva.getCodigoR();
                if (findReserva(id) == null) {
                    throw new NonexistentEntityException("The reserva with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reserva reserva;
            try {
                reserva = em.getReference(Reserva.class, id);
                reserva.getCodigoR();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reserva with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Consumo> consumoCollectionOrphanCheck = reserva.getConsumoCollection();
            for (Consumo consumoCollectionOrphanCheckConsumo : consumoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Reserva (" + reserva + ") cannot be destroyed since the Consumo " + consumoCollectionOrphanCheckConsumo + " in its consumoCollection field has a non-nullable reserva field.");
            }
            Collection<Reservasalon> reservasalonCollectionOrphanCheck = reserva.getReservasalonCollection();
            for (Reservasalon reservasalonCollectionOrphanCheckReservasalon : reservasalonCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Reserva (" + reserva + ") cannot be destroyed since the Reservasalon " + reservasalonCollectionOrphanCheckReservasalon + " in its reservasalonCollection field has a non-nullable reserva field.");
            }
            Collection<Reservahabitacion> reservahabitacionCollectionOrphanCheck = reserva.getReservahabitacionCollection();
            for (Reservahabitacion reservahabitacionCollectionOrphanCheckReservahabitacion : reservahabitacionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Reserva (" + reserva + ") cannot be destroyed since the Reservahabitacion " + reservahabitacionCollectionOrphanCheckReservahabitacion + " in its reservahabitacionCollection field has a non-nullable reserva field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cliente codigoCliente = reserva.getCodigoCliente();
            if (codigoCliente != null) {
                codigoCliente.getReservaCollection().remove(reserva);
                codigoCliente = em.merge(codigoCliente);
            }
            em.remove(reserva);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reserva> findReservaEntities() {
        return findReservaEntities(true, -1, -1);
    }

    public List<Reserva> findReservaEntities(int maxResults, int firstResult) {
        return findReservaEntities(false, maxResults, firstResult);
    }

    private List<Reserva> findReservaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reserva.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Reserva findReserva(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reserva.class, id);
        } finally {
            em.close();
        }
    }

    public int getReservaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reserva> rt = cq.from(Reserva.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Reserva consultarReservaId(Cliente idCliente){
        String consulta = "select r from Reserva r "
                + "where r.getCodigoCliente()= '"+idCliente+"'";
        try{
        EntityManager em = getEntityManager();
        Query query = em.createQuery(consulta);
        return (Reserva) query.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
    
}
