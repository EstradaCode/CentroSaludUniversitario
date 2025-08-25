package Dtos.Usuario;
// Respuesta de login
public record LoginResponse(
        String accessToken,
        long expiresIn,
        String tokenType
) {}
