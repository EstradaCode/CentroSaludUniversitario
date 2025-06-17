package Modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Barrio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private GeoPoint coordenadas;


    public Barrio() {
    }

    public Barrio(GeoPoint coordenadas, String nombre) {
        this.coordenadas = coordenadas;
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public GeoPoint getCoordenadas() {
        return coordenadas;
    }
    public void setCoordenadas(GeoPoint coordenadas) {
        this.coordenadas = coordenadas;
    }
}
