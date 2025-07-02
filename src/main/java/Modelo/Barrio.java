package Modelo;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Barrio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @ElementCollection
    @CollectionTable(name = "barrio_coordenadas", joinColumns = @JoinColumn(name = "barrio_id"))
    private List<GeoPoint> coordenadas;


    public Barrio() {
    }

    public Barrio(List<GeoPoint> coordenadas, String nombre) {
        this.coordenadas = coordenadas;
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public List<GeoPoint> getCoordenadas() {
        return coordenadas;
    }
    public void setCoordenadas(List<GeoPoint> coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
