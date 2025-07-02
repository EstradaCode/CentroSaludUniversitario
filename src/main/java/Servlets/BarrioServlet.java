package Servlets;

import Modelo.Barrio;
import Servicios.Negocio.BarrioService;
import Utils.EntityMgmt;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

//@WebServlet("/barrios")
public class BarrioServlet extends HttpServlet {
    private BarrioService barrioService;

    @Override
    public void init() {
        EntityManager em = EntityMgmt.getEntityManager();
        barrioService = new BarrioService(em);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("id");

        resp.setContentType("text/plain");

        if (idParam != null) {
            Long id = Long.parseLong(idParam);
            Barrio barrio = barrioService.buscarPorId(id);
            if (barrio != null) {
                resp.getWriter().println("Barrio: " + barrio.getNombre());
            } else {
                resp.getWriter().println("No se encontró el barrio.");
            }
        } else {
            List<Barrio> barrios = barrioService.buscarTodos();
            for (Barrio b : barrios) {
                resp.getWriter().println("ID: " + b.getId() + " - Nombre: " + b.getNombre());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String nombre = req.getParameter("nombre");

        Barrio barrio = new Barrio();
        barrio.setNombre(nombre);
        barrioService.guardarBarrio(barrio);

        resp.getWriter().println("Barrio creado con ID: " + barrio.getId());
    }
}

