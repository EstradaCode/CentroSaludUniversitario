package Rest;

import Modelo.Usuario;
import Servicios.Negocio.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/usuarios")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Usuarios", description = "Operaciones CRUD sobre usuarios del sistema")
public class UsuarioResource {

    @Inject
    private UsuarioService usuarioService;

    @GET
    @Operation(
            summary = "Listar todos los usuarios",
            description = "Devuelve la lista completa de usuarios registrados.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
            }
    )
    public List<Usuario> listar() {
        return usuarioService.listarUsuarios();
    }

    @GET
    @Path("/{id}")
    @Operation(
            summary = "Obtener usuario por ID",
            description = "Busca un usuario específico por su ID.",
            parameters = @Parameter(name = "id", description = "ID del usuario", required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
                    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
            }
    )
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
    @Operation(
            summary = "Crear nuevo usuario",
            description = "Crea un nuevo usuario con los datos proporcionados.",
            requestBody = @RequestBody(
                    required = true,
                    description = "Datos del nuevo usuario",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Ejemplo usuario",
                                    value = """
                        {
                          "nombre": "Ludmila",
                          "apellido": "Pérez",
                          "dni": 12345678,
                          "telefono": 1122334455,
                          "username": "ludmi123",
                          "password": "secreta",
                          "email": "ludmi@example.com",
                          "rol": "SALUD",
                          "matricula": 98765
                        }
                        """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Usuario creado correctamente")
            }
    )
    public Response crear(Usuario usuario) {
        usuarioService.crearUsuario(usuario);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(
            summary = "Actualizar un usuario",
            description = "Modifica los datos de un usuario existente.",
            parameters = @Parameter(name = "id", description = "ID del usuario a actualizar", required = true),
            requestBody = @RequestBody(
                    required = true,
                    description = "Datos nuevos del usuario",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Ejemplo actualización",
                                    value = """
                        {
                          "nombre": "Ludmila",
                          "apellido": "Pérez",
                          "dni": 12345678,
                          "telefono": 1122334455,
                          "username": "ludmi_actualizada",
                          "password": "nuevaClave123",
                          "email": "ludmi_new@example.com",
                          "rol": "ADMIN",
                          "matricula": 12345
                        }
                        """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario actualizado"),
                    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
            }
    )
    public Response actualizar(@PathParam("id") Long id, Usuario usuario) {
        usuario.setId(id);
        usuarioService.actualizarUsuario(usuario);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(
            summary = "Eliminar un usuario",
            description = "Elimina un usuario según su ID.",
            parameters = @Parameter(name = "id", description = "ID del usuario", required = true),
            responses = {
                    @ApiResponse(responseCode = "204", description = "Usuario eliminado"),
                    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
            }
    )
    public Response eliminar(@PathParam("id") Long id) {
        usuarioService.eliminarUsuario(id);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}/habilitar")
    @Operation(
            summary = "Habilitar un usuario",
            description = "Marca al usuario como habilitado (enabled=true).",
            parameters = @Parameter(name = "id", description = "ID del usuario a habilitar", required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario habilitado"),
                    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
            }
    )
    public Response habilitarUsuario(@PathParam("id") Long id) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuario no encontrado con id " + id)
                    .build();
        }
        usuarioService.habilitarUsuario(id);
        return Response.ok("Usuario habilitado").build();
    }

    @PUT
    @Path("/{id}/deshabilitar")
    @Operation(
            summary = "Deshabilitar un usuario",
            description = "Marca al usuario como deshabilitado (enabled=false).",
            parameters = @Parameter(name = "id", description = "ID del usuario a deshabilitar", required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario deshabilitado"),
                    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
            }
    )
    public Response deshabilitarUsuario(@PathParam("id") Long id) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuario no encontrado con id " + id)
                    .build();
        }
        usuarioService.deshabilitarUsuario(id);
        return Response.ok("Usuario deshabilitado").build();
    }

}
