package Modelo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "zona",
        uniqueConstraints = @UniqueConstraint(columnNames = {"barrio_id","nombre"}))
public class Zona {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "barrio_id", nullable = false)
    private Barrio barrio;

    // Polígono: lista ORDENADA de vértices
    @ElementCollection
    @CollectionTable(name = "zona_polygon", joinColumns = @JoinColumn(name = "zona_id"))
    @OrderColumn(name = "ord") // <── preserva el orden de los puntos
    private List<GeoPoint> polygon = new ArrayList<>();

    public Zona() {
    }

    public Zona(String nombre, Barrio barrio, List<GeoPoint> polygon) {
        this.id = id;
        this.nombre = nombre;
        this.barrio = barrio;
        this.polygon = polygon;
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

    public Barrio getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }

    public List<GeoPoint> getPolygon() {
        return polygon;
    }

    public void setPolygon(List<GeoPoint> polygon) {
        this.polygon = polygon;
    }
}
