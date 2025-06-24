package Servicios.Negocio;

import Modelo.Encuesta;
import Modelo.Jornada;
import Persistencia.JornadaDao;
import Persistencia.JornadaDaoImp;
import jakarta.persistence.EntityManager;

import java.util.List;

public class JornadaService {

    private JornadaDao jornadaDao;
    private EncuestaService encuestaService;
    public JornadaService(EntityManager em) {
        this.jornadaDao = new JornadaDaoImp(em);
    }

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
