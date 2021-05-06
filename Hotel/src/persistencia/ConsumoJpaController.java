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
import modelo.Consumo;
import modelo.ConsumoPK;
import modelo.Producto;
import modelo.Reserva;
import persistencia.exceptions.NonexistentEntityException;
import persistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author Usuario
 */
public class ConsumoJpaController implements Serializable {

    public ConsumoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("HospitalPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Consumo consumo) throws PreexistingEntityException, Exception {
        if (consumo.getConsumoPK() == null) {
            consumo.setConsumoPK(new ConsumoPK());
        }
        consumo.getConsumoPK().setCodReserva(consumo.getReserva().getCodigoR());
        consumo.getConsumoPK().setCodProducto(consumo.getProducto().getCodigoProducto());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto producto = consumo.getProducto();
            if (producto != null) {
                producto = em.getReference(producto.getClass(), producto.getCodigoProducto());
                consumo.setProducto(producto);
            }
            Reserva reserva = consumo.getReserva();
            if (reserva != null) {
                reserva = em.getReference(reserva.getClass(), reserva.getCodigoR());
                consumo.setReserva(reserva);
            }
            em.persist(consumo);
            if (producto != null) {
                producto.getConsumoCollection().add(consumo);
                producto = em.merge(producto);
            }
            if (reserva != null) {
                reserva.getConsumoCollection().add(consumo);
                reserva = em.merge(reserva);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findConsumo(consumo.getConsumoPK()) != null) {
                throw new PreexistingEntityException("Consumo " + consumo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Consumo consumo) throws NonexistentEntityException, Exception {
        consumo.getConsumoPK().setCodReserva(consumo.getReserva().getCodigoR());
        consumo.getConsumoPK().setCodProducto(consumo.getProducto().getCodigoProducto());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Consumo persistentConsumo = em.find(Consumo.class, consumo.getConsumoPK());
            Producto productoOld = persistentConsumo.getProducto();
            Producto productoNew = consumo.getProducto();
            Reserva reservaOld = persistentConsumo.getReserva();
            Reserva reservaNew = consumo.getReserva();
            if (productoNew != null) {
                productoNew = em.getReference(productoNew.getClass(), productoNew.getCodigoProducto());
                consumo.setProducto(productoNew);
            }
            if (reservaNew != null) {
                reservaNew = em.getReference(reservaNew.getClass(), reservaNew.getCodigoR());
                consumo.setReserva(reservaNew);
            }
            consumo = em.merge(consumo);
            if (productoOld != null && !productoOld.equals(productoNew)) {
                productoOld.getConsumoCollection().remove(consumo);
                productoOld = em.merge(productoOld);
            }
            if (productoNew != null && !productoNew.equals(productoOld)) {
                productoNew.getConsumoCollection().add(consumo);
                productoNew = em.merge(productoNew);
            }
            if (reservaOld != null && !reservaOld.equals(reservaNew)) {
                reservaOld.getConsumoCollection().remove(consumo);
                reservaOld = em.merge(reservaOld);
            }
            if (reservaNew != null && !reservaNew.equals(reservaOld)) {
                reservaNew.getConsumoCollection().add(consumo);
                reservaNew = em.merge(reservaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ConsumoPK id = consumo.getConsumoPK();
                if (findConsumo(id) == null) {
                    throw new NonexistentEntityException("The consumo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ConsumoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Consumo consumo;
            try {
                consumo = em.getReference(Consumo.class, id);
                consumo.getConsumoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The consumo with id " + id + " no longer exists.", enfe);
            }
            Producto producto = consumo.getProducto();
            if (producto != null) {
                producto.getConsumoCollection().remove(consumo);
                producto = em.merge(producto);
            }
            Reserva reserva = consumo.getReserva();
            if (reserva != null) {
                reserva.getConsumoCollection().remove(consumo);
                reserva = em.merge(reserva);
            }
            em.remove(consumo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Consumo> findConsumoEntities() {
        return findConsumoEntities(true, -1, -1);
    }

    public List<Consumo> findConsumoEntities(int maxResults, int firstResult) {
        return findConsumoEntities(false, maxResults, firstResult);
    }

    private List<Consumo> findConsumoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Consumo.class));
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

    public Consumo findConsumo(ConsumoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Consumo.class, id);
        } finally {
            em.close();
        }
    }

    public int getConsumoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Consumo> rt = cq.from(Consumo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
