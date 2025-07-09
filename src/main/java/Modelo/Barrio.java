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
    private List<Zona> zonas;


    public Barrio() {
    }

    public Barrio(List<Zona> zonas, String nombre) {
        this.zonas = zonas;
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public List<Zona> getZonas() {
        return zonas;
    }
    public void setZonas(List<Zona> zonas) {
        this.zonas = zonas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
