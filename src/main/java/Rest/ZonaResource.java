package Rest;

import Modelo.Zona;
import Servicios.Negocio.ZonaService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/zonas")
@RequestScoped
@Consumes("application/json")
@Produces("application/json")
public class ZonaResource {
    @Inject
    private ZonaService zonaService;
    @POST
    public Response crear(Zona zona) {
        zonaService.crearZona(zona);
        return Response.status(Response.Status.CREATED).build();
    }
    @GET
    public Response listar() {
        return Response.ok(zonaService.listarZonas()).build();
    }
    @GET
    @Path("/{id}")
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
    @Path("/{nombre}")
    public Response obtenerPorNombre(@PathParam("nombre") String nombre) {
        List<Zona> zona = zonaService.buscarZonasPorNombre(nombre);
        if (zona != null) {
            return Response.ok(zona).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Zona no encontrada con nombre " + nombre)
                    .build();
        }
    }
    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Long id, Zona zona) {
        zona.setId(id);
        zonaService.actualizarZona(zona);
        return Response.ok().build();
    }
    @DELETE
    @Path("/{id}")
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
