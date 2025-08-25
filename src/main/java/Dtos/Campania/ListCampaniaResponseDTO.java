package Dtos.Campania;

import java.time.LocalDate;

public record ListCampaniaResponseDTO(
            Long id,
            String nombre,
            Boolean activa,
            LocalDate fechaInicio,
            LocalDate fechaFin,

            // Info resumida para el listado
            int cantidadJornadas,       // total de jornadas asociadas
            long totalEncuestas,        // suma de encuestas realizadas
            int totalEncuestadores,     // encuestadores asignados
            String zonaNombre,          // nombre de la zona
            String organizacionNombre   // nombre de la organización
    ) {}
