package Servicios.Implementacion;

import Dtos.Campania.DetailCampaniaResponseDTO;
import Dtos.Campania.ListCampaniaResponseDTO;
import Dtos.Usuario.PageResponse;
import Modelo.Campania;
import Modelo.Jornada;
import Persistencia.CampaniaDao;

import Servicios.Negocio.JornadaService;
import Utils.mappers.CampaniaMapper;
import jakarta.inject.Inject;

import java.time.LocalDate;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.NoSuchElementException;

@ApplicationScoped
public class CampaniaService {

    @Inject
    private CampaniaDao campaniaDao;

    @Inject
    private JornadaService jornadaService;

    @Inject
    private CampaniaMapper campaniaMapper;

    // -----------------------
    // Métodos nuevos (DTOs)
    // -----------------------

    public DetailCampaniaResponseDTO obtenerCampaniaDetalle(Long id) {
        Campania c = campaniaDao.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Campaña no encontrada: " + id));
        return campaniaMapper.toDetailDTO(c);
    }

    public PageResponse<ListCampaniaResponseDTO> listar(int page, int size, String sort, String q) {
        if (size <= 0) size = 20;
        if (size > 100) size = 100;
        if (page < 0) page = 0;

        int offset = page * size;

        List<ListCampaniaResponseDTO> content = campaniaDao.findPageToListDTO(sort, q, offset, size);
        long total = campaniaDao.count(q);

        return new PageResponse<>(content, total, page, size);
    }


    public void crearCampania(Campania campania) {
        campaniaDao.save(campania);
    }

    public Campania obtenerCampaniaPorId(Long id) {
        return campaniaDao.findById(id).orElse(null);
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
        Campania campania = campaniaDao.findById(campaniaId)
                .orElseThrow(() -> new NoSuchElementException("Campaña no encontrada: " + campaniaId));

        // asegurar lado dueño de la relación
        jornada.setCampania(campania);

        // persistir jornada via service hijo
        jornadaService.crearJornada(jornada);

        // mantener consistencia en memoria si la colección existe
        if (campania.getJornadas() != null) {
            campania.getJornadas().add(jornada);
        }

        campaniaDao.update(campania);
    }

    public JornadaService getJornadaService() {
        return jornadaService;
    }
}


