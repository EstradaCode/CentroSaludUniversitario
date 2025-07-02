package Servlets;

import Modelo.OrganizacionSocial;
import Servicios.Negocio.OrganizacionSocialService;
import Utils.EntityMgmt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.persistence.EntityManager;

import java.io.IOException;
import java.util.List;

@WebServlet("/organizacion")
public class OrganizacionSocialServlet extends HttpServlet {

    private OrganizacionSocialService organizacionService;

    @Override
    public void init() {
        EntityManager em = EntityMgmt.getEntityManager();
        this.organizacionService = new OrganizacionSocialService(em);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String nombre = req.getParameter("nombre");
            String domicilio = req.getParameter("domicilio");

            if (nombre == null || domicilio == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("Faltan parámetros: nombre y/o domicilio.");
                return;
            }

            OrganizacionSocial org = new OrganizacionSocial();
            org.setNombre(nombre);
            org.setDomicilio(domicilio);

            organizacionService.guardarOrganizacion(org);
            resp.getWriter().println("Organización creada correctamente.");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Error al crear organización: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("id");

        resp.setContentType("text/plain");

        if (idParam != null) {
            try {
                Long id = Long.parseLong(idParam);
                OrganizacionSocial org = organizacionService.buscarPorId(id);
                if (org != null) {
                    imprimirOrganizacion(resp, org);
                } else {
                    resp.getWriter().println("Organización no encontrada.");
                }
            } catch (NumberFormatException e) {
                resp.getWriter().println("ID inválido.");
            }
        } else {
            List<OrganizacionSocial> organizaciones = organizacionService.obtenerTodas();
            if (organizaciones.isEmpty()) {
                resp.getWriter().println("No hay organizaciones registradas.");
            } else {
                for (OrganizacionSocial org : organizaciones) {
                    imprimirOrganizacion(resp, org);
                    resp.getWriter().println("----------------------------------");
                }
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("id");
        if (idParam != null) {
            try {
                Long id = Long.parseLong(idParam);
                organizacionService.eliminarPorId(id);
                resp.getWriter().println("Organización eliminada con ID: " + id);
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("ID inválido.");
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Debe proporcionar un ID.");
        }
    }

    private void imprimirOrganizacion(HttpServletResponse resp, OrganizacionSocial org) throws IOException {
        resp.getWriter().println("ID: " + org.getId());
        resp.getWriter().println("Nombre: " + org.getNombre());
        resp.getWriter().println("Domicilio: " + org.getDomicilio());
    }
}
