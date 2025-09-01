package Dtos.Jornada;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record ListJornadaResponse(
        @Schema(description = "ID de la jornada", example = "1") Long id,
        @Schema(description = "Fecha de la jornada", example = "2025-07-15") LocalDate fecha,
        @Schema(description = "Hora de inicio (HH:mm)", example = "09:00") String horaInicio,
        @Schema(description = "Hora de fin (HH:mm)", example = "13:00") String horaFin,
        @Schema(description = "ID de campaña") Long campaniaId,
        @Schema(description = "Nombre de campaña (join)") String campaniaNombre,
        @Schema(description = "Cantidad de encuestas realizadas") long encuestasRealizadas
) {}
