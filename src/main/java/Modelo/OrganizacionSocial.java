package Modelo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Schema(description = "Entidad que representa una organización social dentro de un barrio.")
public class OrganizacionSocial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único de la organización", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nombre de la organización social", example = "Red Solidaria", required = true)
    private String nombre;

    @Schema(description = "Domicilio físico de la organización", example = "Calle Falsa 123", required = true)
    private String domicilio;

    @ManyToOne
    @JoinColumn(name = "barrio_id")
    @Schema(description = "Barrio al que pertenece la organización. Solo se requiere su ID al cargar.", implementation = Barrio.class)
    private Barrio barrio;

    @Schema(description = "Actividad principal de la organización", example = "Comedor comunitario", required = true)
    private String actividadPrincipal;

    public OrganizacionSocial() {
    }

    public OrganizacionSocial(String nombre, String domicilio, Barrio barrio, String actividadPrincipal) {
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.barrio = barrio;
        this.actividadPrincipal = actividadPrincipal;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Barrio getBarrio() {
        return barrio;
    }
    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }

    public String getActividadPrincipal() {
        return actividadPrincipal;
    }
    public void setActividadPrincipal(String actividadPrincipal) {
        this.actividadPrincipal = actividadPrincipal;
    }
}
