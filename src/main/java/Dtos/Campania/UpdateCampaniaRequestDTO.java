package Dtos.Campania;

import java.time.LocalDate;

public record UpdateCampaniaRequestDTO(
        // Campos opcionales: solo los no-null se aplican
        String nombre,
        String descripcion,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        Boolean activa,
        Long zonaId,                 // si querés permitir cambiar zona
        String organizacionCuit// opcional: re-vincular a otra organización por CUIT
) {}
