package Utils.mappers;

import Dtos.Encuestador.CreateEncuestadorRequestDTO;
import Modelo.Barrio;
import Modelo.Encuestador;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EncuestadorMapper {

    public CreateEncuestadorRequestDTO toDTO(Encuestador e) {
        return new CreateEncuestadorRequestDTO(
                e.getNombre(),
                e.getTelefono(),
                e.getBarrio() != null ? e.getBarrio().getNombre() : null,
                e.getHorasTrabajadas()
        );
    }

    public Encuestador toEntity(CreateEncuestadorRequestDTO dto, Barrio barrio) {
        var e = new Encuestador();
        e.setNombre(dto.nombre());
       // e.setEmail(dto.email());
        e.setTelefono(dto.telefono());
        e.setHorasTrabajadas(dto.horasTrabajadas());
        e.setBarrio(barrio);
        return e;
    }

    public void updateEntity(Encuestador target, CreateEncuestadorRequestDTO dto, Barrio barrio) {
        target.setNombre(dto.nombre());
        //target.setEmail(dto.email());
        target.setTelefono(dto.telefono());
        target.setHorasTrabajadas(dto.horasTrabajadas());
        target.setBarrio(barrio);
    }
}
