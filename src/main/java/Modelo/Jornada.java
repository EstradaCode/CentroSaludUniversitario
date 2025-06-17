package Modelo;

import jakarta.persistence.*;

import java.util.List;
@Entity
public class Jornada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String horaInicio;
    private String horaFin;
    private String fecha;
    @OneToMany
    private List<Encuesta> encuestas;

    public Jornada() {
    }
    public Jornada(String horaInicio, String horaFin, String fecha, List<Encuesta> encuestas) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fecha = fecha;
        this.encuestas = encuestas;
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
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public List<Encuesta> getEncuestas() {
        return encuestas;
    }
    public void setEncuestas(List<Encuesta> encuestas) {
        this.encuestas = encuestas;
    }
}
