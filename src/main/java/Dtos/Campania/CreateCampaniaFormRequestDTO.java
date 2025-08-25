package Dtos.Campania;

import Dtos.Campania.Organizacion.CreateOrganizacionRequestDTO;

import java.time.LocalDate;

public record CreateCampaniaFormRequestDTO(

        String nombre,
        String descripcion,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        Long zonaId,// A: vincular
        CreateOrganizacionRequestDTO organizacion // B: crear
) {}
