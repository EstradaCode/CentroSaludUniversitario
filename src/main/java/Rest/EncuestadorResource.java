package Rest;

import Modelo.Encuestador;
import Servicios.Negocio.EncuestadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@RequestScoped
@Consumes("application/json")
@Produces("application/json")
@Path("/encuestadores")
@Tag(name = "Encuestadores", description = "Operaciones relacionadas con encuestadores")
public class EncuestadorResource {

    @Inject
    private EncuestadorService encuestadorService;

    @GET
    @Operation(
            summary = "Listar todos los encuestadores",
            description = "Retorna la lista completa de encuestadores registrados en el sistema"
    )
    public Response listarEncuestadores() {
        return Response.ok(encuestadorService.listarEncuestadores()).build();
    }

    @GET
    @Path("/{id}")
    @Operation(
            summary = "Obtener encuestador por ID",
            description = "Busca un encuestador por su identificador único",
            parameters = {
                    @Parameter(
                            name = "id",
                            in = ParameterIn.PATH,
                            required = true,
                            description = "ID del encuestador",
                            example = "1"
                    )
            }
    )
    public Response obtenerEncuestadorPorId(@PathParam("id") Long id) {
        var encuestador = encuestadorService.buscarEncuestadorPorId(id);
        if (encuestador != null) {
            return Response.ok(encuestador).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Encuestador no encontrado con id " + id)
                    .build();
        }
    }

    @POST
    @Operation(
            summary = "Crear nuevo encuestador",
            description = "Registra un nuevo encuestador en el sistema",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del nuevo encuestador",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = Encuestador.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Ejemplo de Encuestador",
                                            summary = "Encuestador de prueba",
                                            value = """
                        {
                          "nombre": "Lucía",
                          "apellido": "Pérez",
                          "dni": 40300200,
                          "telefono": 1122334455,
                          "horasTrabajadas": 35,
                          "barrio": {
                            "id": 2
                          }
                        }
                        """
                                    )
                            }
                    )
            )
    )
    public Response crearEncuestador(Encuestador encuestador) {
        encuestadorService.crearEncuestador(encuestador);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(
            summary = "Actualizar encuestador",
            description = "Modifica los datos de un encuestador existente",
            parameters = {
                    @Parameter(
                            name = "id",
                            in = ParameterIn.PATH,
                            required = true,
                            description = "ID del encuestador a actualizar",
                            example = "1"
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Nuevos datos del encuestador",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = Encuestador.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Encuestador actualizado",
                                            value = """
                        {
                          "nombre": "Ana",
                          "apellido": "Gómez",
                          "dni": 40500444,
                          "telefono": 1199887766,
                          "horasTrabajadas": 40,
                          "barrio": {
                            "id": 3
                          }
                        }
                        """
                                    )
                            }
                    )
            )
    )
    public Response actualizarEncuestador(@PathParam("id") Long id, Encuestador encuestador) {
        encuestador.setId(id);
        encuestadorService.actualizarEncuestador(encuestador);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(
            summary = "Eliminar encuestador",
            description = "Elimina un encuestador por ID",
            parameters = {
                    @Parameter(
                            name = "id",
                            in = ParameterIn.PATH,
                            required = true,
                            description = "ID del encuestador a eliminar",
                            example = "1"
                    )
            }
    )
    public Response eliminarEncuestador(@PathParam("id") Long id) {
        Encuestador encuestador = encuestadorService.buscarEncuestadorPorId(id);
        if (encuestador != null) {
            encuestadorService.eliminarEncuestador(encuestador);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Encuestador no encontrado con id " + id)
                    .build();
        }
    }
}
