/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Salon;
import modelo.Reserva;
import modelo.Reservasalon;
import modelo.ReservasalonPK;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author Usuario
 */
public class ReservasalonJpaController implements Serializable {

    public ReservasalonJpaController() {
        this.emf = Persistence.createEntityManagerFactory("HospitalPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reservasalon reservasalon) throws PreexistingEntityException, Exception {
        if (reservasalon.getReservasalonPK() == null) {
            reservasalon.setReservasalonPK(new ReservasalonPK());
        }
        reservasalon.getReservasalonPK().setNumSalon(reservasalon.getSalon().getNumeroSalon());
        reservasalon.getReservasalonPK().setCodReserva(reservasalon.getReserva().getCodigoR());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Salon salon = reservasalon.getSalon();
            if (salon != null) {
                salon = em.getReference(salon.getClass(), salon.getNumeroSalon());
                reservasalon.setSalon(salon);
            }
            Reserva reserva = reservasalon.getReserva();
            if (reserva != null) {
                reserva = em.getReference(reserva.getClass(), reserva.getCodigoR());
                reservasalon.setReserva(reserva);
            }
            em.persist(reservasalon);
            if (salon != null) {
                salon.getReservasalonCollection().add(reservasalon);
                salon = em.merge(salon);
            }
            if (reserva != null) {
                reserva.getReservasalonCollection().add(reservasalon);
                reserva = em.merge(reserva);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findReservasalon(reservasalon.getReservasalonPK()) != null) {
                throw new PreexistingEntityException("Reservasalon " + reservasalon + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reservasalon reservasalon) throws NonexistentEntityException, Exception {
        reservasalon.getReservasalonPK().setNumSalon(reservasalon.getSalon().getNumeroSalon());
        reservasalon.getReservasalonPK().setCodReserva(reservasalon.getReserva().getCodigoR());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reservasalon persistentReservasalon = em.find(Reservasalon.class, reservasalon.getReservasalonPK());
            Salon salonOld = persistentReservasalon.getSalon();
            Salon salonNew = reservasalon.getSalon();
            Reserva reservaOld = persistentReservasalon.getReserva();
            Reserva reservaNew = reservasalon.getReserva();
            if (salonNew != null) {
                salonNew = em.getReference(salonNew.getClass(), salonNew.getNumeroSalon());
                reservasalon.setSalon(salonNew);
            }
            if (reservaNew != null) {
                reservaNew = em.getReference(reservaNew.getClass(), reservaNew.getCodigoR());
                reservasalon.setReserva(reservaNew);
            }
            reservasalon = em.merge(reservasalon);
            if (salonOld != null && !salonOld.equals(salonNew)) {
                salonOld.getReservasalonCollection().remove(reservasalon);
                salonOld = em.merge(salonOld);
            }
            if (salonNew != null && !salonNew.equals(salonOld)) {
                salonNew.getReservasalonCollection().add(reservasalon);
                salonNew = em.merge(salonNew);
            }
            if (reservaOld != null && !reservaOld.equals(reservaNew)) {
                reservaOld.getReservasalonCollection().remove(reservasalon);
                reservaOld = em.merge(reservaOld);
            }
            if (reservaNew != null && !reservaNew.equals(reservaOld)) {
                reservaNew.getReservasalonCollection().add(reservasalon);
                reservaNew = em.merge(reservaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ReservasalonPK id = reservasalon.getReservasalonPK();
                if (findReservasalon(id) == null) {
                    throw new NonexistentEntityException("The reservasalon with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ReservasalonPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reservasalon reservasalon;
            try {
                reservasalon = em.getReference(Reservasalon.class, id);
                reservasalon.getReservasalonPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reservasalon with id " + id + " no longer exists.", enfe);
            }
            Salon salon = reservasalon.getSalon();
            if (salon != null) {
                salon.getReservasalonCollection().remove(reservasalon);
                salon = em.merge(salon);
            }
            Reserva reserva = reservasalon.getReserva();
            if (reserva != null) {
                reserva.getReservasalonCollection().remove(reservasalon);
                reserva = em.merge(reserva);
            }
            em.remove(reservasalon);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reservasalon> findReservasalonEntities() {
        return findReservasalonEntities(true, -1, -1);
    }

    public List<Reservasalon> findReservasalonEntities(int maxResults, int firstResult) {
        return findReservasalonEntities(false, maxResults, firstResult);
    }

    private List<Reservasalon> findReservasalonEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reservasalon.class));
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

    public Reservasalon findReservasalon(ReservasalonPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reservasalon.class, id);
        } finally {
            em.close();
        }
    }

    public int getReservasalonCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reservasalon> rt = cq.from(Reservasalon.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
