package Rest;

import Modelo.Jornada;
import Servicios.Negocio.JornadaService;
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
public class JornadaResource {

    @Inject
    private JornadaService jornadaService;

    @GET
    public List<Jornada> listar() {
        return jornadaService.obtenerTodasLasJornadas();
    }

    @GET
    @Path("/{id}")
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
    public Response crear(Jornada jornada) {
        jornadaService.crearJornada(jornada);
        System.out.println("Jornada creada: " + jornada.getId());
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Long id, Jornada jornada) {
        jornada.setId(id);
        jornadaService.actualizarJornada(jornada);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
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