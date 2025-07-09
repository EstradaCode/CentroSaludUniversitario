package Rest;
import Modelo.OrganizacionSocial;
import Servicios.Negocio.OrganizacionSocialService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/organizaciones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrganizacionSocialResource {

    @Inject
    private OrganizacionSocialService organizacionService;

    @GET
    public List<OrganizacionSocial> listarTodas() {
        return organizacionService.obtenerTodas();
    }

    @GET
    @Path("/{id}")
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
    public Response crear(OrganizacionSocial organizacion) {
        organizacionService.guardarOrganizacion(organizacion);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
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
