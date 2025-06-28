package Servlets;

import Modelo.Campania;
import Modelo.Jornada;
import Servicios.Negocio.CampaniaService;
import Servicios.Negocio.JornadaService;
import Utils.EntityMgmt;
import jakarta.persistence.EntityManager;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

//@WebServlet("/jornadas")
public class JornadaServlet extends HttpServlet {

    private JornadaService jornadaService;
    private CampaniaService campaniaService;

    @Override
    public void init(){
        EntityManager em = EntityMgmt.getEntityManager();
        this.jornadaService = new JornadaService(em);
        this.campaniaService = new CampaniaService(em); // para buscar la campaña asociada
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String horaInicio = req.getParameter("horaInicio");
        String horaFin = req.getParameter("horaFin");
        LocalDate fecha = LocalDate.parse(req.getParameter("fecha"));
        Long campaniaId = Long.parseLong(req.getParameter("campaniaId"));

        Campania campania = campaniaService.obtenerCampaniaPorId(campaniaId);

        if (campania == null) {
            resp.getWriter().println("Campania no encontrada.");
            return;
        }

        Jornada jornada = new Jornada(campania, horaInicio, horaFin, fecha);
        jornadaService.crearJornada(jornada);

        resp.getWriter().println("Jornada creada correctamente.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fechaParam = req.getParameter("fecha");

        if (fechaParam != null) {
            List<Jornada> jornadas = jornadaService.buscarJornadasPorFecha(fechaParam);
            for (Jornada j : jornadas) {
                resp.getWriter().println("Jornada: " + j.getHoraInicio() + " - " + j.getHoraFin() + " Fecha: " + j.getFecha());
            }
        } else {
            List<Jornada> jornadas = jornadaService.obtenerTodasLasJornadas();
            for (Jornada j : jornadas) {
                resp.getWriter().println("Jornada: " + j.getHoraInicio() + " - " + j.getHoraFin() + " Fecha: " + j.getFecha());
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));

        jornadaService.eliminarJornadaPorId(id);
        resp.getWriter().println("Jornada eliminada.");
    }
}
