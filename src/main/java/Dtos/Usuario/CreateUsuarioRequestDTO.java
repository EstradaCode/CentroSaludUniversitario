package Dtos.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Crear usuario (entra password)
public record CreateUsuarioRequestDTO(
        @NotBlank String nombre,
        @NotBlank String apellido,
        @NotNull  Long dni,
        @NotNull Long telefono,
        @NotBlank @Size(min = 3, max = 30) String username,
        @NotBlank @Size(min = 8) String password,
        @NotBlank @Email String email,
        @NotBlank String rol,   // "ADMIN" | "SALUD" | "VISITANTE"
        Long matricula          // opcional
) {}
