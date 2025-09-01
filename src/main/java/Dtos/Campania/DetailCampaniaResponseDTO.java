package Dtos.Campania;

import java.time.LocalDate;

public record DetailCampaniaResponseDTO(

        Long id,
        String nombre,
        Boolean activa,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        String organizacionNombre,
        String zonaNombre,
        Long version
){}
