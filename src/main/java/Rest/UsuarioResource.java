package Rest;

import Dtos.Usuario.*;
import Servicios.Negocio.IUsuarioService;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/usuarios")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    // CAMBIO: inyectamos la interfaz, no la implementación concreta
    @Inject
    IUsuarioService usuarioService;

    @Context
    UriInfo uriInfo;

    // CAMBIO: ahora devolvemos PageResponse<ListUsuarioResponseDTO>
    @GET
    @io.swagger.v3.oas.annotations.Operation(summary = "Listar usuarios (paginado)")
    public PageResponse<ListUsuarioResponseDTO> listar(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("20") int size,
            @QueryParam("sort") @DefaultValue("id") String sort,
            @QueryParam("q") String q
    ) {
        return usuarioService.listar(page, size, sort, q);
    }

    // CAMBIO: devolvemos DTO de detalle
    @GET
    @Path("/{id}")
    @io.swagger.v3.oas.annotations.Operation(summary = "Obtener usuario por ID")
    public DetailUsuarioResponseDTO obtener(@PathParam("id") Long id) {
        return usuarioService.obtener(id);
    }

    // CAMBIO: recibimos DTO de entrada (request), no entidad
    // CAMBIO: devolvemos 201 + Location + body {id}
    @POST
    @io.swagger.v3.oas.annotations.Operation(summary = "Crear usuario")
    public Response crear(@Valid CreateUsuarioRequestDTO dto) {
        Long id = usuarioService.crear(dto);
        UriBuilder b = uriInfo.getAbsolutePathBuilder().path(String.valueOf(id));
        return Response.created(b.build()).entity(java.util.Map.of("id", id)).build();
    }

    // CAMBIO: recibimos DTO de actualización parcial y devolvemos 204
    @PUT
    @Path("/{id}")
    @io.swagger.v3.oas.annotations.Operation(summary = "Actualizar usuario")
    public Response actualizar(@PathParam("id") Long id, @Valid UpdateUsuarioRequestDTO dto) {
        usuarioService.actualizar(id, dto);
        return Response.noContent().build(); // 204
    }

    // CAMBIO: 204 No Content si elimina OK
    @DELETE
    @Path("/{id}")
    @io.swagger.v3.oas.annotations.Operation(summary = "Eliminar usuario")
    public Response eliminar(@PathParam("id") Long id) {
        usuarioService.eliminar(id);
        return Response.noContent().build(); // 204
    }

    // CAMBIO: reutilizamos el service de actualizar con enabled=true
    @PUT
    @Path("/{id}/habilitar")
    @io.swagger.v3.oas.annotations.Operation(summary = "Habilitar usuario")
    public Response habilitar(@PathParam("id") Long id) {
        // Record con todos los campos → pasamos null salvo enabled=true
        UpdateUsuarioRequestDTO dto = new UpdateUsuarioRequestDTO(
                null, null, null, null, null, null, Boolean.TRUE, null, null, null
        );
        usuarioService.actualizar(id, dto);
        return Response.noContent().build(); // 204
    }

    // CAMBIO: reutilizamos el service de actualizar con enabled=false
    @PUT
    @Path("/{id}/deshabilitar")
    @io.swagger.v3.oas.annotations.Operation(summary = "Deshabilitar usuario")
    public Response deshabilitar(@PathParam("id") Long id) {
        UpdateUsuarioRequestDTO dto = new UpdateUsuarioRequestDTO(
                null, null, null, null, null, null, Boolean.FALSE, null, null, null
        );
        usuarioService.actualizar(id, dto);
        return Response.noContent().build(); // 204
    }
}

