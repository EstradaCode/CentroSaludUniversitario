package Persistencia;

import Modelo.Campania;
import Modelo.OrganizacionSocial;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class OrganizacionSocialDaoImpl implements OrganizacionSocialDao {
    @Inject
    private EntityManager em;
    @Override
    public void save(OrganizacionSocial entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    @Override
    public OrganizacionSocial findById(Long id) {
        return em.find(OrganizacionSocial.class, id);
    }

    @Override
    public List<OrganizacionSocial> findByBarrioId(Long barrioId) {
        String jpql = "SELECT o FROM OrganizacionSocial o WHERE o.barrio.id = :barrioId";
        TypedQuery<OrganizacionSocial> query = em.createQuery(jpql, OrganizacionSocial.class);
        query.setParameter("barrioId", barrioId);
        return query.getResultList();
    }

    @Override
    public List<OrganizacionSocial> findByName(String partialName) {
        String jpql = "SELECT o FROM OrganizacionSocial o WHERE LOWER(o.nombre) LIKE LOWER(CONCAT('%', :name, '%'))";
        TypedQuery<OrganizacionSocial> query = em.createQuery(jpql, OrganizacionSocial.class);
        query.setParameter("name", partialName);
        return query.getResultList();
    }

    @Override
    public List<OrganizacionSocial> findByAddress(String address) {
        String jpql = "SELECT o FROM OrganizacionSocial o WHERE LOWER(o.domicilio) LIKE LOWER(CONCAT('%', :address, '%'))";
        TypedQuery<OrganizacionSocial> query = em.createQuery(jpql, OrganizacionSocial.class);
        query.setParameter("address", address);
        return query.getResultList();
    }

    @Override
    public List<OrganizacionSocial> findAll() {
        String jpql = "SELECT o FROM OrganizacionSocial o";
        TypedQuery<OrganizacionSocial> query = em.createQuery(jpql, OrganizacionSocial.class);
        return query.getResultList();
    }

    @Override
    public void update(OrganizacionSocial entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    @Override
    public void delete(OrganizacionSocial entity) {
        em.getTransaction().begin();
        em.remove(em.contains(entity) ? entity : em.merge(entity));
        em.getTransaction().commit();
    }

    @Override
    public void deleteById(Long id) {
        OrganizacionSocial entity = em.find(OrganizacionSocial.class, id);
        if (entity != null) {
            delete(entity);
        }
    }

    @Override
    public Long countOrganizations() {
        String jpql = "SELECT COUNT(o) FROM OrganizacionSocial o";
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        return query.getSingleResult();
    }
}

