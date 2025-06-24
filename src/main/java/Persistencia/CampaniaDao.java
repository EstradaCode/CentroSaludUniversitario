package Persistencia;

import Modelo.Campania;

import java.time.LocalDate;
import java.util.List;

public interface CampaniaDao extends GenericDao<Campania> /* extends GenericDao<Campania> si querés */ {

    /**
     * Find campaigns active within a date range (e.g. campaigns currently ongoing).
     */
    List<Campania> findActiveCampaigns(LocalDate date);

    /**
     * Find campaigns by partial name match (LIKE).
     */
    List<Campania> findByName(String partialName);

    /**
     * Find campaigns associated with a specific social organization.
     */
    List<Campania> findByOrganization(Long organizationId);

    /**
     * Find campaigns that include a specific neighborhood.
     */
    List<Campania> findByNeighborhood(Long neighborhoodId);

    /**
     * Get campaigns that have assigned surveyors.
     */
    List<Campania> findWithSurveyors();

    /**
     * Count total number of campaigns.
     */
    Long countCampaigns();

}
