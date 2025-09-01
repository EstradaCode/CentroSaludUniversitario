package Persistencia;

import Modelo.Zona;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

@RequestScoped
public class ZonaDaoImpl implements ZonaDao{
    @Inject
    private EntityManager em;
    @Override
    public void save(Zona entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    @Override
    public Optional<Zona> findById(Long id) {
        return Optional.ofNullable(em.find(Zona.class, id));
    }

    @Override
    public List<Zona> findByNombre(String name) {
        String jpql = "SELECT z FROM Zona z WHERE LOWER(z.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))";
        TypedQuery<Zona> query = em.createQuery(jpql, Zona.class);
        query.setParameter("nombre", name);
        return query.getResultList();
    }

    @Override
    public List<Zona> findAll() {
        String jpql = "SELECT z FROM Zona z";
        TypedQuery<Zona> query = em.createQuery(jpql, Zona.class);
        return query.getResultList();
    }

    @Override
    public void update(Zona entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Zona entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }

    @Override
    public void deleteById(Long id) {
        findById(id).ifPresent(this::delete);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }
}
