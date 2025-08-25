package Dtos.Campania;

import java.time.LocalDate;

public record DetailCampaniaResponseDTO(

        Long id,
        String nombre,
        String descripcion,
        Boolean activa,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        Long organizacionId,
        Long zonaId,
        Long version
){}
