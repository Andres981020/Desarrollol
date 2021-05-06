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
import modelo.Comodidad;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Habitacion;
import modelo.Reservahabitacion;
import persistencia.exceptions.IllegalOrphanException;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author Usuario
 */
public class HabitacionJpaController implements Serializable {

    public HabitacionJpaController() {
        this.emf = Persistence.createEntityManagerFactory("HospitalPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Habitacion habitacion) throws PreexistingEntityException, Exception {
        if (habitacion.getComodidadCollection() == null) {
            habitacion.setComodidadCollection(new ArrayList<Comodidad>());
        }
        if (habitacion.getReservahabitacionCollection() == null) {
            habitacion.setReservahabitacionCollection(new ArrayList<Reservahabitacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Comodidad> attachedComodidadCollection = new ArrayList<Comodidad>();
            for (Comodidad comodidadCollectionComodidadToAttach : habitacion.getComodidadCollection()) {
                comodidadCollectionComodidadToAttach = em.getReference(comodidadCollectionComodidadToAttach.getClass(), comodidadCollectionComodidadToAttach.getCodigoComodidad());
                attachedComodidadCollection.add(comodidadCollectionComodidadToAttach);
            }
            habitacion.setComodidadCollection(attachedComodidadCollection);
            Collection<Reservahabitacion> attachedReservahabitacionCollection = new ArrayList<Reservahabitacion>();
            for (Reservahabitacion reservahabitacionCollectionReservahabitacionToAttach : habitacion.getReservahabitacionCollection()) {
                reservahabitacionCollectionReservahabitacionToAttach = em.getReference(reservahabitacionCollectionReservahabitacionToAttach.getClass(), reservahabitacionCollectionReservahabitacionToAttach.getReservahabitacionPK());
                attachedReservahabitacionCollection.add(reservahabitacionCollectionReservahabitacionToAttach);
            }
            habitacion.setReservahabitacionCollection(attachedReservahabitacionCollection);
            em.persist(habitacion);
            for (Comodidad comodidadCollectionComodidad : habitacion.getComodidadCollection()) {
                comodidadCollectionComodidad.getHabitacionCollection().add(habitacion);
                comodidadCollectionComodidad = em.merge(comodidadCollectionComodidad);
            }
            for (Reservahabitacion reservahabitacionCollectionReservahabitacion : habitacion.getReservahabitacionCollection()) {
                Habitacion oldHabitacionOfReservahabitacionCollectionReservahabitacion = reservahabitacionCollectionReservahabitacion.getHabitacion();
                reservahabitacionCollectionReservahabitacion.setHabitacion(habitacion);
                reservahabitacionCollectionReservahabitacion = em.merge(reservahabitacionCollectionReservahabitacion);
                if (oldHabitacionOfReservahabitacionCollectionReservahabitacion != null) {
                    oldHabitacionOfReservahabitacionCollectionReservahabitacion.getReservahabitacionCollection().remove(reservahabitacionCollectionReservahabitacion);
                    oldHabitacionOfReservahabitacionCollectionReservahabitacion = em.merge(oldHabitacionOfReservahabitacionCollectionReservahabitacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHabitacion(habitacion.getNumeroHabitacion()) != null) {
                throw new PreexistingEntityException("Habitacion " + habitacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Habitacion habitacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Habitacion persistentHabitacion = em.find(Habitacion.class, habitacion.getNumeroHabitacion());
            Collection<Comodidad> comodidadCollectionOld = persistentHabitacion.getComodidadCollection();
            Collection<Comodidad> comodidadCollectionNew = habitacion.getComodidadCollection();
            Collection<Reservahabitacion> reservahabitacionCollectionOld = persistentHabitacion.getReservahabitacionCollection();
            Collection<Reservahabitacion> reservahabitacionCollectionNew = habitacion.getReservahabitacionCollection();
            List<String> illegalOrphanMessages = null;
            for (Reservahabitacion reservahabitacionCollectionOldReservahabitacion : reservahabitacionCollectionOld) {
                if (!reservahabitacionCollectionNew.contains(reservahabitacionCollectionOldReservahabitacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Reservahabitacion " + reservahabitacionCollectionOldReservahabitacion + " since its habitacion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Comodidad> attachedComodidadCollectionNew = new ArrayList<Comodidad>();
            for (Comodidad comodidadCollectionNewComodidadToAttach : comodidadCollectionNew) {
                comodidadCollectionNewComodidadToAttach = em.getReference(comodidadCollectionNewComodidadToAttach.getClass(), comodidadCollectionNewComodidadToAttach.getCodigoComodidad());
                attachedComodidadCollectionNew.add(comodidadCollectionNewComodidadToAttach);
            }
            comodidadCollectionNew = attachedComodidadCollectionNew;
            habitacion.setComodidadCollection(comodidadCollectionNew);
            Collection<Reservahabitacion> attachedReservahabitacionCollectionNew = new ArrayList<Reservahabitacion>();
            for (Reservahabitacion reservahabitacionCollectionNewReservahabitacionToAttach : reservahabitacionCollectionNew) {
                reservahabitacionCollectionNewReservahabitacionToAttach = em.getReference(reservahabitacionCollectionNewReservahabitacionToAttach.getClass(), reservahabitacionCollectionNewReservahabitacionToAttach.getReservahabitacionPK());
                attachedReservahabitacionCollectionNew.add(reservahabitacionCollectionNewReservahabitacionToAttach);
            }
            reservahabitacionCollectionNew = attachedReservahabitacionCollectionNew;
            habitacion.setReservahabitacionCollection(reservahabitacionCollectionNew);
            habitacion = em.merge(habitacion);
            for (Comodidad comodidadCollectionOldComodidad : comodidadCollectionOld) {
                if (!comodidadCollectionNew.contains(comodidadCollectionOldComodidad)) {
                    comodidadCollectionOldComodidad.getHabitacionCollection().remove(habitacion);
                    comodidadCollectionOldComodidad = em.merge(comodidadCollectionOldComodidad);
                }
            }
            for (Comodidad comodidadCollectionNewComodidad : comodidadCollectionNew) {
                if (!comodidadCollectionOld.contains(comodidadCollectionNewComodidad)) {
                    comodidadCollectionNewComodidad.getHabitacionCollection().add(habitacion);
                    comodidadCollectionNewComodidad = em.merge(comodidadCollectionNewComodidad);
                }
            }
            for (Reservahabitacion reservahabitacionCollectionNewReservahabitacion : reservahabitacionCollectionNew) {
                if (!reservahabitacionCollectionOld.contains(reservahabitacionCollectionNewReservahabitacion)) {
                    Habitacion oldHabitacionOfReservahabitacionCollectionNewReservahabitacion = reservahabitacionCollectionNewReservahabitacion.getHabitacion();
                    reservahabitacionCollectionNewReservahabitacion.setHabitacion(habitacion);
                    reservahabitacionCollectionNewReservahabitacion = em.merge(reservahabitacionCollectionNewReservahabitacion);
                    if (oldHabitacionOfReservahabitacionCollectionNewReservahabitacion != null && !oldHabitacionOfReservahabitacionCollectionNewReservahabitacion.equals(habitacion)) {
                        oldHabitacionOfReservahabitacionCollectionNewReservahabitacion.getReservahabitacionCollection().remove(reservahabitacionCollectionNewReservahabitacion);
                        oldHabitacionOfReservahabitacionCollectionNewReservahabitacion = em.merge(oldHabitacionOfReservahabitacionCollectionNewReservahabitacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = habitacion.getNumeroHabitacion();
                if (findHabitacion(id) == null) {
                    throw new NonexistentEntityException("The habitacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Habitacion habitacion;
            try {
                habitacion = em.getReference(Habitacion.class, id);
                habitacion.getNumeroHabitacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The habitacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Reservahabitacion> reservahabitacionCollectionOrphanCheck = habitacion.getReservahabitacionCollection();
            for (Reservahabitacion reservahabitacionCollectionOrphanCheckReservahabitacion : reservahabitacionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Habitacion (" + habitacion + ") cannot be destroyed since the Reservahabitacion " + reservahabitacionCollectionOrphanCheckReservahabitacion + " in its reservahabitacionCollection field has a non-nullable habitacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Comodidad> comodidadCollection = habitacion.getComodidadCollection();
            for (Comodidad comodidadCollectionComodidad : comodidadCollection) {
                comodidadCollectionComodidad.getHabitacionCollection().remove(habitacion);
                comodidadCollectionComodidad = em.merge(comodidadCollectionComodidad);
            }
            em.remove(habitacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Habitacion> findHabitacionEntities() {
        return findHabitacionEntities(true, -1, -1);
    }

    public List<Habitacion> findHabitacionEntities(int maxResults, int firstResult) {
        return findHabitacionEntities(false, maxResults, firstResult);
    }

    private List<Habitacion> findHabitacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Habitacion.class));
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

    public Habitacion findHabitacion(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Habitacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getHabitacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Habitacion> rt = cq.from(Habitacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
