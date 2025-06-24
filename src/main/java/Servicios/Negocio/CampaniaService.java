package Servicios.Negocio;

import Modelo.Campania;
import Modelo.Jornada;
import Persistencia.CampaniaDao;
import Persistencia.CampaniaDaoImp;

import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class CampaniaService {

    private CampaniaDao campaniaDao;
    private JornadaService jornadaService;

    public CampaniaService(EntityManager em) {
        this.campaniaDao = new CampaniaDaoImp(em);
    }

    public void crearCampania(Campania campania) {
        campaniaDao.save(campania);
    }

    public Campania obtenerCampaniaPorId(Long id) {
        return campaniaDao.findById(id);
    }

    public List<Campania> listarCampanias() {
        return campaniaDao.findAll();
    }

    public void actualizarCampania(Campania campania) {
        campaniaDao.update(campania);
    }

    public void eliminarCampania(Long id) {
        campaniaDao.deleteById(id);
    }

    public List<Campania> buscarCampaniasActivas(LocalDate fecha) {
        return campaniaDao.findActiveCampaigns(fecha);
    }

    public List<Campania> buscarPorNombre(String nombreParcial) {
        return campaniaDao.findByName(nombreParcial);
    }

    public List<Campania> buscarPorOrganizacion(Long idOrganizacion) {
        return campaniaDao.findByOrganization(idOrganizacion);
    }

    public List<Campania> buscarPorBarrio(Long idBarrio) {
        return campaniaDao.findByNeighborhood(idBarrio);
    }

    public List<Campania> listarCampaniasConEncuestadores() {
        return campaniaDao.findWithSurveyors();
    }

    public Long contarCampanias() {
        return campaniaDao.countCampaigns();
    }
    public void agregarJornada(Long campaniaId, Jornada jornada) {
        Campania campania = campaniaDao.findById(campaniaId);
        if (campania != null) {
            jornadaService.crearJornada(jornada); // usa el service hijo
            campania.getJornadas().add(jornada);
            campaniaDao.update(campania);
        }
    }
    public JornadaService getJornadaService() {
        return jornadaService;
    }
}

