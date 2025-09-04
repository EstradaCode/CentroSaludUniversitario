package Servicios.Negocio;

import Dtos.Jornada.ListJornadaResponseDTO;
import Dtos.Usuario.PageResponse;
import Modelo.Encuesta;
import Modelo.Jornada;
import Persistencia.JornadaDao;

import Servicios.Implementacion.EncuestaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;


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
        return jornadaDao.findById(id).orElseThrow(() -> new NotFoundException("Jornada no encontrada"));
    }

    public List<Jornada> buscarJornadasPorFecha(String fecha) {
        return jornadaDao.findByDate(fecha);
    }

    public PageResponse<ListJornadaResponseDTO> listar(int page, int size, String sort, String search) {
        if (size <= 0) size = 20;
        if (size > 100) size = 100;
        if (page < 0) page = 0;
        int offset = page * size;

        // DAO proyectando a DTO (recomendado)
        List<ListJornadaResponseDTO> content = jornadaDao.findPageToListDTO(sort, search, offset, size);
        long total = jornadaDao.count(search);

        return new PageResponse<>(content, total, page, size);
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
        Jornada jornada = jornadaDao.findById(jornadaId)
                .orElseThrow(() -> new NotFoundException("Jornada no encontrada: " + jornadaId));

        // establecer la FK (lado dueño en Encuesta)
        encuesta.setJornada(jornada);

        // agregar a la colección para mantener consistencia en memoria
        jornada.getEncuestas().add(encuesta);

        // con CascadeType.ALL, persiste/actualiza todo desde JORNADA
        jornadaDao.update(jornada);
    }

}