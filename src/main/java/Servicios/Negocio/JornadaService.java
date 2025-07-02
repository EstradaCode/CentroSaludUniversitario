package Servicios.Negocio;

import Modelo.Encuesta;
import Modelo.Jornada;
import Persistencia.JornadaDao;

import jakarta.inject.Inject;


import java.util.List;

public class JornadaService {
    @Inject
    private JornadaDao jornadaDao;
    @Inject
    private EncuestaService encuestaService;

    public void crearJornada(Jornada jornada) {
        jornadaDao.save(jornada);
    }

    public Jornada buscarJornadaPorId(Long id) {
        return jornadaDao.findById(id);
    }

    public List<Jornada> buscarJornadasPorFecha(String fecha) {
        return jornadaDao.findByDate(fecha);
    }

    public List<Jornada> obtenerTodasLasJornadas() {
        return jornadaDao.findAll();
    }

    public void actualizarJornada(Jornada jornada) {
        jornadaDao.update(jornada);
    }

    public void eliminarJornada(Jornada jornada) {
        jornadaDao.delete(jornada);
    }

    public void eliminarJornadaPorId(Long id) {
        jornadaDao.deleteById(id);
    }
    public void agregarEncuesta(Long jornadaId, Encuesta encuesta) {
        Jornada jornada = jornadaDao.findById(jornadaId);
        if (jornada != null) {
            encuestaService.crearEncuesta(encuesta); // usa el service hijo
            jornada.getEncuestas().add(encuesta);
            jornadaDao.update(jornada);
        }
    }
}
