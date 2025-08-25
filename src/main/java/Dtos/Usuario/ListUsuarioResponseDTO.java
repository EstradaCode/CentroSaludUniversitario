package Dtos.Usuario;

import java.time.Instant;

// Item de listado
public record ListUsuarioResponseDTO(
        Long id,          // por ahora PK interna; más adelante podés pasar a publicId (UUID)
        String username,
        String email,
        Boolean enabled,
        String rol
) {}
