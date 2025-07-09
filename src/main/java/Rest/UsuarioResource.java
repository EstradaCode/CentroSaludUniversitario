package Rest;

import Modelo.Usuario;
import Servicios.Negocio.UsuarioService;
import jakarta.enterprise.context.RequestScoped;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
// aclaración con imports -> ws jax-rs | inject cdi
import java.util.List;

@Path("/usuarios")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    private UsuarioService usuarioService;

    @GET
    public List<Usuario> listar() {
        return usuarioService.listarUsuarios();
    }

    @GET
    @Path("/{id}")
    public Response obtenerPorId(@PathParam("id") Long id) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        if (usuario != null) {
            return Response.ok(usuario).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuario no encontrado con id " + id)
                    .build();
        }
    }


    @POST
    public Response crear(Usuario usuario) {
        usuarioService.crearUsuario(usuario);
        System.out.println("Usuario creado: " + usuario.getUsername());
        return Response.status(Response.Status.CREATED).build();

    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Long id, Usuario usuario) {
        usuario.setId(id);
        usuarioService.actualizarUsuario(usuario);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Long id) {
        usuarioService.eliminarUsuario(id);
        return Response.noContent().build();
    }
    @PUT
    @Path("/{id}/habilitar")
    public Response habilitarUsuario(@PathParam("id") Long id) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuario no encontrado con id " + id)
                    .build();
        }
        usuarioService.habilitarUsuario(id);
        return Response.ok().entity("Usuario habilitado").build();
    }

    @PUT
    @Path("/{id}/deshabilitar")
    public Response deshabilitarUsuario(@PathParam("id") Long id) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuario no encontrado con id " + id)
                    .build();
        }
        usuarioService.deshabilitarUsuario(id);
        return Response.ok().entity("Usuario deshabilitado").build();
    }

}