package Modelo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Schema(description = "Entidad que representa una zona delimitada dentro de un barrio.")
public class Zona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la zona", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nombre identificador de la zona", example = "Zona Sur")
    private String nombre;

    @ElementCollection(targetClass = GeoPoint.class)
    @Schema(description = "Lista de puntos geográficos que delimitan la zona", implementation = GeoPoint.class)
    private List<GeoPoint> geoPoint;

    @ManyToOne
    @JoinColumn(name = "barrio_id")
    @Schema(description = "Barrio al que pertenece esta zona", implementation = Barrio.class)
    private Barrio barrio;

    public Zona() {
    }

    public Zona(String nombre, List<GeoPoint> geoPoint, Barrio barrio) {
        this.nombre = nombre;
        this.geoPoint = geoPoint;
        this.barrio = barrio;
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

    public List<GeoPoint> getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(List<GeoPoint> geoPoint) {
        this.geoPoint = geoPoint;
    }

    public Barrio getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }
}
