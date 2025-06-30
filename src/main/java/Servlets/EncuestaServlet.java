package Servlets;

import Modelo.Encuesta;
import Modelo.EncuestaPersona;
import Modelo.GeoPoint;
import Modelo.Jornada;
import Servicios.Negocio.EncuestaService;
import Servicios.Negocio.JornadaService;
import Utils.EntityMgmt;
import Utils.Enums.*;
import jakarta.persistence.EntityManager;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//@WebServlet("/encuestas")
public class EncuestaServlet extends HttpServlet {
    private EncuestaService encuestaService;
    private JornadaService jornadaService;
    @Override
    public void init() {
        EntityManager em = EntityMgmt.getEntityManager();
        encuestaService = new EncuestaService(em);
        jornadaService = new JornadaService(em);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            // para la prueba de cruds se harán a mano, idealmente se hará con la ruta de un archivo o un Archivo File recibido y luego pasarlo a una clase ETL que realice la transformación de datos del csv a la tabla.
            String fechaCreacionStr = req.getParameter("fechaCreacion");
            String tipoViviendaStr = req.getParameter("tipoVivienda");
            boolean accesoAgua = Boolean.parseBoolean(req.getParameter("accesoAgua"));
            boolean aguaPotable = Boolean.parseBoolean(req.getParameter("aguaPotable"));
            boolean accesoSalud = Boolean.parseBoolean(req.getParameter("accesoSalud"));
            int cantidadHabitaciones = Integer.parseInt(req.getParameter("cantidadHabitaciones"));
            boolean asistenciaAlimentaria = Boolean.parseBoolean(req.getParameter("asistenciaAlimentaria"));
            String conexionElectricaStr = req.getParameter("conexionElectrica");
            String uuidApi = req.getParameter("uuidApi");

            // Coordenadas (GeoPoint embebido)
            double lat = Double.parseDouble(req.getParameter("lat"));
            double lon = Double.parseDouble(req.getParameter("lon"));
            GeoPoint coordenadas = new GeoPoint(lat, lon);
            Long jornadaId = Long.parseLong(req.getParameter("jornadaId"));
            Encuesta encuesta = new Encuesta();
            encuesta.setCoordenadas(coordenadas);
            encuesta.setFechaCreacion(Date.valueOf(fechaCreacionStr));
            encuesta.setTipoVivienda(TipoVivienda.valueOf(tipoViviendaStr));
            encuesta.setAccesoAgua(accesoAgua);
            encuesta.setAguaPotable(aguaPotable);
            encuesta.setConexionElectrica(TipoConexionElectrica.valueOf(conexionElectricaStr));
            encuesta.setCantidadHabitaciones(cantidadHabitaciones);
            encuesta.setAsistenciaAlimentaria(asistenciaAlimentaria);
            encuesta.setAccesoSalud(accesoSalud);
            encuesta.setUuidApi(uuidApi);
            Jornada jornada = jornadaService.buscarJornadaPorId(jornadaId);

            if (jornada == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("Jornada no encontrada.");
                return;
            }

            encuesta.setJornada(jornada);
            jornada.getEncuestas().add(encuesta);
            // tratamiento *prueba individual* de encuesta por persona
            EncuestaPersona persona = new EncuestaPersona();
            persona.setEdad(Integer.parseInt(req.getParameter("edad")));
            persona.setGenero(Genero.valueOf(req.getParameter("genero")));
            persona.setNivelEducativo(NivelEducativo.valueOf(req.getParameter("nivelEducativo")));
            persona.setTipoEmpleo(TipoEmpleo.valueOf(req.getParameter("tipoEmpleo")));
            persona.setCoberturaSalud(AtencionSalud.valueOf(req.getParameter("coberturaSalud")));
            persona.setAccesoMedicacion(AccesoMedicacion.valueOf(req.getParameter("accesoMedicacion")));

            Set<Enfermedad> enfermedades = Arrays.stream(req.getParameter("enfermedades").split(","))
                    .map(String::trim)
                    .map(Enfermedad::valueOf)
                    .collect(Collectors.toSet());
            persona.setEnfermedades(enfermedades);

            Set<MetodoAnticonceptivo> anticonceptivos = Arrays.stream(req.getParameter("anticonceptivos").split(","))
                    .map(String::trim)
                    .map(MetodoAnticonceptivo::valueOf)
                    .collect(Collectors.toSet());
            persona.setAnticonceptivos(anticonceptivos);


            persona.setEncuesta(encuesta);

            encuesta.setPersonas(List.of(persona)); // o agregar si ya hay lista

            // creacion de encuesta
            encuestaService.crearEncuesta(encuesta);


            resp.getWriter().println("Encuesta creada correctamente.");

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Error al crear encuesta: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("id");

        resp.setContentType("text/plain");

        if (idParam != null) {
            Long id = Long.parseLong(idParam);
            Encuesta encuesta = encuestaService.obtenerEncuestaPorId(id);
            if (encuesta != null) {
                imprimirEncuesta(resp, encuesta);
            } else {
                resp.getWriter().println("Encuesta no encontrada.");
            }
        } else {
            List<Encuesta> encuestas = encuestaService.obtenerTodasLasEncuestas();
            if (encuestas.isEmpty()) {
                resp.getWriter().println("No hay encuestas registradas.");
            } else {
                for (Encuesta e : encuestas) {
                    imprimirEncuesta(resp, e);
                    resp.getWriter().println("----------------------------------");
                }
            }
        }
    }

    private void imprimirEncuesta(HttpServletResponse resp, Encuesta encuesta) throws IOException {
        resp.getWriter().println("Encuesta ID: " + encuesta.getId());
        resp.getWriter().println("Fecha creación: " + encuesta.getFechaCreacion());
        resp.getWriter().println("Tipo vivienda: " + encuesta.getTipoVivienda());
        resp.getWriter().println("Agua: " + encuesta.getAccesoAgua() + ", Potable: " + encuesta.getAguaPotable());
        resp.getWriter().println("Electricidad: " + encuesta.getConexionElectrica());
        resp.getWriter().println("Habitaciones: " + encuesta.getCantidadHabitaciones());
        resp.getWriter().println("Salud: " + encuesta.getAccesoSalud());
        resp.getWriter().println("Asistencia alimentaria: " + encuesta.getAsistenciaAlimentaria());

        if (encuesta.getPersonas() != null && !encuesta.getPersonas().isEmpty()) {
            resp.getWriter().println("Personas:");
            for (EncuestaPersona p : encuesta.getPersonas()) {
                resp.getWriter().println(" - Edad: " + p.getEdad() +
                        ", Género: " + p.getGenero() +
                        ", Empleo: " + p.getTipoEmpleo() +
                        ", Salud: " + p.getCoberturaSalud());
            }
        } else {
            resp.getWriter().println("No hay personas cargadas en la encuesta.");
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Encuesta encuesta = encuestaService.obtenerEncuestaPorId(id);
        if (encuesta != null) {
            encuestaService.eliminarEncuesta(encuesta);
            resp.getWriter().println("Encuesta eliminada.");
        } else {
            resp.getWriter().println("Encuesta no encontrada.");
        }
    }
}
