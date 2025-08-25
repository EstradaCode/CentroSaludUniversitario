package Dtos.Usuario;

import jakarta.validation.constraints.NotBlank;

// Login
public record LoginRequestDTO(
        @NotBlank String identity, // email o username
        @NotBlank String password
) {}
