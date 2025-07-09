package Rest;

import Modelo.Barrio;
import Modelo.Jornada;
import Servicios.Negocio.BarrioService;
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
public class BarrioResource {

    @Inject
    private BarrioService barrioService;

    @GET
    public List<Barrio> listar() {
        return barrioService.buscarTodos();
    }

    @GET
    @Path("/{id}")
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
    public Response crear(Barrio barrio) {
        barrioService.crearBarrio(barrio);
        System.out.println("Barrio creado: " + barrio.getNombre());
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Long id, Barrio barrio) {
        barrio.setId(id);
        barrioService.actualizarBarrio(barrio);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
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