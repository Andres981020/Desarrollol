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
import modelo.Salon;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Comodidad;
import modelo.Habitacion;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author Usuario
 */
public class ComodidadJpaController implements Serializable {

    public ComodidadJpaController() {
        this.emf = Persistence.createEntityManagerFactory("HospitalPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Comodidad comodidad) throws PreexistingEntityException, Exception {
        if (comodidad.getSalonCollection() == null) {
            comodidad.setSalonCollection(new ArrayList<Salon>());
        }
        if (comodidad.getHabitacionCollection() == null) {
            comodidad.setHabitacionCollection(new ArrayList<Habitacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Salon> attachedSalonCollection = new ArrayList<Salon>();
            for (Salon salonCollectionSalonToAttach : comodidad.getSalonCollection()) {
                salonCollectionSalonToAttach = em.getReference(salonCollectionSalonToAttach.getClass(), salonCollectionSalonToAttach.getNumeroSalon());
                attachedSalonCollection.add(salonCollectionSalonToAttach);
            }
            comodidad.setSalonCollection(attachedSalonCollection);
            Collection<Habitacion> attachedHabitacionCollection = new ArrayList<Habitacion>();
            for (Habitacion habitacionCollectionHabitacionToAttach : comodidad.getHabitacionCollection()) {
                habitacionCollectionHabitacionToAttach = em.getReference(habitacionCollectionHabitacionToAttach.getClass(), habitacionCollectionHabitacionToAttach.getNumeroHabitacion());
                attachedHabitacionCollection.add(habitacionCollectionHabitacionToAttach);
            }
            comodidad.setHabitacionCollection(attachedHabitacionCollection);
            em.persist(comodidad);
            for (Salon salonCollectionSalon : comodidad.getSalonCollection()) {
                salonCollectionSalon.getComodidadCollection().add(comodidad);
                salonCollectionSalon = em.merge(salonCollectionSalon);
            }
            for (Habitacion habitacionCollectionHabitacion : comodidad.getHabitacionCollection()) {
                habitacionCollectionHabitacion.getComodidadCollection().add(comodidad);
                habitacionCollectionHabitacion = em.merge(habitacionCollectionHabitacion);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findComodidad(comodidad.getCodigoComodidad()) != null) {
                throw new PreexistingEntityException("Comodidad " + comodidad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Comodidad comodidad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comodidad persistentComodidad = em.find(Comodidad.class, comodidad.getCodigoComodidad());
            Collection<Salon> salonCollectionOld = persistentComodidad.getSalonCollection();
            Collection<Salon> salonCollectionNew = comodidad.getSalonCollection();
            Collection<Habitacion> habitacionCollectionOld = persistentComodidad.getHabitacionCollection();
            Collection<Habitacion> habitacionCollectionNew = comodidad.getHabitacionCollection();
            Collection<Salon> attachedSalonCollectionNew = new ArrayList<Salon>();
            for (Salon salonCollectionNewSalonToAttach : salonCollectionNew) {
                salonCollectionNewSalonToAttach = em.getReference(salonCollectionNewSalonToAttach.getClass(), salonCollectionNewSalonToAttach.getNumeroSalon());
                attachedSalonCollectionNew.add(salonCollectionNewSalonToAttach);
            }
            salonCollectionNew = attachedSalonCollectionNew;
            comodidad.setSalonCollection(salonCollectionNew);
            Collection<Habitacion> attachedHabitacionCollectionNew = new ArrayList<Habitacion>();
            for (Habitacion habitacionCollectionNewHabitacionToAttach : habitacionCollectionNew) {
                habitacionCollectionNewHabitacionToAttach = em.getReference(habitacionCollectionNewHabitacionToAttach.getClass(), habitacionCollectionNewHabitacionToAttach.getNumeroHabitacion());
                attachedHabitacionCollectionNew.add(habitacionCollectionNewHabitacionToAttach);
            }
            habitacionCollectionNew = attachedHabitacionCollectionNew;
            comodidad.setHabitacionCollection(habitacionCollectionNew);
            comodidad = em.merge(comodidad);
            for (Salon salonCollectionOldSalon : salonCollectionOld) {
                if (!salonCollectionNew.contains(salonCollectionOldSalon)) {
                    salonCollectionOldSalon.getComodidadCollection().remove(comodidad);
                    salonCollectionOldSalon = em.merge(salonCollectionOldSalon);
                }
            }
            for (Salon salonCollectionNewSalon : salonCollectionNew) {
                if (!salonCollectionOld.contains(salonCollectionNewSalon)) {
                    salonCollectionNewSalon.getComodidadCollection().add(comodidad);
                    salonCollectionNewSalon = em.merge(salonCollectionNewSalon);
                }
            }
            for (Habitacion habitacionCollectionOldHabitacion : habitacionCollectionOld) {
                if (!habitacionCollectionNew.contains(habitacionCollectionOldHabitacion)) {
                    habitacionCollectionOldHabitacion.getComodidadCollection().remove(comodidad);
                    habitacionCollectionOldHabitacion = em.merge(habitacionCollectionOldHabitacion);
                }
            }
            for (Habitacion habitacionCollectionNewHabitacion : habitacionCollectionNew) {
                if (!habitacionCollectionOld.contains(habitacionCollectionNewHabitacion)) {
                    habitacionCollectionNewHabitacion.getComodidadCollection().add(comodidad);
                    habitacionCollectionNewHabitacion = em.merge(habitacionCollectionNewHabitacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = comodidad.getCodigoComodidad();
                if (findComodidad(id) == null) {
                    throw new NonexistentEntityException("The comodidad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comodidad comodidad;
            try {
                comodidad = em.getReference(Comodidad.class, id);
                comodidad.getCodigoComodidad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comodidad with id " + id + " no longer exists.", enfe);
            }
            Collection<Salon> salonCollection = comodidad.getSalonCollection();
            for (Salon salonCollectionSalon : salonCollection) {
                salonCollectionSalon.getComodidadCollection().remove(comodidad);
                salonCollectionSalon = em.merge(salonCollectionSalon);
            }
            Collection<Habitacion> habitacionCollection = comodidad.getHabitacionCollection();
            for (Habitacion habitacionCollectionHabitacion : habitacionCollection) {
                habitacionCollectionHabitacion.getComodidadCollection().remove(comodidad);
                habitacionCollectionHabitacion = em.merge(habitacionCollectionHabitacion);
            }
            em.remove(comodidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Comodidad> findComodidadEntities() {
        return findComodidadEntities(true, -1, -1);
    }

    public List<Comodidad> findComodidadEntities(int maxResults, int firstResult) {
        return findComodidadEntities(false, maxResults, firstResult);
    }

    private List<Comodidad> findComodidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Comodidad.class));
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

    public Comodidad findComodidad(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Comodidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getComodidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Comodidad> rt = cq.from(Comodidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
