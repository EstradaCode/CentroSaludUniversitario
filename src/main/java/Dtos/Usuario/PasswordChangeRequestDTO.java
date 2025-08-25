package Dtos.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Cambiar contraseña
public record PasswordChangeRequestDTO(
        @NotBlank String oldPassword,
        @NotBlank @Size(min = 8) String newPassword
) {}
