package Persistencia;

import Modelo.Encuestador;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

public class EncuestadorDaoImpl implements EncuestadorDao{
    @Inject
    private EntityManager em;
    @Override
    public void save(Encuestador entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }
    @Override
    public Encuestador findById(Long id) {
        return em.find(Encuestador.class, id);
    }
    @Override
    public List<Encuestador> findAll() {
        return em.createQuery("SELECT e FROM Encuestador e", Encuestador.class).getResultList();
    }
    @Override
    public void update(Encuestador entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }
    @Override
    public void delete(Encuestador entity) {
        em.getTransaction().begin();
        em.remove(em.contains(entity) ? entity : em.merge(entity));
        em.getTransaction().commit();
    }
    @Override
    public void deleteById(Long id) {
        Encuestador entity = findById(id);
        if (entity != null) {
            delete(entity);
        }
    }
}
