package Persistencia;

import Modelo.Encuesta;
import jakarta.persistence.EntityManager;

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

    @Override
    public List<Encuesta> findByFechaCreacion(String fecha) {
        String jpql = "SELECT e FROM Encuesta e WHERE e.fechaCreacion = :fecha";
        return em.createQuery(jpql, Encuesta.class)
                .setParameter("fecha", fecha)
                .getResultList();
    }

    @Override
    public List<Encuesta> findByEncuestador(Long idEncuestador) {
        String jpql = "SELECT e FROM Encuesta e WHERE e.encuestador.id = :idEnc";
        return em.createQuery(jpql, Encuesta.class)
                .setParameter("idEnc", idEncuestador)
                .getResultList();
    }

    @Override
    public List<Encuesta> findByRespuesta(Long idPregunta, String respuesta) {
        String jpql = "SELECT e FROM Encuesta e JOIN e.respuestas r WHERE KEY(r).id = :idPregunta AND r = :respuesta";
        return em.createQuery(jpql, Encuesta.class)
                .setParameter("idPregunta", idPregunta)
                .setParameter("respuesta", respuesta)
                .getResultList();
    }

    @Override
    public List<Encuesta> findByPregunta(Long idPregunta) {
        String jpql = "SELECT e FROM Encuesta e JOIN e.respuestas r WHERE KEY(r).id = :idPregunta";
        return em.createQuery(jpql, Encuesta.class)
                .setParameter("idPregunta", idPregunta)
                .getResultList();
    }

    @Override
    public Long countRespuestasByPregunta(Long idPregunta) {
        String jpql = "SELECT COUNT(r) FROM Encuesta e JOIN e.respuestas r WHERE KEY(r).id = :idPregunta";
        return em.createQuery(jpql, Long.class)
                .setParameter("idPregunta", idPregunta)
                .getSingleResult();
    }
}