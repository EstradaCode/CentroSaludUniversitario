package Persistencia;


import Modelo.Encuesta;
import Utils.Enums.AtencionSalud;
import Utils.Enums.TipoVivienda;

import java.util.List;

import java.util.Date;

public interface EncuestaDao extends GenericDao<Encuesta, Long> {

    /**
     * Guardar todas las encuestas.
     */
    void saveAll(List<Encuesta> encuestas);
    /**
     * Buscar encuestas por fecha de creación exacta.
     */
    List<Encuesta> findByFechaCreacion(Date fecha);

    /**
     * Buscar encuestas por UUID externo (identificador API).
     */
    List<Encuesta> findByUuidApi(String uuidApi);

    /**
     * Buscar encuestas por tipo de vivienda.
     */
    List<Encuesta> findByTipoVivienda(TipoVivienda tipoVivienda);

    /**
     * Contar encuestas según acceso a agua.
     */
    Long countByAccesoAgua(Boolean accesoAgua);

    /**
     * Contar encuestas según disponibilidad de agua potable.
     */
    Long countByAguaPotable(Boolean aguaPotable);

    /**
     * Contar encuestas según acceso a salud.
     */
    Long countByAccesoSalud(AtencionSalud accesoSalud);

    // Si necesitás, podés agregar más métodos específicos según tus filtros y consultas.
}
