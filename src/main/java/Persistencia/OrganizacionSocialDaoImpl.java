package Persistencia;

import Modelo.Campania;
import Modelo.OrganizacionSocial;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class OrganizacionSocialDaoImpl implements OrganizacionSocialDao{
    // Implementación de los métodos de la interfaz OrganizacionSocialDao
    // Aquí se pueden usar JPA, Hibernate o cualquier otra tecnología de persistencia
    private final EntityManager em;
    public OrganizacionSocialDaoImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public void save(OrganizacionSocial entity) {
        // Lógica para guardar una organización social
    }

    @Override
    public OrganizacionSocial findById(Long id) {
        // Lógica para encontrar una organización social por ID
        return null; // Reemplazar con la implementación real
    }
    public List<OrganizacionSocial> findByBarrioId(Long barrioId) {
        String jpql = "SELECT o FROM OrganizacionSocial o WHERE o.barrio.id = :barrioId";
        TypedQuery<OrganizacionSocial> query = em.createQuery(jpql, OrganizacionSocial.class);
        query.setParameter("barrioId", barrioId);
        return query.getResultList();
    }
    public List<OrganizacionSocial> findByName(String partialName){
        String jpql = "SELECT o FROM OrganizacionSocial o WHERE LOWER(o.nombre) LIKE LOWER(CONCAT('%', :name, '%'))";
        TypedQuery<OrganizacionSocial> query = em.createQuery(jpql, OrganizacionSocial.class);
        query.setParameter("name", partialName);
        return query.getResultList();
    }
    public List<OrganizacionSocial> findByAddress(String address){
        String jpql = "SELECT o FROM OrganizacionSocial o WHERE LOWER(o.domicilio) LIKE LOWER(CONCAT('%', :address, '%'))";
        TypedQuery<OrganizacionSocial> query = em.createQuery(jpql, OrganizacionSocial.class);
        query.setParameter("address", address);
        return query.getResultList();
    }

    @Override
    public List<OrganizacionSocial> findAll() {
        // Lógica para encontrar todas las organizaciones sociales
        return null; // Reemplazar con la implementación real
    }

    @Override
    public void update(OrganizacionSocial entity) {
        // Lógica para actualizar una organización social
    }

    @Override
    public void delete(OrganizacionSocial entity) {
        // Lógica para eliminar una organización social por entidad
    }

    @Override
    public void deleteById(Long id) {
        // Lógica para eliminar una organización social por ID
    }
    @Override
    public Long countOrganizations() {
        String jpql = "SELECT COUNT(o) FROM OrganizacionSocial o";
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        return query.getSingleResult();
    }
}
