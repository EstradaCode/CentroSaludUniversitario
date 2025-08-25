package Dtos.Usuario;

import java.time.Instant;

// Detalle
public record DetailUsuarioResponseDTO(
        Long id,
        String nombre,
        String apellido,
        Long dni,
        Long telefono,
        String username,
        String email,
        Boolean enabled,
        String rol,
        Long matricula
) {}
