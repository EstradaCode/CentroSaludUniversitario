package Persistencia;

import Modelo.Jornada;

import java.util.List;

public interface JornadaDao extends GenericDao<Jornada> {


    /**
     * Find active journeys (BETWEEN).
     */
    List<Jornada> findByDate(String date);

}
