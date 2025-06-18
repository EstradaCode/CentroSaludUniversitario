package Persistencia;


import Modelo.Encuesta;
import java.util.List;

public interface EncuestaDao extends GenericDao<Encuesta> {

    /**
     * Buscar encuestas por fecha de creación exacta.
     */
    List<Encuesta> findByFechaCreacion(String fecha);

    /**
     * Buscar encuestas por id de encuestador.
     */
    List<Encuesta> findByEncuestador(Long idEncuestador);

    /**
     * Buscar encuestas que contengan una respuesta específica a una pregunta dada.
     *
     * @param idPregunta id de la pregunta
     * @param respuesta valor de la respuesta a buscar
     * @return lista de encuestas que tengan esa respuesta para esa pregunta
     */
    List<Encuesta> findByRespuesta(Long idPregunta, String respuesta);

    /**
     * Buscar encuestas que contengan respuestas para una pregunta específica, sin importar la respuesta.
     *
     * @param idPregunta id de la pregunta
     * @return lista de encuestas que tengan una respuesta a esa pregunta
     */
    List<Encuesta> findByPregunta(Long idPregunta);

    /**
     * Contar la cantidad total de respuestas para una pregunta específica (útil para estadísticas).
     *
     * @param idPregunta id de la pregunta
     * @return cantidad total de respuestas registradas para esa pregunta
     */
    Long countRespuestasByPregunta(Long idPregunta);
}