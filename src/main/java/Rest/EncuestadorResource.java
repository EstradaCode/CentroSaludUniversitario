package Rest;

import Modelo.Encuestador;
import Servicios.Negocio.EncuestadorService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@RequestScoped
@Consumes("application/json")
@Produces("application/json")
@Path("/encuestadores")
public class EncuestadorResource {
    @Inject
    private EncuestadorService encuestadorService;

    @GET
    public Response listarEncuestadores() {
        return Response.ok(encuestadorService.listarEncuestadores()).build();
    }

    @GET
    @Path("/{id}")
    public Response obtenerEncuestadorPorId(Long id) {
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
    public Response crearEncuestador(Encuestador encuestador) {
        encuestadorService.crearEncuestador(encuestador);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizarEncuestador(@PathParam("id") Long id, Encuestador encuestador) {
        encuestador.setId(id);
        encuestadorService.actualizarEncuestador(encuestador);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
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
