package Modelo;

import jakarta.persistence.*;

import java.util.List;
@Entity
public class Zona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @ElementCollection(targetClass = GeoPoint.class)
    private List<GeoPoint> geoPoint;
    @ManyToOne
    @JoinColumn(name = "barrio_id")
    private Barrio barrio;

    public Barrio getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }

    public Zona() {
    }

    public Zona(Long id, String nombre, List<GeoPoint> geoPoint) {
        this.id = id;
        this.nombre = nombre;
        this.geoPoint = geoPoint;
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
}
