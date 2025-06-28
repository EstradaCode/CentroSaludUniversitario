package Servlets;

import Modelo.Encuesta;
import Modelo.GeoPoint;
import Servicios.Negocio.EncuestaService;
import Utils.EntityMgmt;
import Utils.Enums.TipoConexionElectrica;
import Utils.Enums.TipoVivienda;
import jakarta.persistence.EntityManager;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/encuestas")
public class EncuestaServlet extends HttpServlet {

    private EncuestaService encuestaService;

    @Override
    public void init() {
        EntityManager em = EntityMgmt.getEntityManager();
        encuestaService = new EncuestaService(em);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            // Parámetros simples
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

        if (idParam != null) {
            Long id = Long.parseLong(idParam);
            Encuesta encuesta = encuestaService.obtenerEncuestaPorId(id);
            if (encuesta != null) {
                resp.getWriter().println("Encuesta: " + encuesta.getId() + " UUID: " + encuesta.getUuidApi());
            } else {
                resp.getWriter().println("Encuesta no encontrada.");
            }
        } else {
            List<Encuesta> encuestas = encuestaService.obtenerTodasLasEncuestas();
            for (Encuesta e : encuestas) {
                resp.getWriter().println("Encuesta ID: " + e.getId() + " UUID: " + e.getUuidApi());
            }
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
