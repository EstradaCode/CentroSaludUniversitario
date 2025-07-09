package Rest;

import Modelo.Campania;
import Servicios.Negocio.CampaniaService;
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
public class CampaniaResource {
    @Inject
    private CampaniaService campaniaService;

    @GET
    public List<Campania> listar() { return campaniaService.listarCampanias();}

    @GET
    @Path("/{id}")
    public Response obtenerPorId(@PathParam("id") Long id) {
        Campania campania = campaniaService.obtenerCampaniaPorId(id);
        if (campania != null) {
            return Response.ok(campania).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Campania no encontrada con id " + id)
                    .build();
        }
    }
    @POST
    public Response crear(Campania campania) {
        campaniaService.crearCampania(campania);
        return Response.status(Response.Status.CREATED).build();
    }
    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Long id, Campania campania) {
        campania.setId(id);
        campaniaService.actualizarCampania(campania);
        return Response.ok().build();
    }
    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Long id) {
        campaniaService.eliminarCampania(id);
        return Response.noContent().build();
    }
}
