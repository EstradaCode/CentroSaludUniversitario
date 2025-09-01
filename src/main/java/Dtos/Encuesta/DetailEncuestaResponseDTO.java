package Dtos.Encuesta;

import Modelo.GeoPoint;
import Utils.Enums.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.Date;

public record DetailEncuestaResponseDTO (@Schema(description = "ID de la encuesta") Long id,
                                         @Schema(description = "Fecha de creación (fuente del ETL)") Date fechaCreacion,
                                         @Schema(description = "Latitud (si está disponible)") Double latitud,
                                         @Schema(description = "Longitud (si está disponible)") Double longitud,
                                         @Schema(description = "Coordenadas embebidas") GeoPoint coordenadas,
                                         @Schema(description = "ID de la jornada") Long jornadaId,
                                         @Schema(description = "Fecha de la jornada") LocalDate jornadaFecha,
                                         @Schema(description = "ID de campaña") Long campaniaId,
                                         @Schema(description = "Nombre de la campaña") String campaniaNombre,


                                         @Schema(description = "Tipo de vivienda") TipoVivienda tipoVivienda,
                                         @Schema(description = "¿Acceso a agua?") Boolean accesoAgua,
                                         @Schema(description = "¿Agua potable?") Boolean aguaPotable,
                                         @Schema(description = "Tipo de conexión eléctrica") TipoConexionElectrica conexionElectrica,
                                         @Schema(description = "Sistemas de calefacción")TipoCalefaccion calefaccion,
                                         @Schema(description = "Cantidad de habitaciones") CantidadHabitaciones cantidadHabitaciones,
                                         @Schema(description = "¿Recibe asistencia alimentaria?") Boolean asistenciaAlimentaria,
                                         @Schema(description = "Nivel de ingresos") NivelIngresos ingresos,
                                         @Schema(description = "Tipos de atención en salud") AtencionSalud atencionSalud,
                                         @Schema(description = "Métodos anticonceptivos") MetodoAnticonceptivo anticonceptivos,
                                         @Schema(description = "Acceso a medicación") AccesoMedicacion accesoMedicacion,


                                         @Schema(description = "Cantidad de personas relevadas en esta encuesta") int personasCount){
}
