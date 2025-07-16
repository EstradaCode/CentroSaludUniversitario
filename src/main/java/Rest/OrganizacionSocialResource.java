package Rest;

import Modelo.OrganizacionSocial;
import Servicios.Negocio.OrganizacionSocialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/organizaciones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Organizaciones Sociales", description = "Operaciones CRUD sobre organizaciones sociales")
public class OrganizacionSocialResource {

    @Inject
    private OrganizacionSocialService organizacionService;

    @GET
    @Operation(
            summary = "Listar todas las organizaciones sociales",
            description = "Devuelve una lista con todas las organizaciones registradas.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de organizaciones obtenida correctamente")
            }
    )
    public List<OrganizacionSocial> listarTodas() {
        return organizacionService.obtenerTodas();
    }

    @GET
    @Path("/{id}")
    @Operation(
            summary = "Obtener una organización por ID",
            description = "Devuelve los datos de una organización social específica.",
            parameters = {
                    @Parameter(name = "id", description = "ID de la organización", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Organización encontrada"),
                    @ApiResponse(responseCode = "404", description = "Organización no encontrada")
            }
    )
    public Response obtenerPorId(@PathParam("id") Long id) {
        OrganizacionSocial org = organizacionService.buscarPorId(id);
        if (org == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Organización social no encontrada con id " + id)
                    .build();
        }
        return Response.ok(org).build();
    }

    @POST
    @Operation(
            summary = "Crear una nueva organización social",
            description = "Registra una nueva organización social. El barrio debe especificarse solo con su ID.",
            requestBody = @RequestBody(
                    required = true,
                    description = "Datos de la nueva organización",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Ejemplo de creación",
                                    value = """
                        {
                          "nombre": "Centro Comunitario El Sol",
                          "domicilio": "Calle Falsa 123",
                          "actividadPrincipal": "Alimentación infantil",
                          "barrio": {
                            "id": 1
                          }
                        }
                        """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Organización creada correctamente")
            }
    )
    public Response crear(OrganizacionSocial organizacion) {
        organizacionService.guardarOrganizacion(organizacion);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(
            summary = "Actualizar una organización social",
            description = "Modifica los datos de una organización social existente. El barrio también debe pasarse solo con ID.",
            parameters = @Parameter(name = "id", description = "ID de la organización a actualizar", required = true),
            requestBody = @RequestBody(
                    required = true,
                    description = "Datos actualizados de la organización",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Ejemplo de actualización",
                                    value = """
                        {
                          "nombre": "Centro Comunitario El Sol Renovado",
                          "domicilio": "Calle Verdad 456",
                          "actividadPrincipal": "Asistencia médica",
                          "barrio": {
                            "id": 2
                          }
                        }
                        """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Organización actualizada correctamente"),
                    @ApiResponse(responseCode = "404", description = "Organización no encontrada")
            }
    )
    public Response actualizar(@PathParam("id") Long id, OrganizacionSocial organizacion) {
        OrganizacionSocial existente = organizacionService.buscarPorId(id);
        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Organización social no encontrada con id " + id)
                    .build();
        }
        organizacion.setId(id);
        organizacionService.actualizarOrganizacion(organizacion);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(
            summary = "Eliminar una organización social",
            description = "Elimina una organización por su ID.",
            parameters = @Parameter(name = "id", description = "ID de la organización a eliminar", required = true),
            responses = {
                    @ApiResponse(responseCode = "204", description = "Organización eliminada correctamente"),
                    @ApiResponse(responseCode = "404", description = "Organización no encontrada")
            }
    )
    public Response eliminar(@PathParam("id") Long id) {
        OrganizacionSocial existente = organizacionService.buscarPorId(id);
        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Organización social no encontrada con id " + id)
                    .build();
        }
        organizacionService.eliminarPorId(id);
        return Response.noContent().build();
    }
}
