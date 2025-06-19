package Modelo;

import jakarta.persistence.*;

@Entity
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "encuesta_id", nullable = false)
    private Encuesta encuesta;

    @ManyToOne
    @JoinColumn(name = "pregunta_id", nullable = false)
    private Pregunta pregunta;
    @Column(name = "indice_persona", nullable = true)
    private int indice_persona;


    private String valor;

    public Respuesta(){}

    public Respuesta(Encuesta encuesta,Pregunta pregunta, int indicePersona, String valor) {
        this.encuesta = encuesta;
        this.pregunta = pregunta;
        indice_persona = indicePersona;
        this.valor = valor;
    }

    public Long getId() { return id; }

    public Encuesta getEncuesta() { return encuesta; }

    public void setEncuesta(Encuesta encuesta) { this.encuesta = encuesta; }

    public Pregunta getPregunta() { return pregunta; }

    public void setPregunta(Pregunta pregunta) { this.pregunta = pregunta; }

    public String getValor() { return valor; }

    public void setValor(String valor) { this.valor = valor; }

    public int getIndice_persona() {
        return indice_persona;
    }

    public void setIndice_persona(int indice_persona) {
        this.indice_persona = indice_persona;
    }
}