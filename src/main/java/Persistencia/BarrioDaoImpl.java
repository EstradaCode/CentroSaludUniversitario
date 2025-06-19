package Persistencia;

import Modelo.Barrio;
import Modelo.Campania;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class BarrioDaoImpl implements BarrioDao{
    // Implementación de los métodos de BarrioDao
    private EntityManager em;
    public BarrioDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Barrio entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    @Override
    public Barrio findById(Long id) {
        return em.find(Barrio.class, id);
    }

    @Override
    public List<Barrio> findAll() {
        return em.createQuery("SELECT b FROM Campania b", Barrio.class).getResultList();
    }

    @Override
    public void update(Barrio entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Barrio entity) {
        em.getTransaction().begin();
        em.remove(em.contains(entity) ? entity : em.merge(entity));
        em.getTransaction().commit();
    }

    @Override
    public void deleteById(Long id) {
        Barrio b = findById(id);
        if (b != null) {
            delete(b);
        }
    }

    @Override
    public List<Barrio> findByName(String partialName) {
        String jpql = "SELECT b FROM Barrio b WHERE LOWER(b.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))";
        TypedQuery<Barrio> query = em.createQuery(jpql, Barrio.class);
        query.setParameter("nombre", partialName);
        return query.getResultList();
    }

    @Override
    public Long countNeighborhoods() {
        String jpql = "SELECT COUNT(b) FROM Barrio b";
        return em.createQuery(jpql, Long.class).getSingleResult();
    }
}
