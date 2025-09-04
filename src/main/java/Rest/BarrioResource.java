package Rest;

import Modelo.Barrio;
import Servicios.Implementacion.BarrioService;
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

@Path("/barrios")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Barrios", description = "Operaciones CRUD sobre barrios")
public class BarrioResource {

    @Inject
    private BarrioService barrioService;

    @GET
    @Operation(
            summary = "Listar todos los barrios",
            description = "Devuelve todos los barrios registrados.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
            }
    )
    public List<Barrio> listar() {
        return barrioService.buscarTodos();
    }

    @GET
    @Path("/{id}")
    @Operation(
            summary = "Obtener un barrio por ID",
            description = "Busca un barrio por su identificador.",
            parameters = @Parameter(name = "id", description = "ID del barrio a buscar", required = true),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Barrio encontrado"),
                    @ApiResponse(responseCode = "404", description = "Barrio no encontrado")
            }
    )
    public Response obtenerPorId(@PathParam("id") Long id) {
        Barrio barrio = barrioService.buscarPorId(id);
        if (barrio != null) {
            return Response.ok(barrio).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Barrio no encontrado con id " + id)
                    .build();
        }
    }

    @POST
    @Operation(
            summary = "Crear un nuevo barrio",
            description = "Crea un nuevo barrio con su nombre y zonas asociadas.",
            requestBody = @RequestBody(
                    required = true,
                    description = "Datos del barrio a crear",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Barrio con zona",
                                    value = """
                        {
                          "nombre": "Barrio Parque",
                          "zonas": [
                            {
                              "nombre": "Zona A",
                              "geoPoint": [
                                { "latitud": -34.6037, "longitud": -58.3816 },
                                { "latitud": -34.6040, "longitud": -58.3820 }
                              ]
                            }
                          ]
                        }
                        """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Barrio creado exitosamente")
            }
    )
    public Response crear(Barrio barrio) {
        barrioService.crearBarrio(barrio);
        System.out.println("Barrio creado: " + barrio.getNombre());
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(
            summary = "Actualizar un barrio",
            description = "Actualiza el nombre o zonas de un barrio existente.",
            parameters = @Parameter(name = "id", description = "ID del barrio a actualizar", required = true),
            requestBody = @RequestBody(
                    description = "Datos actualizados del barrio",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Barrio actualizado",
                                    value = """
                        {
                          "nombre": "Barrio Parque Renovado",
                          "zonas": [
                            {
                              "nombre": "Zona B",
                              "geoPoint": [
                                { "latitud": -34.6050, "longitud": -58.3825 }
                              ]
                            }
                          ]
                        }
                        """
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Barrio actualizado correctamente")
            }
    )
    public Response actualizar(@PathParam("id") Long id, Barrio barrio) {
        barrio.setId(id);
        barrioService.actualizarBarrio(barrio);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(
            summary = "Eliminar un barrio",
            description = "Elimina un barrio específico por su ID.",
            parameters = @Parameter(name = "id", description = "ID del barrio a eliminar", required = true),
            responses = {
                    @ApiResponse(responseCode = "204", description = "Barrio eliminado correctamente"),
                    @ApiResponse(responseCode = "404", description = "Barrio no encontrado")
            }
    )
    public Response eliminar(@PathParam("id") Long id) {
        Barrio barrio = barrioService.buscarPorId(id);
        if (barrio != null) {
            barrioService.eliminarBarrio(barrio);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Barrio no encontrado con id " + id)
                    .build();
        }
    }
}
