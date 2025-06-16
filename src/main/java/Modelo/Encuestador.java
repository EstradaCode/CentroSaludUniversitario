package Modelo;

import jakarta.persistence.Entity;

@Entity
public class Encuestador extends Persona{
    private Long id;
    private Barrio barrio;
    private int horasTrabajadas;

    public Encuestador() {
    }
    public Encuestador(String nombre, String apellido, long dni, long telefono, Barrio barrio, int horasTrabajadas) {
        super(nombre, apellido, dni, telefono);
        this.barrio = barrio;
        this.horasTrabajadas = horasTrabajadas;
    }
    public Barrio getBarrio() {
        return barrio;
    }
    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }
    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }
    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

}
