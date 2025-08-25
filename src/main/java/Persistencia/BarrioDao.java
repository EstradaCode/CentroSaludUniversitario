package Persistencia;

import Modelo.Barrio;

import java.util.List;

public interface BarrioDao extends GenericDao<Barrio,Long> {

    /**
     * Find neighborhoods by partial name match (LIKE).
     */
    List<Barrio> findByName(String partialName);

    /**
     * Count total number of neighborhoods.
     */
    Long countNeighborhoods();
}
