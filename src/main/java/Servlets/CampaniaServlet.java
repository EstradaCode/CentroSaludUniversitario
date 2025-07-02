package Servlets;

import Modelo.Barrio;
import Modelo.Campania;
import Modelo.OrganizacionSocial;
import Servicios.Negocio.BarrioService;
import Servicios.Negocio.CampaniaService;
import Servicios.Negocio.OrganizacionSocialService;
import Utils.EntityMgmt;
import jakarta.persistence.EntityManager;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//@WebServlet("/campanias")
public class CampaniaServlet extends HttpServlet {
    private CampaniaService campaniaService;
    private OrganizacionSocialService organizacionService;
    private BarrioService barrioService;
    @Override
    public void init() {
        EntityManager em = EntityMgmt.getEntityManager();
        campaniaService = new CampaniaService(em);
        this.organizacionService = new OrganizacionSocialService(em);
        this.barrioService= new BarrioService(em);
    }

    // Crear campaña
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String nombre = req.getParameter("nombre");
        String archivo = req.getParameter("archivo");
        LocalDate fechaInicio = LocalDate.parse(req.getParameter("fechaInicio"));
        LocalDate fechaFin = LocalDate.parse(req.getParameter("fechaFin"));
        Long organizacionId = Long.parseLong(req.getParameter("organizacionId"));
        OrganizacionSocial organizacion = organizacionService.buscarPorId(organizacionId);
        if (organizacion == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Organización social no encontrada.");
            return;
        }

        String barriosParam = req.getParameter("barrios");
        List<Barrio> barrios = new ArrayList<>();
        if (barriosParam != null && !barriosParam.isEmpty()) {
            String[] ids = barriosParam.split(",");
            for (String idStr : ids) {
                try {
                    Long barrioId = Long.parseLong(idStr.trim());
                    Barrio barrio = barrioService.buscarPorId(barrioId); // asegurate que este método exista
                    if (barrio != null) {
                        barrios.add(barrio);
                    } else {
                        System.out.println("Barrio no encontrado para ID: " + barrioId);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ID de barrio inválido: " + idStr);
                }
            }
        }

        Campania campania = new Campania();
        campania.setNombre(nombre);
        campania.setRutaArchivoEncuesta(archivo);
        campania.setFechaInicio(fechaInicio);
        campania.setFechaFin(fechaFin);
        campania.setOrganizacionSocial(organizacion);


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
