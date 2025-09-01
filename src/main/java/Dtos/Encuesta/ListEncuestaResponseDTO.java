package Dtos.Encuesta;

import Utils.Enums.NivelIngresos;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.Date;

public record ListEncuestaResponseDTO(@Schema(description = "ID de la encuesta", example = "123") Long id,
                                      @Schema(description = "Fecha de creación (fuente del ETL)") Date fechaCreacion,
                                      @Schema(description = "Latitud (si está disponible)") Double latitud,
                                      @Schema(description = "Longitud (si está disponible)") Double longitud,
                                      @Schema(description = "ID de la jornada") Long jornadaId,
                                      @Schema(description = "Fecha de la jornada") LocalDate jornadaFecha,
                                      @Schema(description = "ID de campaña") Long campaniaId,
                                      @Schema(description = "Nombre de la campaña") String campaniaNombre,
                                      @Schema(description = "Nivel de ingresos (para filtros rápidos)") NivelIngresos ingresos,
                                      @Schema(description = "¿Recibe asistencia alimentaria?") Boolean asistenciaAlimentaria
) {}
