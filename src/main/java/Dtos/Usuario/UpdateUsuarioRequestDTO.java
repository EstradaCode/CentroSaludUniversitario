package Dtos.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

// Actualizar usuario (parcial: campos que vengan != null se aplican)
public record UpdateUsuarioRequestDTO(
        String nombre,
        String apellido,
        Long dni,
        Long telefono,
        String username,
        @Email String email,
        Boolean enabled,
        String rol,
        Long matricula,
        @Size(min = 8) String password // opcional; si viene, se hashea
) {}
