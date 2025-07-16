package Rest;

import Modelo.Zona;
import Servicios.Negocio.ZonaService;
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

@Path("/zonas")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Zonas", description = "Operaciones CRUD sobre zonas geográficas")
public class ZonaResource {

    @Inject
    private ZonaService zonaService;

    @POST
    @Operation(
            summary = "Crear nueva zona",
            description = "Crea una nueva zona dentro de un barrio ya existente (referido por ID).",
            requestBody = @RequestBody(
                    required = true,
                    description = "Datos de la zona a crear. El barrio debe indicarse por su ID.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Ejemplo zona",
                                    value = """
                                    {
                                      "nombre": "Zona Norte",
                                      "geoPoint": [
                                        { "latitud": -34.6037, "longitud": -58.3816 },
                                        { "latitud": -34.6048, "longitud": -58.3827 }
                                      ],
                                      "barrio": {
                                        "id": 1
                                      }
                                    }
                                    """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Zona creada correctamente")
            }
    )
    public Response crear(Zona zona) {
        zonaService.crearZona(zona);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Operation(
            summary = "Listar todas las zonas",
            description = "Obtiene la lista completa de zonas registradas.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Listado de zonas obtenido correctamente")
            }
    )
    public Response listar() {
        return Response.ok(zonaService.listarZonas()).build();
    }

    @GET
    @Path("/{id}")
    @Operation(
            summary = "Obtener zona por ID",
            description = "Busca una zona por su identificador.",
            parameters = @Parameter(name = "id", description = "ID de la zona", required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Zona encontrada"),
                    @ApiResponse(responseCode = "404", description = "Zona no encontrada")
            }
    )
    public Response obtenerPorId(@PathParam("id") Long id) {
        Zona zona = zonaService.buscarZonaPorId(id);
        if (zona != null) {
            return Response.ok(zona).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Zona no encontrada con id " + id)
                    .build();
        }
    }

    @GET
    @Path("/nombre/{nombre}")
    @Operation(
            summary = "Buscar zonas por nombre",
            description = "Devuelve una lista de zonas que coinciden con el nombre proporcionado.",
            parameters = @Parameter(name = "nombre", description = "Nombre de la zona a buscar", required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Zonas encontradas"),
                    @ApiResponse(responseCode = "404", description = "No se encontraron zonas con ese nombre")
            }
    )
    public Response obtenerPorNombre(@PathParam("nombre") String nombre) {
        List<Zona> zonas = zonaService.buscarZonasPorNombre(nombre);
        if (zonas != null && !zonas.isEmpty()) {
            return Response.ok(zonas).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Zona no encontrada con nombre " + nombre)
                    .build();
        }
    }

    @PUT
    @Path("/id/{id}")
    @Operation(
            summary = "Actualizar zona",
            description = "Actualiza los datos de una zona existente.",
            parameters = @Parameter(name = "id", description = "ID de la zona a actualizar", required = true),
            requestBody = @RequestBody(
                    required = true,
                    description = "Nuevos datos de la zona",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Ejemplo actualización zona",
                                    value = """
                                    {
                                      "nombre": "Zona Centro",
                                      "geoPoint": [
                                        { "latitud": -34.6037, "longitud": -58.3816 },
                                        { "latitud": -34.6050, "longitud": -58.3840 }
                                      ],
                                      "barrio": {
                                        "id": 2
                                      }
                                    }
                                    """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Zona actualizada correctamente")
            }
    )
    public Response actualizar(@PathParam("id") Long id, Zona zona) {
        zona.setId(id);
        zonaService.actualizarZona(zona);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(
            summary = "Eliminar zona",
            description = "Elimina una zona por su ID.",
            parameters = @Parameter(name = "id", description = "ID de la zona a eliminar", required = true),
            responses = {
                    @ApiResponse(responseCode = "204", description = "Zona eliminada correctamente"),
                    @ApiResponse(responseCode = "404", description = "Zona no encontrada")
            }
    )
    public Response eliminar(@PathParam("id") Long id) {
        Zona zona = zonaService.buscarZonaPorId(id);
        if (zona != null) {
            zonaService.eliminarZona(zona);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Zona no encontrada con id " + id)
                    .build();
        }
    }
}
