package Persistencia;

import Modelo.OrganizacionSocial;

import java.util.List;

public interface OrganizacionSocialDao extends GenericDao<OrganizacionSocial,Long> {

    /**
     * Find social organizations by partial name match (LIKE).
     */
    List<OrganizacionSocial> findByName(String partialName);
    /**
     * Find social organizations by partial adress match (LIKE).
     */
    List<OrganizacionSocial> findByAddress(String partialAddress);
    /**
     * Count total number of social organizations.
     */
    Long countOrganizations();

    /**
     * Find social organizations by neighborhood ID.
     */
    List<OrganizacionSocial> findByBarrioId(Long barrioId);
}
