package Persistencia;

import Modelo.Campania;



import java.util.List;



import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class CampaniaDaoImp implements CampaniaDao {

    private EntityManager em;

    public CampaniaDaoImp(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Campania entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    @Override
    public Campania findById(Long id) {
        return em.find(Campania.class, id);
    }

    @Override
    public List<Campania> findAll() {
        return em.createQuery("SELECT c FROM Campania c", Campania.class).getResultList();
    }

    @Override
    public void update(Campania entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Campania entity) {
        em.getTransaction().begin();
        em.remove(em.contains(entity) ? entity : em.merge(entity));
        em.getTransaction().commit();
    }

    @Override
    public void deleteById(Long id) {
        Campania c = findById(id);
        if (c != null) {
            delete(c);
        }
    }

    @Override
    public List<Campania> findActiveCampaigns(String date) {
        String jpql = "SELECT c FROM Campania c WHERE :date BETWEEN c.fechaInicio AND c.fechaFin";
        TypedQuery<Campania> query = em.createQuery(jpql, Campania.class);
        query.setParameter("date", date);
        return query.getResultList();
    }

    @Override
    public List<Campania> findByName(String partialName) {
        String jpql = "SELECT c FROM Campania c WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%', :name, '%'))";
        TypedQuery<Campania> query = em.createQuery(jpql, Campania.class);
        query.setParameter("name", partialName);
        return query.getResultList();
    }

    @Override
    public List<Campania> findByOrganization(Long organizationId) {
        String jpql = "SELECT c FROM Campania c WHERE c.organizacionSocial.id = :orgId";
        TypedQuery<Campania> query = em.createQuery(jpql, Campania.class);
        query.setParameter("orgId", organizationId);
        return query.getResultList();
    }

    @Override
    public List<Campania> findByNeighborhood(Long neighborhoodId) {
        String jpql = "SELECT c FROM Campania c JOIN c.zona z WHERE z.id = :neighborhoodId";
        TypedQuery<Campania> query = em.createQuery(jpql, Campania.class);
        query.setParameter("neighborhoodId", neighborhoodId);
        return query.getResultList();
    }

    @Override
    public List<Campania> findWithSurveyors() {
        String jpql = "SELECT DISTINCT c FROM Campania c JOIN c.encuestadores e";
        TypedQuery<Campania> query = em.createQuery(jpql, Campania.class);
        return query.getResultList();
    }

    @Override
    public Long countCampaigns() {
        String jpql = "SELECT COUNT(c) FROM Campania c";
        return em.createQuery(jpql, Long.class).getSingleResult();
    }
}

