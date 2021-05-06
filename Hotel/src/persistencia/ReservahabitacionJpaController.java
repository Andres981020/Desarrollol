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
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Habitacion;
import modelo.Reserva;
import modelo.Reservahabitacion;
import modelo.ReservahabitacionPK;
import modelo.Usuario;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author Usuario
 */
public class ReservahabitacionJpaController implements Serializable {

    public ReservahabitacionJpaController() {
        this.emf = Persistence.createEntityManagerFactory("HospitalPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reservahabitacion reservahabitacion) throws PreexistingEntityException, Exception {
        if (reservahabitacion.getReservahabitacionPK() == null) {
            reservahabitacion.setReservahabitacionPK(new ReservahabitacionPK());
        }
        reservahabitacion.getReservahabitacionPK().setCodReserva(reservahabitacion.getReserva().getCodigoR());
        reservahabitacion.getReservahabitacionPK().setNumHabitacion(reservahabitacion.getHabitacion().getNumeroHabitacion());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Habitacion habitacion = reservahabitacion.getHabitacion();
            if (habitacion != null) {
                habitacion = em.getReference(habitacion.getClass(), habitacion.getNumeroHabitacion());
                reservahabitacion.setHabitacion(habitacion);
            }
            Reserva reserva = reservahabitacion.getReserva();
            if (reserva != null) {
                reserva = em.getReference(reserva.getClass(), reserva.getCodigoR());
                reservahabitacion.setReserva(reserva);
            }
            em.persist(reservahabitacion);
            if (habitacion != null) {
                habitacion.getReservahabitacionCollection().add(reservahabitacion);
                habitacion = em.merge(habitacion);
            }
            if (reserva != null) {
                reserva.getReservahabitacionCollection().add(reservahabitacion);
                reserva = em.merge(reserva);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findReservahabitacion(reservahabitacion.getReservahabitacionPK()) != null) {
                throw new PreexistingEntityException("Reservahabitacion " + reservahabitacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reservahabitacion reservahabitacion) throws NonexistentEntityException, Exception {
        reservahabitacion.getReservahabitacionPK().setCodReserva(reservahabitacion.getReserva().getCodigoR());
        reservahabitacion.getReservahabitacionPK().setNumHabitacion(reservahabitacion.getHabitacion().getNumeroHabitacion());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reservahabitacion persistentReservahabitacion = em.find(Reservahabitacion.class, reservahabitacion.getReservahabitacionPK());
            Habitacion habitacionOld = persistentReservahabitacion.getHabitacion();
            Habitacion habitacionNew = reservahabitacion.getHabitacion();
            Reserva reservaOld = persistentReservahabitacion.getReserva();
            Reserva reservaNew = reservahabitacion.getReserva();
            if (habitacionNew != null) {
                habitacionNew = em.getReference(habitacionNew.getClass(), habitacionNew.getNumeroHabitacion());
                reservahabitacion.setHabitacion(habitacionNew);
            }
            if (reservaNew != null) {
                reservaNew = em.getReference(reservaNew.getClass(), reservaNew.getCodigoR());
                reservahabitacion.setReserva(reservaNew);
            }
            reservahabitacion = em.merge(reservahabitacion);
            if (habitacionOld != null && !habitacionOld.equals(habitacionNew)) {
                habitacionOld.getReservahabitacionCollection().remove(reservahabitacion);
                habitacionOld = em.merge(habitacionOld);
            }
            if (habitacionNew != null && !habitacionNew.equals(habitacionOld)) {
                habitacionNew.getReservahabitacionCollection().add(reservahabitacion);
                habitacionNew = em.merge(habitacionNew);
            }
            if (reservaOld != null && !reservaOld.equals(reservaNew)) {
                reservaOld.getReservahabitacionCollection().remove(reservahabitacion);
                reservaOld = em.merge(reservaOld);
            }
            if (reservaNew != null && !reservaNew.equals(reservaOld)) {
                reservaNew.getReservahabitacionCollection().add(reservahabitacion);
                reservaNew = em.merge(reservaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ReservahabitacionPK id = reservahabitacion.getReservahabitacionPK();
                if (findReservahabitacion(id) == null) {
                    throw new NonexistentEntityException("The reservahabitacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ReservahabitacionPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reservahabitacion reservahabitacion;
            try {
                reservahabitacion = em.getReference(Reservahabitacion.class, id);
                reservahabitacion.getReservahabitacionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reservahabitacion with id " + id + " no longer exists.", enfe);
            }
            Habitacion habitacion = reservahabitacion.getHabitacion();
            if (habitacion != null) {
                habitacion.getReservahabitacionCollection().remove(reservahabitacion);
                habitacion = em.merge(habitacion);
            }
            Reserva reserva = reservahabitacion.getReserva();
            if (reserva != null) {
                reserva.getReservahabitacionCollection().remove(reservahabitacion);
                reserva = em.merge(reserva);
            }
            em.remove(reservahabitacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reservahabitacion> findReservahabitacionEntities() {
        return findReservahabitacionEntities(true, -1, -1);
    }

    public List<Reservahabitacion> findReservahabitacionEntities(int maxResults, int firstResult) {
        return findReservahabitacionEntities(false, maxResults, firstResult);
    }

    private List<Reservahabitacion> findReservahabitacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reservahabitacion.class));
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

    public Reservahabitacion findReservahabitacion(ReservahabitacionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reservahabitacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getReservahabitacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reservahabitacion> rt = cq.from(Reservahabitacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public Reservahabitacion buscarPorReserva(int codReserva){
        String consulta = "SELECT r FROM Reservahabitacion r "
                + "WHERE r.reservahabitacionPK.codReserva = '"+codReserva+"'";
        try{
            EntityManager em = getEntityManager();
            Query query = em.createQuery(consulta);
            return (Reservahabitacion) query.getSingleResult();  
        }catch(NoResultException e){
            return null;
        }
    }
}
