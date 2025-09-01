package Dtos.Jornada;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record UpdateJornadaRequest(
        @Schema(description = "ID de la jornada", example = "1", required = true, accessMode = Schema.AccessMode.READ_ONLY)
        @NotNull Long id,


        @Schema(description = "ID de la campaña asociada", example = "10", required = true)
        @NotNull Long campaniaId,


        @Schema(description = "Fecha de la jornada", example = "2025-07-15", required = true)
        @NotNull LocalDate fecha,


        @Schema(description = "Hora de inicio (HH:mm)", example = "09:00", required = true)
        @NotBlank @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Formato HH:mm") String horaInicio,


        @Schema(description = "Hora de fin (HH:mm)", example = "13:00", required = true)
        @NotBlank @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Formato HH:mm") String horaFin
) {}
