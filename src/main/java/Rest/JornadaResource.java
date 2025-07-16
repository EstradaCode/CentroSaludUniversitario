package Rest;

import Modelo.Jornada;
import Servicios.Negocio.JornadaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/jornadas")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Jornadas", description = "Operaciones relacionadas con las jornadas de encuestadores")
public class JornadaResource {

    @Inject
    private JornadaService jornadaService;

    @GET
    @Operation(
            summary = "Listar todas las jornadas",
            description = "Retorna la lista de todas las jornadas existentes"
    )
    public List<Jornada> listar() {
        return jornadaService.obtenerTodasLasJornadas();
    }

    @GET
    @Path("/{id}")
    @Operation(
            summary = "Obtener jornada por ID",
            description = "Busca una jornada específica por su ID",
            parameters = {
                    @Parameter(
                            name = "id",
                            in = ParameterIn.PATH,
                            required = true,
                            description = "ID de la jornada a buscar",
                            example = "1"
                    )
            }
    )
    public Response obtenerPorId(@PathParam("id") Long id) {
        Jornada jornada = jornadaService.buscarJornadaPorId(id);
        if (jornada != null) {
            return Response.ok(jornada).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Jornada no encontrada con id " + id)
                    .build();
        }
    }

    @POST
    @Operation(
            summary = "Crear nueva jornada",
            description = "Registra una nueva jornada",
            requestBody = @RequestBody(
                    required = true,
                    description = "Datos de la jornada a crear",
                    content = @Content(
                            schema = @Schema(implementation = Jornada.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Ejemplo Jornada",
                                            summary = "Jornada típica",
                                            value = """
                        {
                          "horaInicio": "08:00",
                          "horaFin": "12:00",
                          "fecha": "2025-07-15",
                          "campania": {
                            "id": 1
                          }
                        }
                        """
                                    )
                            }
                    )
            )
    )
    public Response crear(Jornada jornada) {
        jornadaService.crearJornada(jornada);
        System.out.println("Jornada creada: " + jornada.getId());
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(
            summary = "Actualizar jornada",
            description = "Actualiza los datos de una jornada existente",
            parameters = {
                    @Parameter(
                            name = "id",
                            in = ParameterIn.PATH,
                            required = true,
                            description = "ID de la jornada a actualizar",
                            example = "1"
                    )
            },
            requestBody = @RequestBody(
                    required = true,
                    description = "Datos nuevos para la jornada",
                    content = @Content(
                            schema = @Schema(implementation = Jornada.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Jornada actualizada",
                                            value = """
                        {
                          "horaInicio": "10:00",
                          "horaFin": "14:00",
                          "fecha": "2025-07-20",
                          "campania": {
                            "id": 2
                          }
                        }
                        """
                                    )
                            }
                    )
            )
    )
    public Response actualizar(@PathParam("id") Long id, Jornada jornada) {
        jornada.setId(id);
        jornadaService.actualizarJornada(jornada);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(
            summary = "Eliminar jornada",
            description = "Elimina una jornada por su ID",
            parameters = {
                    @Parameter(
                            name = "id",
                            in = ParameterIn.PATH,
                            required = true,
                            description = "ID de la jornada a eliminar",
                            example = "1"
                    )
            }
    )
    public Response eliminar(@PathParam("id") Long id) {
        Jornada jornada = jornadaService.buscarJornadaPorId(id);
        if (jornada != null) {
            jornadaService.eliminarJornada(jornada);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Jornada no encontrada con id " + id)
                    .build();
        }
    }
}
