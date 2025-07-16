package Modelo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Schema(description = "Entidad que representa una jornada de encuestas dentro de una campaña.")
public class Jornada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la jornada", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @ManyToOne
    @Schema(description = "Campaña asociada a la jornada. Solo se necesita el ID al crear.", implementation = Campania.class)
    @JsonbTransient
    private Campania campania;

    @Schema(description = "Hora de inicio de la jornada (HH:mm)", example = "09:00", required = true)
    private String horaInicio;

    @Schema(description = "Hora de fin de la jornada (HH:mm)", example = "13:00", required = true)
    private String horaFin;

    @Schema(description = "Fecha de la jornada", example = "2025-07-15", required = true)
    private LocalDate fecha;

    @OneToMany(mappedBy = "jornada", cascade = CascadeType.ALL)
    @Schema(description = "Lista de encuestas realizadas durante la jornada. Solo se gestiona internamente.")
    private List<Encuesta> encuestas = new ArrayList<>();

    public Jornada() {
    }

    public Jornada(Campania campania, String horaInicio, String horaFin, LocalDate fecha, List<Encuesta> encuestas) {
        this.campania = campania;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fecha = fecha;
        this.encuestas = encuestas != null ? encuestas : new ArrayList<>();
    }

    public Jornada(Campania campania, String horaInicio, String horaFin, LocalDate fecha) {
        this.campania = campania;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Campania getCampania() {
        return campania;
    }
    public void setCampania(Campania campania) {
        this.campania = campania;
    }

    public String getHoraInicio() {
        return horaInicio;
    }
    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }
    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public List<Encuesta> getEncuestas() {
        return encuestas;
    }
    public void setEncuestas(List<Encuesta> encuestas) {
        this.encuestas = encuestas;
    }
}
