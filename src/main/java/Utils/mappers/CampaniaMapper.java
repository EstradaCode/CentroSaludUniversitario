package Utils.mappers;

import Dtos.Campania.DetailCampaniaResponseDTO;
import Dtos.Campania.ListCampaniaResponseDTO;
import Modelo.Campania;

public final class CampaniaMapper {
    public ListCampaniaResponseDTO toListDTO(Campania c) {
        // NOTA: los agregados (cantJornadas, totalEncuestas, totalEncuestadores) acá van en 0
        // para evitar N+1; si después querés, los calculamos con queries específicas en el DAO.
        return new ListCampaniaResponseDTO(
                c.getId(),
                c.getNombre(),
                c.isActiva(),
                c.getFechaInicio(),
                c.getFechaFin(),
                0, // cantidadJornadas (optimizable con proyección)
                0, // totalEncuestas    (optimizable con proyección)
                0 ,
                c.getBarrio().getNombre(),
                c.getOrganizacionSocial() != null ? c.getOrganizacionSocial().getNombre() : null// totalEncuestadores(optimizable con proyección)
        );
    }

    public DetailCampaniaResponseDTO toDetailDTO(Campania c) {
        String orgNombre  = (c.getOrganizacionSocial() != null) ? c.getOrganizacionSocial().getNombre() : null;
        String zonaNombre = (c.getBarrio() != null) ? c.getBarrio().getNombre() : null;

        return new DetailCampaniaResponseDTO(
                c.getId(),
                c.getNombre(),
                c.isActiva(),
                c.getFechaInicio(),
                c.getFechaFin(),
                orgNombre,
                zonaNombre,
                (c.getVersion() != null ? c.getVersion() : null) // si usás @Version
        );
    }

}
