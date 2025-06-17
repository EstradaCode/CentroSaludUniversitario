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
    private String latitud;
    private String longitud;

        @ManyToOne
        private Encuestador encuestador;

        @ElementCollection
        @CollectionTable(name = "pregunta_respuesta", joinColumns = @JoinColumn(name = "encuesta_id"))
        @MapKeyJoinColumn(name = "pregunta_id")
        @Column(name = "respuesta")
        private Map<Pregunta, String> respuestas;

        public Encuesta() {}

        public Encuesta( String fechaCreacion, String latitud, String longitud, Encuestador encuestador) {
            this.fechaCreacion = fechaCreacion;
            this.latitud = latitud;
            this.longitud = longitud;
            this.encuestador = encuestador;
            this.respuestas = new HashMap<>();
        }

        // Getters y setters...
    }

