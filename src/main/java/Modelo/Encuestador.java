package Modelo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Schema(description = "Entidad que representa un encuestador, heredando de Persona.")
public class Encuestador extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del encuestador", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "barrio_id")
    @Schema(description = "Barrio al que pertenece el encuestador", implementation = Barrio.class)
    private Barrio barrio;

    @Schema(description = "Cantidad de horas trabajadas por el encuestador", example = "12")
    private int horasTrabajadas;

    public Encuestador() {
    }

    public Encuestador(String nombre, String apellido, long dni, long telefono, Barrio barrio, int horasTrabajadas) {
        super(nombre, apellido, dni, telefono);
        this.barrio = barrio;
        this.horasTrabajadas = horasTrabajadas;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
