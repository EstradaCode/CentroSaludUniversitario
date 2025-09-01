package Dtos.Encuestador;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
// con uno estamos bien.
public record CreateEncuestadorRequestDTO(
        @NotBlank String nombre,
        @Email String email,
        @NotBlank String telefono,
        @NotNull Long barrioId,
        @Min(0) int horasTrabajadas
) {
}
