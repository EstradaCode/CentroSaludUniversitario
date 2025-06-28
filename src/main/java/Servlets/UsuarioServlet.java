package Servlets;

import Modelo.Usuario;
import Servicios.Negocio.UsuarioService;
import Utils.EntityMgmt;
import jakarta.persistence.EntityManager;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

//@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {
    private UsuarioService usuarioService;

    @Override
    public void init() {
        EntityManager em = EntityMgmt.getEntityManager();
        usuarioService = new UsuarioService(em);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String accion = req.getParameter("accion");

        switch (accion) {
            case "listar":
                List<Usuario> usuarios = usuarioService.listarUsuarios();
                for (Usuario u : usuarios) {
                    resp.getWriter().println(u.getId() + " - " + u.getUsername());
                }
                break;
            case "buscar":
                Long id = Long.parseLong(req.getParameter("id"));
                Usuario usuario = usuarioService.buscarUsuarioPorId(id);
                if (usuario != null) {
                    resp.getWriter().println("Usuario: " + usuario.getUsername());
                } else {
                    resp.getWriter().println("No encontrado");
                }
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        String dniStr = req.getParameter("dni");
        String telefonoStr = req.getParameter("telefono");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String rol = req.getParameter("rol");
        String matriculaStr = req.getParameter("matricula");

        if (nombre == null || nombre.trim().isEmpty() ||
                apellido == null || apellido.trim().isEmpty() ||
                username == null || username.trim().isEmpty() ||
                password == null || password.trim().isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Error: faltan datos obligatorios");
            return;
        }

        Long dni = null;
        Long telefono = null;
        Long matricula = null;

        try {
            dni = (dniStr == null || dniStr.trim().isEmpty()) ? null : Long.parseLong(dniStr);
            telefono = (telefonoStr == null || telefonoStr.trim().isEmpty()) ? null : Long.parseLong(telefonoStr);
            matricula = (matriculaStr == null || matriculaStr.trim().isEmpty()) ? null : Long.parseLong(matriculaStr);
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Error: parámetros numéricos inválidos");
            return;
        }

        // Validar si ya existe username o dni
        if (usuarioService.buscarPorUsername(username)!= null) {
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            resp.getWriter().println("Error: el nombre de usuario ya está en uso");
            return;
        }
        if (dni != null && usuarioService.buscarPorDni(dni)!=null) {
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
            resp.getWriter().println("Error: el DNI ya está registrado");
            return;
        }

        Usuario nuevo = new Usuario(nombre.trim(), apellido.trim(), dni, telefono, username.trim(), password, email, rol, matricula);

        usuarioService.crearUsuario(nuevo);

        resp.setContentType("text/plain");
        resp.getWriter().println("Usuario creado con ID: " + nuevo.getId());
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Simulación de update (se puede mejorar usando filtros o parámetros)
        Long id = Long.parseLong(req.getParameter("id"));
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        if (usuario != null) {
            usuario.setEmail("nuevo@mail.com"); // prueba
            usuarioService.actualizarUsuario(usuario);
            resp.getWriter().println("Usuario actualizado");
        } else {
            resp.getWriter().println("No encontrado");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        usuarioService.eliminarUsuario(id);
        resp.getWriter().println("Usuario eliminado");
    }
}
