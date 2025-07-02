package Persistencia;

import Modelo.Jornada;
import jakarta.inject.Inject;
import jakarta.persistence.TypedQuery;

import java.util.List;
import jakarta.persistence.EntityManager;


public class JornadaDaoImp implements JornadaDao {
    @Inject
    private EntityManager em;

    @Override
    public void save(Jornada jornada) {
        em.getTransaction().begin();
        em.persist(jornada);
        em.getTransaction().commit();
    }

    @Override
    public Jornada findById(Long id) {
        return em.find(Jornada.class, id);
    }
    @Override
    public List<Jornada> findByDate(String date) {
        String jpql = "SELECT j FROM Jornada j WHERE j.fecha = :date";
        TypedQuery<Jornada> query = em.createQuery(jpql, Jornada.class);
        query.setParameter("date", date);
        return query.getResultList();
    }

    @Override
    public List<Jornada> findAll() {
        String jpql = "SELECT j FROM Jornada j";
        return em.createQuery(jpql, Jornada.class).getResultList();
    }

    @Override
    public void update(Jornada jornada) {
        em.getTransaction().begin();
        em.merge(jornada);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Jornada jornada) {
        em.getTransaction().begin();
        em.remove(em.contains(jornada) ? jornada : em.merge(jornada));
        em.getTransaction().commit();
    }

    @Override
    public void deleteById(Long id) {
        Jornada jornada = findById(id);
        if (jornada != null) {
            delete(jornada);
        }
    }
}

