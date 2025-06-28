package Servlets;

import Modelo.Campania;
import Servicios.Negocio.CampaniaService;
import Utils.EntityMgmt;
import jakarta.persistence.EntityManager;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/campanias")
public class CampaniaServlet extends HttpServlet {
    private CampaniaService campaniaService;

    @Override
    public void init() {
        EntityManager em = EntityMgmt.getEntityManager();
        campaniaService = new CampaniaService(em);
    }

    // Crear campaña
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String nombre = req.getParameter("nombre");
        String archivo = req.getParameter("archivo");
        LocalDate fechaInicio = LocalDate.parse(req.getParameter("fechaInicio"));
        LocalDate fechaFin = LocalDate.parse(req.getParameter("fechaFin"));

        Campania campania = new Campania();
        campania.setNombre(nombre);
        campania.setRutaArchivoEncuesta(archivo);
        campania.setFechaInicio(fechaInicio);
        campania.setFechaFin(fechaFin);

        // Para simplificar, dejamos zonas, encuestadores y org. social vacíos o nulos.
        campaniaService.crearCampania(campania);
        resp.getWriter().write("Campaña creada con ID: " + campania.getId());
    }

    // Listar o buscar por ID
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("id");
        if (idParam != null) {
            Long id = Long.parseLong(idParam);
            Campania c = campaniaService.obtenerCampaniaPorId(id);
            if (c != null) {
                resp.getWriter().write("Campaña: " + c.getNombre() + ", Fecha inicio: " + c.getFechaInicio());
            } else {
                resp.getWriter().write("No se encontró campaña con ID: " + id);
            }
        } else {
            List<Campania> campanias = campaniaService.listarCampanias();
            for (Campania c : campanias) {
                resp.getWriter().write("ID: " + c.getId() + ", Nombre: " + c.getNombre() + "\n");
            }
        }
    }

    // Actualizar
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Campania campania = campaniaService.obtenerCampaniaPorId(id);

        if (campania != null) {
            String nuevoNombre = req.getParameter("nombre");
            String nuevaRuta = req.getParameter("archivo");

            if (nuevoNombre != null) campania.setNombre(nuevoNombre);
            if (nuevaRuta != null) campania.setRutaArchivoEncuesta(nuevaRuta);

            campaniaService.actualizarCampania(campania);
            resp.getWriter().write("Campaña actualizada.");
        } else {
            resp.getWriter().write("Campaña no encontrada.");
        }
    }

    // Borrar
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        campaniaService.eliminarCampania(id);
        resp.getWriter().write("Campaña eliminada con ID: " + id);
    }
}
