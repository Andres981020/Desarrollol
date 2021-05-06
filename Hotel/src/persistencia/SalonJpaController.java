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
import modelo.Reservasalon;
import modelo.Salon;
import persistencia.exceptions.IllegalOrphanException;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author Usuario
 */
public class SalonJpaController implements Serializable {

    public SalonJpaController() {
        this.emf = Persistence.createEntityManagerFactory("HospitalPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Salon salon) throws PreexistingEntityException, Exception {
        if (salon.getComodidadCollection() == null) {
            salon.setComodidadCollection(new ArrayList<Comodidad>());
        }
        if (salon.getReservasalonCollection() == null) {
            salon.setReservasalonCollection(new ArrayList<Reservasalon>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Comodidad> attachedComodidadCollection = new ArrayList<Comodidad>();
            for (Comodidad comodidadCollectionComodidadToAttach : salon.getComodidadCollection()) {
                comodidadCollectionComodidadToAttach = em.getReference(comodidadCollectionComodidadToAttach.getClass(), comodidadCollectionComodidadToAttach.getCodigoComodidad());
                attachedComodidadCollection.add(comodidadCollectionComodidadToAttach);
            }
            salon.setComodidadCollection(attachedComodidadCollection);
            Collection<Reservasalon> attachedReservasalonCollection = new ArrayList<Reservasalon>();
            for (Reservasalon reservasalonCollectionReservasalonToAttach : salon.getReservasalonCollection()) {
                reservasalonCollectionReservasalonToAttach = em.getReference(reservasalonCollectionReservasalonToAttach.getClass(), reservasalonCollectionReservasalonToAttach.getReservasalonPK());
                attachedReservasalonCollection.add(reservasalonCollectionReservasalonToAttach);
            }
            salon.setReservasalonCollection(attachedReservasalonCollection);
            em.persist(salon);
            for (Comodidad comodidadCollectionComodidad : salon.getComodidadCollection()) {
                comodidadCollectionComodidad.getSalonCollection().add(salon);
                comodidadCollectionComodidad = em.merge(comodidadCollectionComodidad);
            }
            for (Reservasalon reservasalonCollectionReservasalon : salon.getReservasalonCollection()) {
                Salon oldSalonOfReservasalonCollectionReservasalon = reservasalonCollectionReservasalon.getSalon();
                reservasalonCollectionReservasalon.setSalon(salon);
                reservasalonCollectionReservasalon = em.merge(reservasalonCollectionReservasalon);
                if (oldSalonOfReservasalonCollectionReservasalon != null) {
                    oldSalonOfReservasalonCollectionReservasalon.getReservasalonCollection().remove(reservasalonCollectionReservasalon);
                    oldSalonOfReservasalonCollectionReservasalon = em.merge(oldSalonOfReservasalonCollectionReservasalon);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSalon(salon.getNumeroSalon()) != null) {
                throw new PreexistingEntityException("Salon " + salon + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Salon salon) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Salon persistentSalon = em.find(Salon.class, salon.getNumeroSalon());
            Collection<Comodidad> comodidadCollectionOld = persistentSalon.getComodidadCollection();
            Collection<Comodidad> comodidadCollectionNew = salon.getComodidadCollection();
            Collection<Reservasalon> reservasalonCollectionOld = persistentSalon.getReservasalonCollection();
            Collection<Reservasalon> reservasalonCollectionNew = salon.getReservasalonCollection();
            List<String> illegalOrphanMessages = null;
            for (Reservasalon reservasalonCollectionOldReservasalon : reservasalonCollectionOld) {
                if (!reservasalonCollectionNew.contains(reservasalonCollectionOldReservasalon)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Reservasalon " + reservasalonCollectionOldReservasalon + " since its salon field is not nullable.");
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
            salon.setComodidadCollection(comodidadCollectionNew);
            Collection<Reservasalon> attachedReservasalonCollectionNew = new ArrayList<Reservasalon>();
            for (Reservasalon reservasalonCollectionNewReservasalonToAttach : reservasalonCollectionNew) {
                reservasalonCollectionNewReservasalonToAttach = em.getReference(reservasalonCollectionNewReservasalonToAttach.getClass(), reservasalonCollectionNewReservasalonToAttach.getReservasalonPK());
                attachedReservasalonCollectionNew.add(reservasalonCollectionNewReservasalonToAttach);
            }
            reservasalonCollectionNew = attachedReservasalonCollectionNew;
            salon.setReservasalonCollection(reservasalonCollectionNew);
            salon = em.merge(salon);
            for (Comodidad comodidadCollectionOldComodidad : comodidadCollectionOld) {
                if (!comodidadCollectionNew.contains(comodidadCollectionOldComodidad)) {
                    comodidadCollectionOldComodidad.getSalonCollection().remove(salon);
                    comodidadCollectionOldComodidad = em.merge(comodidadCollectionOldComodidad);
                }
            }
            for (Comodidad comodidadCollectionNewComodidad : comodidadCollectionNew) {
                if (!comodidadCollectionOld.contains(comodidadCollectionNewComodidad)) {
                    comodidadCollectionNewComodidad.getSalonCollection().add(salon);
                    comodidadCollectionNewComodidad = em.merge(comodidadCollectionNewComodidad);
                }
            }
            for (Reservasalon reservasalonCollectionNewReservasalon : reservasalonCollectionNew) {
                if (!reservasalonCollectionOld.contains(reservasalonCollectionNewReservasalon)) {
                    Salon oldSalonOfReservasalonCollectionNewReservasalon = reservasalonCollectionNewReservasalon.getSalon();
                    reservasalonCollectionNewReservasalon.setSalon(salon);
                    reservasalonCollectionNewReservasalon = em.merge(reservasalonCollectionNewReservasalon);
                    if (oldSalonOfReservasalonCollectionNewReservasalon != null && !oldSalonOfReservasalonCollectionNewReservasalon.equals(salon)) {
                        oldSalonOfReservasalonCollectionNewReservasalon.getReservasalonCollection().remove(reservasalonCollectionNewReservasalon);
                        oldSalonOfReservasalonCollectionNewReservasalon = em.merge(oldSalonOfReservasalonCollectionNewReservasalon);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = salon.getNumeroSalon();
                if (findSalon(id) == null) {
                    throw new NonexistentEntityException("The salon with id " + id + " no longer exists.");
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
            Salon salon;
            try {
                salon = em.getReference(Salon.class, id);
                salon.getNumeroSalon();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The salon with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Reservasalon> reservasalonCollectionOrphanCheck = salon.getReservasalonCollection();
            for (Reservasalon reservasalonCollectionOrphanCheckReservasalon : reservasalonCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Salon (" + salon + ") cannot be destroyed since the Reservasalon " + reservasalonCollectionOrphanCheckReservasalon + " in its reservasalonCollection field has a non-nullable salon field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Comodidad> comodidadCollection = salon.getComodidadCollection();
            for (Comodidad comodidadCollectionComodidad : comodidadCollection) {
                comodidadCollectionComodidad.getSalonCollection().remove(salon);
                comodidadCollectionComodidad = em.merge(comodidadCollectionComodidad);
            }
            em.remove(salon);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Salon> findSalonEntities() {
        return findSalonEntities(true, -1, -1);
    }

    public List<Salon> findSalonEntities(int maxResults, int firstResult) {
        return findSalonEntities(false, maxResults, firstResult);
    }

    private List<Salon> findSalonEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Salon.class));
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

    public Salon findSalon(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Salon.class, id);
        } finally {
            em.close();
        }
    }

    public int getSalonCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Salon> rt = cq.from(Salon.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
