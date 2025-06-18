package Persistencia;

import Modelo.Persona;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PersonaDaoImp implements GenericDao <Persona> {
    private EntityManager em;

    public PersonaDaoImp(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Persona persona) {
        em.getTransaction().begin();
        em.persist(persona);
        em.getTransaction().commit();
    }

    @Override
    public Persona findById(Long id) {
        return em.find(Persona.class, id);
    }

    @Override
    public List<Persona> findAll() {
        return em.createQuery("SELECT p FROM Persona p", Persona.class).getResultList();
    }

    @Override
    public void update(Persona persona) {
        em.getTransaction().begin();
        em.merge(persona);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Persona persona) {
        em.getTransaction().begin();
        em.remove(em.contains(persona) ? persona : em.merge(persona));
        em.getTransaction().commit();
    }

    @Override
    public void deleteById(Long id) {
        Persona persona = em.find(Persona.class, id);
        if (persona != null) {
            delete(persona);
        }

    }
}
