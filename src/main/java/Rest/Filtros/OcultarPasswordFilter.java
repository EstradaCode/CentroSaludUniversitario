package Rest.Filtros;
import Modelo.Usuario;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

@Provider
@Priority(Priorities.ENTITY_CODER)
public class OcultarPasswordFilter implements ContainerResponseFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {

        // Verificamos que el recurso que responde sea UsuarioResource
        Class<?> recurso = resourceInfo.getResourceClass();

        if (recurso.getSimpleName().equals("UsuarioResource")) {
            Object entity = responseContext.getEntity();

            if (entity == null) return;

            if (entity instanceof Usuario) {
                limpiarPassword((Usuario) entity);
            } else if (entity instanceof List<?>) {
                List<?> lista = (List<?>) entity;
                for (Object o : lista) {
                    if (o instanceof Usuario) {
                        limpiarPassword((Usuario) o);
                    }
                }
            }
        }
    }

    private void limpiarPassword(Usuario usuario) {
        try {
            Field passwordField = Usuario.class.getDeclaredField("password");
            passwordField.setAccessible(true);
            passwordField.set(usuario, null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace(); // O logueá como prefieras
        }
    }
}
