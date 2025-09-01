package Servicios.Negocio;

import Modelo.Encuesta;
import Persistencia.EncuestaDao;
import Persistencia.EncuestaDaoImp;
import Utils.Enums.AtencionSalud;
import Utils.Enums.TipoVivienda;
import jakarta.inject.Inject;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class EncuestaService {
    @Inject
    private EncuestaDao encuestaDao;

    // Crear nueva encuesta
    public void crearEncuesta(Encuesta encuesta) {
        encuestaDao.save(encuesta);
    }

    //Crear nuevas encuestas
    public void crearEncuestas(List<Encuesta> encuestas) {
        encuestaDao.saveAll(encuestas);
    }

    // Buscar encuesta por ID
    public Optional<Encuesta> obtenerEncuestaPorId(Long id) {
        return encuestaDao.findById(id);
    }

    // Obtener todas las encuestas
    public List<Encuesta> obtenerTodasLasEncuestas() {
        return encuestaDao.findAll();
    }

    // Actualizar encuesta existente
    public void actualizarEncuesta(Encuesta encuesta) {
        encuestaDao.update(encuesta);
    }

    // Eliminar encuesta
    public void eliminarEncuesta(Encuesta encuesta) {
        encuestaDao.delete(encuesta);
    }

    // Buscar encuestas por fecha de creación
    public List<Encuesta> buscarPorFechaCreacion(Date fecha) {
        return encuestaDao.findByFechaCreacion(fecha);
    }

    // Buscar encuestas por UUID externo (API)
    public List<Encuesta> buscarPorUuidApi(String uuidApi) {
        return encuestaDao.findByUuidApi(uuidApi);
    }

    // Buscar encuestas por tipo de vivienda
    public List<Encuesta> buscarPorTipoVivienda(TipoVivienda tipoVivienda) {
        return encuestaDao.findByTipoVivienda(tipoVivienda);
    }

    // Estadísticas - contar encuestas con acceso a agua
    public Long contarPorAccesoAgua(Boolean accesoAgua) {
        return encuestaDao.countByAccesoAgua(accesoAgua);
    }

    // Estadísticas - contar encuestas con agua potable
    public Long contarPorAguaPotable(Boolean aguaPotable) {
        return encuestaDao.countByAguaPotable(aguaPotable);
    }

    // Estadísticas - contar encuestas con acceso a salud
    public Long contarPorAccesoSalud(AtencionSalud accesoSalud) {
        return encuestaDao.countByAccesoSalud(accesoSalud);
    }

}

