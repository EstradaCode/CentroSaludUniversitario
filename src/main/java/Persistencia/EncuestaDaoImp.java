package Persistencia;

import Modelo.Encuesta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.Date;
import java.util.List;

public class EncuestaDaoImp implements EncuestaDao {

    private EntityManager em;

    public EncuestaDaoImp(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Encuesta entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }
    @Override
    public void saveAll(List<Encuesta> entities) {
        em.getTransaction().begin();
        for (Encuesta entity : entities) {
            em.persist(entity);
        }
        em.getTransaction().commit();
    }
    @Override
    public Encuesta findById(Long id) {
        return em.find(Encuesta.class, id);
    }

    @Override
    public List<Encuesta> findAll() {
        return em.createQuery("SELECT e FROM Encuesta e", Encuesta.class).getResultList();
    }

    @Override
    public void update(Encuesta entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Encuesta entity) {
        em.getTransaction().begin();
        em.remove(em.contains(entity) ? entity : em.merge(entity));
        em.getTransaction().commit();
    }

    @Override
    public void deleteById(Long id) {
        Encuesta e = findById(id);
        if (e != null) {
            delete(e);
        }
    }

    // Nuevos métodos adaptados

    @Override
    public List<Encuesta> findByFechaCreacion(Date fecha) {
        String jpql = "SELECT e FROM Encuesta e WHERE DATE(e.fechaCreacion) = DATE(:fecha)";
        TypedQuery<Encuesta> query = em.createQuery(jpql, Encuesta.class);
        query.setParameter("fecha", fecha);
        return query.getResultList();
    }

    @Override
    public List<Encuesta> findByUuidApi(String uuidApi) {
        String jpql = "SELECT e FROM Encuesta e WHERE e.uuidApi = :uuidApi";
        TypedQuery<Encuesta> query = em.createQuery(jpql, Encuesta.class);
        query.setParameter("uuidApi", uuidApi);
        return query.getResultList();
    }

    @Override
    public List<Encuesta> findByTipoVivienda(String tipoVivienda) {
        String jpql = "SELECT e FROM Encuesta e WHERE e.tipoVivienda = :tipoVivienda";
        TypedQuery<Encuesta> query = em.createQuery(jpql, Encuesta.class);
        query.setParameter("tipoVivienda", tipoVivienda);
        return query.getResultList();
    }

    @Override
    public Long countByAccesoAgua(Boolean accesoAgua) {
        String jpql = "SELECT COUNT(e) FROM Encuesta e WHERE e.accesoAgua = :accesoAgua";
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        query.setParameter("accesoAgua", accesoAgua);
        return query.getSingleResult();
    }

    @Override
    public Long countByAguaPotable(Boolean aguaPotable) {
        String jpql = "SELECT COUNT(e) FROM Encuesta e WHERE e.aguaPotable = :aguaPotable";
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        query.setParameter("aguaPotable", aguaPotable);
        return query.getSingleResult();
    }

    @Override
    public Long countByAccesoSalud(Boolean accesoSalud) {
        String jpql = "SELECT COUNT(e) FROM Encuesta e WHERE e.accesoSalud = :accesoSalud";
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        query.setParameter("accesoSalud", accesoSalud);
        return query.getSingleResult();
    }
}
