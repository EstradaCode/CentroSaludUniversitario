package Modelo;

public class Barrio {
    private String nombre;
    private Vec2D coordenadas;


    public Barrio() {
    }

    public Barrio(Vec2D coordenadas, String nombre) {
        this.coordenadas = coordenadas;
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Vec2D getCoordenadas() {
        return coordenadas;
    }
    public void setCoordenadas(Vec2D coordenadas) {
        this.coordenadas = coordenadas;
    }
}
