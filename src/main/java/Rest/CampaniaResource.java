package Rest;

import Modelo.Campania;
import Servicios.Implementacion.CampaniaService;
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

@Path("/campanias")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Campañas", description = "Operaciones CRUD sobre campañas de encuestas")
public class CampaniaResource {

    @Inject
    private CampaniaService campaniaService;

    @GET
    @Operation(
            summary = "Listar todas las campañas",
            description = "Devuelve la lista completa de campañas registradas.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Campañas obtenidas correctamente")
            }
    )
    public List<Campania> listar() {
        return campaniaService.listarCampanias();
    }

    @GET
    @Path("/{id}")
    @Operation(
            summary = "Obtener campaña por ID",
            description = "Busca una campaña específica por su ID.",
            parameters = @Parameter(name = "id", description = "ID de la campaña", required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Campaña encontrada"),
                    @ApiResponse(responseCode = "404", description = "Campaña no encontrada")
            }
    )
    public Response obtenerPorId(@PathParam("id") Long id) {
        Campania campania = campaniaService.obtenerCampaniaPorId(id);
        if (campania != null) {
            return Response.ok(campania).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Campaña no encontrada con id " + id)
                    .build();
        }
    }

    @POST
    @Operation(
            summary = "Crear nueva campaña",
            description = "Crea una nueva campaña con los datos proporcionados. No se cargan zonas, jornadas ni encuestadores en esta operación.",
            requestBody = @RequestBody(
                    required = true,
                    description = "Datos de la nueva campaña. La organización social debe referenciarse por su ID.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Ejemplo campaña",
                                    value = """
                                        {
                                          "nombre": "Campaña de Vacunación",
                                          "rutaArchivoEncuesta": "https://storage.googleapis.com/mi-bucket/encuesta.xlsx",
                                          "fechaInicio": "2025-08-01",
                                          "fechaFin": "2025-08-20",
                                          "organizacionSocial": {
                                            "id": 2
                                          }
                                        }
                                        """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Campaña creada correctamente")
            }
    )
    public Response crear(Campania campania) {
        campaniaService.crearCampania(campania);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(
            summary = "Actualizar campaña",
            description = "Actualiza una campaña existente.",
            parameters = @Parameter(name = "id", description = "ID de la campaña a actualizar", required = true),
            requestBody = @RequestBody(
                    required = true,
                    description = "Datos actualizados de la campaña.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Ejemplo actualización",
                                    value = """
                                        {
                                          "nombre": "Campaña de Prevención",
                                          "rutaArchivoEncuesta": "https://storage.googleapis.com/mi-bucket/prevencion.xlsx",
                                          "fechaInicio": "2025-09-01",
                                          "fechaFin": "2025-09-15",
                                          "organizacionSocial": {
                                            "id": 2
                                          }
                                        }
                                        """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Campaña actualizada correctamente"),
                    @ApiResponse(responseCode = "404", description = "Campaña no encontrada")
            }
    )
    public Response actualizar(@PathParam("id") Long id, Campania campania) {
        campania.setId(id);
        campaniaService.actualizarCampania(campania);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(
            summary = "Eliminar campaña",
            description = "Elimina una campaña por su ID.",
            parameters = @Parameter(name = "id", description = "ID de la campaña a eliminar", required = true),
            responses = {
                    @ApiResponse(responseCode = "204", description = "Campaña eliminada correctamente"),
                    @ApiResponse(responseCode = "404", description = "Campaña no encontrada")
            }
    )
    public Response eliminar(@PathParam("id") Long id) {
        campaniaService.eliminarCampania(id);
        return Response.noContent().build();
    }
}
