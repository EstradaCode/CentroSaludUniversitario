package Modelo;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

@Entity
public class Encuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fechaCreacion;
    private GeoPoint coordenadas;

        @ManyToOne
        private Encuestador encuestador;
        @Embedded
        private GeoPoint ubicacion;
        @ElementCollection
        @CollectionTable(name = "pregunta_respuesta", joinColumns = @JoinColumn(name = "encuesta_id"))
        @MapKeyJoinColumn(name = "pregunta_id")
        @Column(name = "respuesta") // esto genera una tabla intermedio que representa el mapa
        private Map<Pregunta, String> respuestas;

        public Encuesta() {}

        public Encuesta( String fechaCreacion, GeoPoint coordenadas, Encuestador encuestador) {
            this.fechaCreacion = fechaCreacion;
            this.coordenadas = coordenadas;
            this.encuestador = encuestador;
            this.respuestas = new HashMap<>();
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public GeoPoint getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(GeoPoint coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Encuestador getEncuestador() {
        return encuestador;
    }

    public void setEncuestador(Encuestador encuestador) {
        this.encuestador = encuestador;
    }

    public Map<Pregunta, String> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(Map<Pregunta, String> respuestas) {
        this.respuestas = respuestas;
    }
// Getters y setters...
    }

